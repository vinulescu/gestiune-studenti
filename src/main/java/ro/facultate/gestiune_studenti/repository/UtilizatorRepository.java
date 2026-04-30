package ro.facultate.gestiune_studenti.repository;

import ro.facultate.gestiune_studenti.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; // Nu uita de acest import!

@Repository
public interface UtilizatorRepository extends JpaRepository<Utilizator, Long> {
    // Funcție magică: Spring va ști automat să scrie interogarea SQL!
    Optional<Utilizator> findByNumeUtilizator(String numeUtilizator);
}