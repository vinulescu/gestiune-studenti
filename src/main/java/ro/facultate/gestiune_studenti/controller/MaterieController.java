package ro.facultate.gestiune_studenti.controller;

import ro.facultate.gestiune_studenti.model.Materie;
import ro.facultate.gestiune_studenti.repository.MaterieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class MaterieController {

    @Autowired
    private MaterieRepository materieRepository;

    // Afișează pagina cu lista de materii și formularul
    @GetMapping("/materii")
    public String afiseazaMaterii(Model model) {
        List<Materie> listaMaterii = materieRepository.findAll(); // Extrage tot din baza de date
        model.addAttribute("materii", listaMaterii);
        model.addAttribute("materieNoua", new Materie()); // Obiect gol pentru formular
        return "materii";
    }

    // Salvează o materie nouă
    @PostMapping("/materii/salveaza")
    public String salveazaMaterie(@ModelAttribute("materieNoua") Materie materie) {
        materieRepository.save(materie); // Salvează în baza de date
        return "redirect:/materii"; // Ne întoarce pe pagina cu lista actualizată
    }
}