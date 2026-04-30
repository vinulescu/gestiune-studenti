package ro.facultate.gestiune_studenti.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UTILIZATOR")
public class Utilizator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Utilizator")
    private Long idUtilizator;

    @Column(name = "NumeUtilizator", nullable = false, unique = true)
    private String numeUtilizator;

    @Column(name = "Parola_Criptata", nullable = false)
    private String parolaCriptata;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "Rol", nullable = false)
    private Rol rol;

    public enum Rol {
        Student, Profesor, Secretariat, Admin
    }

    public Utilizator() {}

    public Long getIdUtilizator() { return idUtilizator; }
    public void setIdUtilizator(Long idUtilizator) { this.idUtilizator = idUtilizator; }
    public String getNumeUtilizator() { return numeUtilizator; }
    public void setNumeUtilizator(String numeUtilizator) { this.numeUtilizator = numeUtilizator; }
    public String getParolaCriptata() { return parolaCriptata; }
    public void setParolaCriptata(String parolaCriptata) { this.parolaCriptata = parolaCriptata; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
}