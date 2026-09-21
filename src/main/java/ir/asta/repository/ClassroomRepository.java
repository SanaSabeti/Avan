package ir.asta.repository;

import ir.asta.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
