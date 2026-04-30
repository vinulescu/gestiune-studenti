package ro.facultate.gestiune_studenti.controller;

import ro.facultate.gestiune_studenti.model.Grupa;
import ro.facultate.gestiune_studenti.repository.GrupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class GrupaController {

    @Autowired
    private GrupaRepository grupaRepository;

    @GetMapping("/grupe")
    public String afiseazaGrupe(Model model) {
        List<Grupa> listaGrupe = grupaRepository.findAll();
        model.addAttribute("grupe", listaGrupe);
        model.addAttribute("grupaNoua", new Grupa());
        return "grupe";
    }

    @PostMapping("/grupe/salveaza")
    public String salveazaGrupa(@ModelAttribute("grupaNoua") Grupa grupa) {
        grupaRepository.save(grupa);
        return "redirect:/grupe";
    }
}