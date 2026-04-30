package ro.facultate.gestiune_studenti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    // Această metodă "prinde" momentul când cineva accesează site-ul tău (localhost:8080/)
    @GetMapping("/")
    public String afiseazaPrimaPagina(Model model) {
        // Trimitem un mesaj din Java către pagina HTML
        model.addAttribute("mesajBunVenit", "Bun venit la platforma de gestiune a facultății!");
        return "index"; // Caută un fișier numit index.html
    }
}