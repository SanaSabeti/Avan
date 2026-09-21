package ir.asta.repository;

import ir.asta.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    List<Teacher> findByClassroomId(Long classroomId);
}


