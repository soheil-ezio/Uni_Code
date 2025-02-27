package soheil.demo.start.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.model.student;
import soheil.demo.start.model.studentRepository;
import soheil.demo.start.service.studentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class studentController {

    private final studentService studentService;

    public studentController(studentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students-list")
    ResponseEntity<List<student>> getAllStudents() {
        if (studentService.getAllStudents() == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/find-student-{id}")
    ResponseEntity<student> findStudent(@PathVariable long id) {
        if (studentService.getStudentById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("/add-student")
    ResponseEntity<student> addStudent(@RequestBody student student) {
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @PutMapping("/update-student-{id}")
    ResponseEntity<student> updateStudent(@RequestBody student student, @PathVariable long id) {
        if (studentService.getStudentById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.updateStudent(student, id));
    }

}
