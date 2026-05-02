package ro.facultate.gestiune_studenti.controller;

import ro.facultate.gestiune_studenti.model.Profesor;
import ro.facultate.gestiune_studenti.model.Utilizator;
import ro.facultate.gestiune_studenti.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable; // Import adăugat pentru {id}

@Controller
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // REQ-25: Vizualizarea listei
    @GetMapping("/profesori")
    public String afiseazaProfesori(Model model) {
        model.addAttribute("profesori", profesorRepository.findAll());
        return "profesori";
    }

    // REQ-24: Adăugarea unui profesor și a contului său
    @PostMapping("/profesori/salveaza")
    public String salveazaProfesor(
            @RequestParam String username, @RequestParam String parola, @RequestParam String email,
            @RequestParam String nume, @RequestParam String prenume, @RequestParam String titluAcademic) {

        // 1. Creăm contul de Utilizator
        Utilizator utilizator = new Utilizator();
        utilizator.setNumeUtilizator(username);
        utilizator.setParolaCriptata(passwordEncoder.encode(parola)); // Criptează parola aici!
        utilizator.setEmail(email);
        utilizator.setRol(Utilizator.Rol.Profesor);

        // 2. Creăm profilul de Profesor
        Profesor profesor = new Profesor();
        profesor.setNume(nume);
        profesor.setPrenume(prenume);
        profesor.setTitluAcademic(titluAcademic);
        
        // 3. Facem legătura
        profesor.setUtilizator(utilizator);

        // Salvăm în baza de date
        profesorRepository.save(profesor);

        return "redirect:/profesori";
    }

    // ==========================================
    // METODE NOI PENTRU EDITARE ȘI ȘTERGERE
    // ==========================================

    // 1. Șterge un profesor
    @PostMapping("/profesori/sterge/{id}")
    public String stergeProfesor(@PathVariable("id") Long idProfesor) {
        profesorRepository.deleteById(idProfesor);
        return "redirect:/profesori";
    }

    // 2. Afișează pagina de editare cu datele profesorului selectat
    @GetMapping("/profesori/editeaza/{id}")
    public String arataFormularEditareProfesor(@PathVariable("id") Long idProfesor, Model model) {
        Profesor profesor = profesorRepository.findById(idProfesor)
            .orElseThrow(() -> new IllegalArgumentException("ID profesor invalid: " + idProfesor));
        
        model.addAttribute("profesor", profesor);
        return "edit-profesor"; // Trimite către edit-profesor.html
    }

    // 3. Salvează modificările făcute de utilizator
    @PostMapping("/profesori/actualizeaza/{id}")
    public String actualizeazaProfesor(
            @PathVariable("id") Long idProfesor,
            @RequestParam String nume,
            @RequestParam String prenume,
            @RequestParam String titluAcademic) {
        
        // Găsim profesorul existent
        Profesor profesor = profesorRepository.findById(idProfesor)
            .orElseThrow(() -> new IllegalArgumentException("ID profesor invalid: " + idProfesor));
        
        // Actualizăm doar datele personale (nu și contul/parola)
        profesor.setNume(nume);
        profesor.setPrenume(prenume);
        profesor.setTitluAcademic(titluAcademic);
        
        // Salvăm modificările
        profesorRepository.save(profesor);
        
        return "redirect:/profesori"; // Ne întoarcem la lista de profesori
    }
}