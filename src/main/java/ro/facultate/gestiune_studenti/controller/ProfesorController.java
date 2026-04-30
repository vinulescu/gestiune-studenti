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
}