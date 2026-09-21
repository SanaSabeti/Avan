package ir.asta.controller;

import ir.asta.exception.ResourceNotFoundException;
import ir.asta.dto.CreateTeacherRequest;
import ir.asta.model.Teacher;
import ir.asta.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms/{classroomId}/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Long classroomId, @PathVariable int teacherId) {
        try {
            Teacher teacher = teacherService.findById(classroomId, teacherId);
            return ResponseEntity.status(200).body(teacher);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@PathVariable Long classroomId, @RequestBody CreateTeacherRequest request) {
        try {
            Teacher teacher = teacherService.create(classroomId, request);
            return ResponseEntity.status(201).body(teacher);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long classroomId, @PathVariable Integer teacherId) {
        try {
            teacherService.delete(classroomId, teacherId);
            return ResponseEntity.status(204).build();
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> findAllTeachers(@PathVariable Long classroomId){
        try {
            List<Teacher> teachers = teacherService.findAll(classroomId);
            return ResponseEntity.status(200).body(teachers);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(404).build();
        }
    }
}
