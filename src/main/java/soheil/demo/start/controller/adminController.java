package soheil.demo.start.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soheil.demo.start.DTO.userDTO;
import soheil.demo.start.Repository.universityRepository;
import soheil.demo.start.model.professor;
import soheil.demo.start.model.student;
import soheil.demo.start.model.university;
import soheil.demo.start.service.professorService;
import soheil.demo.start.service.universityService;
import soheil.demo.start.service.studentService;

@RestController
@RequestMapping("/admin")
public class adminController {

    //Service & Repository declaration.
    //-------------------------------------------------------------------------------
    private final universityService universityService;
    private final studentService studentService;
    private final professorService professorService;
    private final universityRepository universityRepository;

    //Constructor.
    //-------------------------------------------------------------------------------
    public adminController(universityService universityService,
                           studentService studentService,
                           professorService professorService,
                           universityRepository universityRepository)
    {
        this.universityService = universityService;
        this.studentService = studentService;
        this.professorService = professorService;
        this.universityRepository = universityRepository;
    }
    //-------------------------------------------------------------------------------

    //Methods.
    //-------------------------------------------------------------------------------
    @PostMapping("/add-university")
    public ResponseEntity<university> addUniversity(@RequestBody university university) {
        return ResponseEntity.ok(universityService.addUniversity(university));
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody userDTO userDTO) {
        if (userDTO.getRole().equalsIgnoreCase("STUDENT")) {
            student student = new student(
                    userDTO.getName(),
                    userDTO.getLast_Name(),
                    userDTO.getStudent_id_number(),
                    userDTO.getUsername(),
                    userDTO.getPassword(),
                    "STUDENT"
            );
            student.setUniversity(universityRepository.getReferenceById(userDTO.getUniversityId()));
            studentService.addStudent(student);

            return ResponseEntity.ok("User created successfully" + "\nUsername:" + userDTO.getUsername());
        } else if (userDTO.getRole().equalsIgnoreCase("PROFESSOR")) {
            professor professor = new professor(
                    userDTO.getName(),
                    userDTO.getLast_Name(),
                    userDTO.getProfessor_id_number(),
                    userDTO.getUsername(),
                    userDTO.getPassword(),
                    "PROFESSOR"
            );
            professor.setUniversity(universityRepository.getReferenceById(userDTO.getUniversityId()));
            professorService.addProfessor(professor);
            return ResponseEntity.ok("User created successfully" + "\nUsername:" + userDTO.getUsername());
        }else
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
    //-------------------------------------------------------------------------------
}
