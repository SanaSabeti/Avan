package ir.asta.service;

import ir.asta.exception.ResourceNotFoundException;
import ir.asta.model.Classroom;
import ir.asta.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public Classroom findById(Long id) {
        return classroomRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Classroom not found"));
    }

    public Classroom create() {
        Classroom classroom = new Classroom();
        return classroomRepository.save(classroom);
    }

    public void delete(Long id) {
        if (!classroomRepository.existsById(id)) {
            throw new ResourceNotFoundException("Classroom not found");
        }
        classroomRepository.deleteById(id);
    }
}
