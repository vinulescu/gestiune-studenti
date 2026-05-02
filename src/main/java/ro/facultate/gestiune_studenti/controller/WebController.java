package ro.facultate.gestiune_studenti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.facultate.gestiune_studenti.repository.StudentRepository;
import ro.facultate.gestiune_studenti.repository.ProfesorRepository;
import ro.facultate.gestiune_studenti.repository.GrupaRepository;
import ro.facultate.gestiune_studenti.repository.MaterieRepository;

@Controller
public class WebController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private GrupaRepository grupaRepository;
    @Autowired
    private MaterieRepository materieRepository;

    @GetMapping("/")
    public String paginaPrincipala(Model model, Authentication authentication) {
        // Verificăm dacă există un utilizator logat și dacă are rolul de ADMIN
        if (authentication != null && authentication.getAuthorities().toString().contains("ROLE_ADMIN")) {
            // Trimitem numărul total către pagina HTML folosind funcția .count() a bazei de date
            model.addAttribute("nrStudenti", studentRepository.count());
            model.addAttribute("nrProfesori", profesorRepository.count());
            model.addAttribute("nrGrupe", grupaRepository.count());
            model.addAttribute("nrMaterii", materieRepository.count());
        }
        return "index";
    }

    @GetMapping("/login")
    public String afiseazaPaginaLogin() {
        return "login"; // Returnează fișierul login.html din folderul templates
    }
}