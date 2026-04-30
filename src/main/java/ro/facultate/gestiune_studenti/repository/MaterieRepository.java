package ro.facultate.gestiune_studenti.repository;

import ro.facultate.gestiune_studenti.model.Materie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterieRepository extends JpaRepository<Materie, Long> {
}