package ro.facultate.gestiune_studenti.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GRUPA")
public class Grupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Grupa")
    private Long idGrupa;

    @Column(name = "Nume_Grupa", nullable = false)
    private String numeGrupa;

    @Column(name = "An_Studiu", nullable = false)
    private Integer anStudiu;

    @Column(name = "Forma_Invatamant", nullable = false)
    private String formaInvatamant;

    public Grupa() {}

    // Getters și Setters
    public Long getIdGrupa() { return idGrupa; }
    public void setIdGrupa(Long idGrupa) { this.idGrupa = idGrupa; }
    public String getNumeGrupa() { return numeGrupa; }
    public void setNumeGrupa(String numeGrupa) { this.numeGrupa = numeGrupa; }
    public Integer getAnStudiu() { return anStudiu; }
    public void setAnStudiu(Integer anStudiu) { this.anStudiu = anStudiu; }
    public String getFormaInvatamant() { return formaInvatamant; }
    public void setFormaInvatamant(String formaInvatamant) { this.formaInvatamant = formaInvatamant; }
}