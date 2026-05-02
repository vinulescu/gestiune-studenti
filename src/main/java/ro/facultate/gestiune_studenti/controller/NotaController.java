package ro.facultate.gestiune_studenti.controller;

import ro.facultate.gestiune_studenti.model.Nota;
import ro.facultate.gestiune_studenti.repository.*;
import ro.facultate.gestiune_studenti.service.RaportService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import java.util.stream.Collectors;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class NotaController {

    @Autowired private NotaRepository notaRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private ProfesorRepository profesorRepository;
    @Autowired private MaterieRepository materieRepository;
    @Autowired private RaportService raportService;

    // Afișăm formularul și lista de note
   @GetMapping("/note")
public String afiseazaNote(Model model, Authentication authentication) {
    // Luăm numele de utilizator al celui logat
    String username = authentication.getName();
    
    // Verificăm ce rol are cel logat
    String rol = authentication.getAuthorities().toString();

    if (rol.contains("ROLE_Student")) { // Atenție la majuscule, depinde cum e în Enum (Student)
        // REQ-35: Studentul își vede doar notele lui
        var noteFiltrate = notaRepository.findAll().stream()
            .filter(n -> n.getStudent().getUtilizator().getNumeUtilizator().equals(username))
            .collect(Collectors.toList());
        model.addAttribute("note", noteFiltrate);
    } else {
        // Profesorii și Adminii văd tot
        model.addAttribute("note", notaRepository.findAll());
    }

    model.addAttribute("studenti", studentRepository.findAll());
    model.addAttribute("profesori", profesorRepository.findAll());
    model.addAttribute("materii", materieRepository.findAll());
    
    return "note";
}

    @GetMapping("/note/export-pdf")
    public void exportaPdf(HttpServletResponse response, Authentication authentication) throws IOException {
        String username = authentication.getName();
        
        // Găsim notele studentului logat
        List<Nota> noteStudent = notaRepository.findAll().stream()
                .filter(n -> n.getStudent().getUtilizator().getNumeUtilizator().equals(username))
                .collect(java.util.stream.Collectors.toList());

        // Setăm browserul să descarce fișierul, nu să afișeze o pagină HTML
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=situatie_scolara_" + username + ".pdf";
        response.setHeader(headerKey, headerValue);

        // Generăm PDF-ul
        raportService.exportaNotePdf(response, noteStudent, username);
    }

    // Salvăm nota în baza de date
    @PostMapping("/note/salveaza")
    public String salveazaNota(
            @RequestParam Long idStudent,
            @RequestParam Long idMaterie,
            @RequestParam Long idProfesor,
            @RequestParam Integer valoare,
            @RequestParam String dataAcordarii) {

        Nota nota = new Nota();
        
        // Căutăm entitățile în baza de date după ID-urile primite din dropdown
        nota.setStudent(studentRepository.findById(idStudent).orElse(null));
        nota.setMaterie(materieRepository.findById(idMaterie).orElse(null));
        nota.setProfesor(profesorRepository.findById(idProfesor).orElse(null));
        
        // Setăm valorile simple
        nota.setValoare(valoare);
        nota.setDataAcordarii(LocalDate.parse(dataAcordarii)); // Transformăm textul din HTML în dată calendaristică

        notaRepository.save(nota);

        return "redirect:/note";
    }
}