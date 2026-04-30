package ro.facultate.gestiune_studenti.model;

import jakarta.persistence.*;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Student")
    private Long idStudent;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Utilizator", referencedColumnName = "ID_Utilizator", unique = true)
    private Utilizator utilizator;

     @ManyToOne
     @JoinColumn(name = "ID_Grupa")
     private Grupa grupa;

    @Column(name = "Nume", nullable = false)
    private String nume;

    @Column(name = "Prenume", nullable = false)
    private String prenume;

    @Column(name = "CNP", nullable = false, unique = true, length = 13)
    private String cnp;

    @Column(name = "Matricol", nullable = false, unique = true)
    private String matricol;

    public Student() {}

    // Getters și Setters
    public Long getIdStudent() { return idStudent; }
    public void setIdStudent(Long idStudent) { this.idStudent = idStudent; }
    public Utilizator getUtilizator() { return utilizator; }
    public void setUtilizator(Utilizator utilizator) { this.utilizator = utilizator; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public String getCnp() { return cnp; }
    public void setCnp(String cnp) { this.cnp = cnp; }
    public String getMatricol() { return matricol; }
    public void setMatricol(String matricol) { this.matricol = matricol; }
    public Grupa getGrupa() { return grupa; }
    public void setGrupa(Grupa grupa) { this.grupa = grupa; }
}