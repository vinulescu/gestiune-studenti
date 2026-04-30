package ro.facultate.gestiune_studenti.repository;

import ro.facultate.gestiune_studenti.model.Grupa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupaRepository extends JpaRepository<Grupa, Long> {
}