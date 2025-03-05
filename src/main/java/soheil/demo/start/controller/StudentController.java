package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.model.Student;
import soheil.demo.start.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    //Service declaration.
    //-------------------------------------------------------------------------------
    private final StudentService STUDENT_SERVICE;

    //Constructor.
    //-------------------------------------------------------------------------------
    public StudentController(StudentService STUDENT_SERVICE) {
        this.STUDENT_SERVICE = STUDENT_SERVICE;
    }
    //-------------------------------------------------------------------------------

    //End-Points. ( <C-R-U-D> End-points )
    //-------------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        if (STUDENT_SERVICE.getAllStudents() == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(STUDENT_SERVICE.getAllStudents());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Student> findStudent(@PathVariable long id) {
        if (STUDENT_SERVICE.getStudentById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(STUDENT_SERVICE.getStudentById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(STUDENT_SERVICE.addStudent(student));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable long id) {
        if (STUDENT_SERVICE.getStudentById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(STUDENT_SERVICE.updateStudent(student, id));
    }
    //-------------------------------------------------------------------------------
}
