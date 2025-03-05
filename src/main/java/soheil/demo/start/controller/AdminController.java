package soheil.demo.start.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.DTO.UserDTO;
import soheil.demo.start.repository.UniversityRepository;
import soheil.demo.start.model.Professor;
import soheil.demo.start.model.Student;
import soheil.demo.start.model.University;
import soheil.demo.start.service.ProfessorService;
import soheil.demo.start.service.UniversityService;
import soheil.demo.start.service.StudentService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    //Service & repository declaration.
    //-------------------------------------------------------------------------------
    private final UniversityService UNIVERSITY_SERVICE;
    private final StudentService STUDENT_SERVICE;
    private final ProfessorService PROFESSOR_SERVICE;
    private final UniversityRepository UNIVERSITY_REPOSITORY;

    //Constructor.
    //-------------------------------------------------------------------------------
    public AdminController(UniversityService UNIVERSITY_SERVICE,
                           StudentService STUDENT_SERVICE,
                           ProfessorService PROFESSOR_SERVICE,
                           UniversityRepository UNIVERSITY_REPOSITORY)
    {
        this.UNIVERSITY_SERVICE = UNIVERSITY_SERVICE;
        this.STUDENT_SERVICE = STUDENT_SERVICE;
        this.PROFESSOR_SERVICE = PROFESSOR_SERVICE;
        this.UNIVERSITY_REPOSITORY = UNIVERSITY_REPOSITORY;
    }
    //-------------------------------------------------------------------------------

    //Methods.
    //-------------------------------------------------------------------------------
    @GetMapping("/welcome")
    public ResponseEntity<?> welcome() {
        return ResponseEntity.ok("Welcome");
    }

    @PostMapping("/create/university")
    public ResponseEntity<University> addUniversity(@RequestBody University university) {
        return ResponseEntity.ok(UNIVERSITY_SERVICE.addUniversity(university));
    }

    @PostMapping("/create/user")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getRole().equalsIgnoreCase("STUDENT")) {
            Student student = new Student(
                    userDTO.getName(),
                    userDTO.getLast_Name(),
                    userDTO.getStudent_id_number(),
                    userDTO.getUsername(),
                    userDTO.getPassword(),
                    "STUDENT"
            );
            student.setUniversity(UNIVERSITY_REPOSITORY.getReferenceById(userDTO.getUniversityId()));
            STUDENT_SERVICE.addStudent(student);

            return ResponseEntity.ok("User created successfully" + "\nUsername:" + userDTO.getUsername());
        } else if (userDTO.getRole().equalsIgnoreCase("PROFESSOR")) {
            Professor professor = new Professor(
                    userDTO.getName(),
                    userDTO.getLast_Name(),
                    userDTO.getProfessor_id_number(),
                    userDTO.getUsername(),
                    userDTO.getPassword(),
                    "PROFESSOR"
            );
            professor.setUniversity(UNIVERSITY_REPOSITORY.getReferenceById(userDTO.getUniversityId()));
            PROFESSOR_SERVICE.addProfessor(professor);
            return ResponseEntity.ok("User created successfully" + "\nUsername:" + userDTO.getUsername());
        }else
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
    //-------------------------------------------------------------------------------
}
