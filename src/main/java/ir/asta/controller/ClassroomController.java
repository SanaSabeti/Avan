package ir.asta.controller;

import ir.asta.exception.ResourceNotFoundException;
import ir.asta.dto.CurveStudentsRequest;
import ir.asta.model.Classroom;
import ir.asta.service.ClassroomService;
import ir.asta.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;
    private final StudentService studentService;

    public ClassroomController(ClassroomService classroomService, StudentService studentService) {
        this.classroomService = classroomService;
        this.studentService = studentService;
    }

    @GetMapping("{classroomId}")
    public ResponseEntity<Classroom> getClassroom(@PathVariable Long classroomId) {
        try {
            Classroom classroom = classroomService.findById(classroomId);
            return ResponseEntity.status(200).body(classroom);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping
    public ResponseEntity<Classroom> createClassroom() {
        Classroom classroom = classroomService.create();
        return ResponseEntity.status(201).body(classroom);
    }

    @DeleteMapping("/{classroomId}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long classroomId) {
        try {
            classroomService.delete(classroomId);
        }catch (ResourceNotFoundException exception){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/{classroomId}/score-curves")
    public ResponseEntity<Void> curveStudents(@PathVariable Long classroomId, @RequestBody CurveStudentsRequest request) {
        try {
            studentService.curveStudents(classroomId, request);
            return ResponseEntity.status(204).build();

        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).build();
        }
    }
}
