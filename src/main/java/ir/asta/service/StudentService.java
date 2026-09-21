package ir.asta.service;

import ir.asta.exception.ResourceNotFoundException;
import ir.asta.dto.CurveStudentsRequest;
import ir.asta.dto.RankedStudentResponse;
import ir.asta.model.Classroom;
import ir.asta.exception.InvalidScoreException;
import ir.asta.model.Student;
import ir.asta.dto.CreateStudentRequest;
import ir.asta.repository.ClassroomRepository;
import ir.asta.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;

    public StudentService(StudentRepository studentRepository, ClassroomRepository classroomRepository) {
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }

    public Student findById(Long classroomId, int studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        if (!student.getClassroom().getId().equals(classroomId)) {
            throw new ResourceNotFoundException("Student not found in this classroom");
        }

        return student;
    }

    public Student create(Long classroomId, CreateStudentRequest request) throws InvalidScoreException {
            Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found"));

            if (request.getScore() < 0 || request.getScore() > 100) {
                throw new InvalidScoreException(
                    "Score should be between 0 and 100");
            }

            Student student = new Student(request.getName(), request.getScore());
            student.setClassroom(classroom);

            return studentRepository.save(student);
    }

    public void delete(Long classroomId, Integer studentId) {
        Student student = findById(classroomId, studentId);
        studentRepository.delete(student);
    }

    public List<Student> findAll(Long classroomId) {
        if (!classroomRepository.existsById(classroomId)) {
            throw new ResourceNotFoundException("Classroom not found");
        }

        return studentRepository.findByClassroomId(classroomId);
    }

    public void curveStudents(Long classroomId, CurveStudentsRequest request) {
        if (!classroomRepository.existsById(classroomId)) {
            throw new ResourceNotFoundException("Classroom not found");
        }

        List<Student> students = studentRepository.findByClassroomId(classroomId);
        students.forEach(student -> student.curveScore(request.getPoint()));

        studentRepository.saveAll(students);
    }

    public List<RankedStudentResponse> sortStudents(Long classroomId) {
        if (!classroomRepository.existsById(classroomId)) {
            throw new ResourceNotFoundException("Classroom not found");
        }

        List<Student> students = studentRepository.findByClassroomId(classroomId);
        students.sort(Comparator.comparingInt(Student::getScore).reversed());

        return students.stream()
            .map(student -> new RankedStudentResponse(student.getId(), student.getName(), student.getScore(), students.indexOf(student) + 1))
            .toList();
    }
}