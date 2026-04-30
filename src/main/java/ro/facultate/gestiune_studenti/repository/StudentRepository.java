package ro.facultate.gestiune_studenti.repository;

import ro.facultate.gestiune_studenti.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}