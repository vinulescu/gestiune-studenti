package ro.facultate.gestiune_studenti.repository;

import ro.facultate.gestiune_studenti.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizatorRepository extends JpaRepository<Utilizator, Long> {
    // Aici Spring Boot ne generează automat funcțiile de salvare, ștergere, căutare în baza de date!
}