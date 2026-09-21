package ir.asta.controller;

import ir.asta.exception.ResourceNotFoundException;
import ir.asta.dto.CreateStudentRequest;
import ir.asta.exception.InvalidScoreException;
import ir.asta.dto.RankedStudentResponse;
import ir.asta.model.Student;
import ir.asta.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms/{classroomId}/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long classroomId, @PathVariable int studentId) {
        try {
            Student student = studentService.findById(classroomId, studentId);
            return ResponseEntity.status(200).body(student);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@PathVariable Long classroomId, @RequestBody CreateStudentRequest request) {
        try {
            Student student = studentService.create(classroomId, request);
            return ResponseEntity.status(201).body(student);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).build();
        } catch (InvalidScoreException exception) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long classroomId, @PathVariable Integer studentId) {
        try {
            studentService.delete(classroomId, studentId);
            return ResponseEntity.status(204).build();
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents(@PathVariable Long classroomId){
        try {
            List<Student> students = studentService.findAll(classroomId);
            return ResponseEntity.status(200).body(students);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).build();
        }
    }

    //GET /classrooms/{classroomId}/students?sort=score&includeRank=true
    @GetMapping(params = {"sort=score", "includeRank=true"})
    public ResponseEntity<?> showStudentsWithRank(@PathVariable Long classroomId, @RequestParam String sort, @RequestParam boolean includeRank) {
        try {
            List<RankedStudentResponse> students =
                studentService.sortStudents(classroomId);
            return ResponseEntity.status(200).body(students);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).build();
        }
    }
}
