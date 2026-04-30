package ro.facultate.gestiune_studenti.repository;

import ro.facultate.gestiune_studenti.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}