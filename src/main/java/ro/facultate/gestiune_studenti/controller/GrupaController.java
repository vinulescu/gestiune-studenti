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
    @org.springframework.web.bind.annotation.PostMapping("/grupe/sterge/{id}")
    public String stergeGrupa(@org.springframework.web.bind.annotation.PathVariable("id") Long idGrupa) {
        grupaRepository.deleteById(idGrupa);
        return "redirect:/grupe";
    }

    @org.springframework.web.bind.annotation.GetMapping("/grupe/editeaza/{id}")
    public String arataFormularEditareGrupa(@org.springframework.web.bind.annotation.PathVariable("id") Long idGrupa, Model model) {
        ro.facultate.gestiune_studenti.model.Grupa grupa = grupaRepository.findById(idGrupa)
            .orElseThrow(() -> new IllegalArgumentException("ID invalid: " + idGrupa));
        model.addAttribute("grupa", grupa);
        return "edit-grupa";
    }

    @org.springframework.web.bind.annotation.PostMapping("/grupe/actualizeaza/{id}")
    public String actualizeazaGrupa(
            @org.springframework.web.bind.annotation.PathVariable("id") Long idGrupa,
            @org.springframework.web.bind.annotation.RequestParam String numeGrupa,
            @org.springframework.web.bind.annotation.RequestParam int anStudiu,
            @org.springframework.web.bind.annotation.RequestParam String formaInvatamant) {
        
        ro.facultate.gestiune_studenti.model.Grupa grupa = grupaRepository.findById(idGrupa).orElseThrow();
        grupa.setNumeGrupa(numeGrupa);
        grupa.setAnStudiu(anStudiu);
        grupa.setFormaInvatamant(formaInvatamant);
        grupaRepository.save(grupa);
        return "redirect:/grupe";
    }
}