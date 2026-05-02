package ro.facultate.gestiune_studenti.controller;

import ro.facultate.gestiune_studenti.model.Materie;
import ro.facultate.gestiune_studenti.repository.MaterieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    // ==========================================
    // METODE NOI PENTRU EDITARE ȘI ȘTERGERE
    // ==========================================

    // 1. Șterge o materie
    @PostMapping("/materii/sterge/{id}")
    public String stergeMaterie(@PathVariable("id") Long idMaterie) {
        materieRepository.deleteById(idMaterie);
        return "redirect:/materii";
    }

    // 2. Afișează pagina de editare
    @GetMapping("/materii/editeaza/{id}")
    public String arataFormularEditareMaterie(@PathVariable("id") Long idMaterie, Model model) {
        Materie materie = materieRepository.findById(idMaterie)
            .orElseThrow(() -> new IllegalArgumentException("ID materie invalid: " + idMaterie));
        
        model.addAttribute("materie", materie);
        return "edit-materie"; // Trimite către noul fișier HTML
    }

    // 3. Salvează modificările făcute de utilizator
    @PostMapping("/materii/actualizeaza/{id}")
    public String actualizeazaMaterie(
            @PathVariable("id") Long idMaterie,
            @RequestParam String denumire,
            @RequestParam int credite,
            @RequestParam int numarOre) {
        
        // Găsim materia existentă
        Materie materie = materieRepository.findById(idMaterie)
            .orElseThrow(() -> new IllegalArgumentException("ID materie invalid: " + idMaterie));
        
        // Actualizăm datele
        materie.setDenumire(denumire);
        materie.setCredite(credite);
        materie.setNumarOre(numarOre);
        
        // Salvăm
        materieRepository.save(materie);
        
        return "redirect:/materii"; // Ne întoarcem la listă
    }
}