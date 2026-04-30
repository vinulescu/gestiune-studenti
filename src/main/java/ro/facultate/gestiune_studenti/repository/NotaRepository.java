package ro.facultate.gestiune_studenti.repository;

import ro.facultate.gestiune_studenti.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
}