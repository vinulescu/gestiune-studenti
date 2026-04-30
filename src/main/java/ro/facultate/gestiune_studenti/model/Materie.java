package ro.facultate.gestiune_studenti.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MATERIE")
public class Materie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Materie")
    private Long idMaterie;

    @Column(name = "Denumire", nullable = false)
    private String denumire;

    @Column(name = "Credite", nullable = false)
    private Integer credite;

    @Column(name = "Numar_Ore", nullable = false)
    private Integer numarOre;

    public Materie() {}

    // Getters și Setters
    public Long getIdMaterie() { return idMaterie; }
    public void setIdMaterie(Long idMaterie) { this.idMaterie = idMaterie; }
    public String getDenumire() { return denumire; }
    public void setDenumire(String denumire) { this.denumire = denumire; }
    public Integer getCredite() { return credite; }
    public void setCredite(Integer credite) { this.credite = credite; }
    public Integer getNumarOre() { return numarOre; }
    public void setNumarOre(Integer numarOre) { this.numarOre = numarOre; }
}