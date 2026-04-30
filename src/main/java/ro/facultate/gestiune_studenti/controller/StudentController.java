package ro.facultate.gestiune_studenti.controller;

import ro.facultate.gestiune_studenti.model.Student;
import ro.facultate.gestiune_studenti.model.Utilizator;
import ro.facultate.gestiune_studenti.model.Grupa;
import ro.facultate.gestiune_studenti.repository.StudentRepository;
import ro.facultate.gestiune_studenti.repository.GrupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Am adăugat acest import pentru securitate
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GrupaRepository grupaRepository;

    // MODIFICAREA 1: Am "injectat" unealta de criptare aici
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/studenti")
    public String afiseazaStudenti(Model model) {
        model.addAttribute("studenti", studentRepository.findAll());
        model.addAttribute("grupe", grupaRepository.findAll());
        return "studenti";
    }

    @PostMapping("/studenti/salveaza")
    public String salveazaStudent(
            @RequestParam String username, @RequestParam String parola, @RequestParam String email,
            @RequestParam String nume, @RequestParam String prenume, @RequestParam String cnp,
            @RequestParam String matricol, @RequestParam Long idGrupa) {

        Utilizator utilizator = new Utilizator();
        utilizator.setNumeUtilizator(username);
        
        // MODIFICAREA 2: Am folosit unealta de criptare pentru a ascunde parola înainte să o salvăm
        utilizator.setParolaCriptata(passwordEncoder.encode(parola)); 
        
        utilizator.setEmail(email);
        utilizator.setRol(Utilizator.Rol.Student);

        Student student = new Student();
        student.setNume(nume);
        student.setPrenume(prenume);
        student.setCnp(cnp);
        student.setMatricol(matricol);
        
        student.setUtilizator(utilizator);
        Grupa grupa = grupaRepository.findById(idGrupa).orElse(null);
        student.setGrupa(grupa);

        studentRepository.save(student);

        return "redirect:/studenti";
    }
}