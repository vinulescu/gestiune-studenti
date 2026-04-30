package ro.facultate.gestiune_studenti.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PROFESOR")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Profesor")
    private Long idProfesor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Utilizator", referencedColumnName = "ID_Utilizator", unique = true)
    private Utilizator utilizator;

    @Column(name = "Nume", nullable = false)
    private String nume;

    @Column(name = "Prenume", nullable = false)
    private String prenume;

    @Column(name = "Titlu_Academic", nullable = false)
    private String titluAcademic;

    public Profesor() {}

    // Getters și Setters
    public Long getIdProfesor() { return idProfesor; }
    public void setIdProfesor(Long idProfesor) { this.idProfesor = idProfesor; }
    public Utilizator getUtilizator() { return utilizator; }
    public void setUtilizator(Utilizator utilizator) { this.utilizator = utilizator; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public String getTitluAcademic() { return titluAcademic; }
    public void setTitluAcademic(String titluAcademic) { this.titluAcademic = titluAcademic; }
}