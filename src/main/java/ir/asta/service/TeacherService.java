package ir.asta.service;

import ir.asta.exception.ResourceNotFoundException;
import ir.asta.model.Classroom;
import ir.asta.dto.CreateTeacherRequest;
import ir.asta.model.Teacher;
import ir.asta.repository.ClassroomRepository;
import ir.asta.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;

    public TeacherService(TeacherRepository teacherRepository, ClassroomRepository classroomRepository) {
        this.teacherRepository = teacherRepository;
        this.classroomRepository = classroomRepository;
    }

    public Teacher findById(Long classroomId, Integer teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
            .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        if (!teacher.getClassroom().getId().equals(classroomId)) {
            throw new ResourceNotFoundException("Teacher not found in this classroom");
        }

        return teacher;
    }

    public Teacher create(Long classroomId, CreateTeacherRequest request) {
        Classroom classroom = classroomRepository.findById(classroomId)
            .orElseThrow(() -> new ResourceNotFoundException("Classroom not found"));

        Teacher teacher = new Teacher(request.getName());
        teacher.setClassroom(classroom);

        return teacherRepository.save(teacher);
    }

    public void delete(Long classroomId, Integer teacherId) {
        Teacher teacher = findById(classroomId, teacherId);
        teacherRepository.delete(teacher);
    }

    public List<Teacher> findAll(Long classroomId) {
        if (!classroomRepository.existsById(classroomId)) {
            throw new ResourceNotFoundException("Classroom not found");
        }

        return teacherRepository.findByClassroomId(classroomId);
    }
}