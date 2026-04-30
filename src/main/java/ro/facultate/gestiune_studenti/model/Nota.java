package ro.facultate.gestiune_studenti.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "NOTA")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Nota")
    private Long idNota;

    @ManyToOne
    @JoinColumn(name = "ID_Student", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "ID_Materie", nullable = false)
    private Materie materie;

    @ManyToOne
    @JoinColumn(name = "ID_Profesor", nullable = false)
    private Profesor profesor;

    @Column(name = "Valoare", nullable = false)
    private Integer valoare;

    @Column(name = "Data_Acordarii", nullable = false)
    private LocalDate dataAcordarii;

    public Nota() {}

    // Getters și Setters
    public Long getIdNota() { return idNota; }
    public void setIdNota(Long idNota) { this.idNota = idNota; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Materie getMaterie() { return materie; }
    public void setMaterie(Materie materie) { this.materie = materie; }
    public Profesor getProfesor() { return profesor; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }
    public Integer getValoare() { return valoare; }
    public void setValoare(Integer valoare) { this.valoare = valoare; }
    public LocalDate getDataAcordarii() { return dataAcordarii; }
    public void setDataAcordarii(LocalDate dataAcordarii) { this.dataAcordarii = dataAcordarii; }
}