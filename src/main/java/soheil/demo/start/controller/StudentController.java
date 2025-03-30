package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.DTO.DtoMapper;
import soheil.demo.start.DTO.UserDTO;
import soheil.demo.start.model.Student;
import soheil.demo.start.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    //Service declaration.
    //-------------------------------------------------------------------------------
    private final StudentService studentService;
    private final DtoMapper dtoMapper;

    //Constructor.
    //-------------------------------------------------------------------------------
    public StudentController(StudentService studentService,
                             DtoMapper dtoMapper)
    {
        this.studentService = studentService;
        this.dtoMapper = dtoMapper;
    }
    //-------------------------------------------------------------------------------

    //End-Points. ( <C-R-U-D> End-points )
    //-------------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllStudents() {
        if (studentService.findAll() == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok
                (
                        studentService
                                .findAll()
                                .stream()
                                .map(dtoMapper::studentToUserDTO)
                                .toList()
                );
    }

    @GetMapping("/details/{username}")
    public ResponseEntity<UserDTO> findStudent(@PathVariable String username) {
        if (studentService.findById(username).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dtoMapper.studentToUserDTO(studentService.findById(username).get()));
    }

    @PostMapping("/create")
    public ResponseEntity<Student> addStudent(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(studentService.add(dtoMapper.userDTOToStudent(userDTO)));
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<UserDTO> updateStudent(@RequestBody UserDTO userDTO, @PathVariable String username) {
        if (studentService.findById(username).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dtoMapper
                .studentToUserDTO(studentService.update(dtoMapper.userDTOToStudent(userDTO), username)));
    }
    //-------------------------------------------------------------------------------
}
