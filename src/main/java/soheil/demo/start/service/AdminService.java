package soheil.demo.start.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import soheil.demo.start.DTO.DtoMapper;
import soheil.demo.start.DTO.UserDTO;
import soheil.demo.start.model.Professor;
import soheil.demo.start.model.Student;
import soheil.demo.start.repository.FacultyRepository;
import soheil.demo.start.repository.UniversityRepository;

@Service
public class AdminService {


    //Service & repository declaration.
    //-------------------------------------------------------------------------------
    private final StudentService studentService;
    private final ProfessorService professorService;
    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;
    private final DtoMapper dtoMapper;
    //-------------------------------------------------------------------------------

    //Constructor
    //-------------------------------------------------------------------------------
    public AdminService(StudentService studentService,
                        ProfessorService professorService,
                        UniversityRepository universityRepository,
                        FacultyRepository facultyRepository,
                        DtoMapper dtoMapper)
    {
        this.studentService = studentService;
        this.professorService = professorService;
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
        this.dtoMapper = dtoMapper;
    }
    //-------------------------------------------------------------------------------

    //Method.
    //-------------------------------------------------------------------------------
    public ResponseEntity<String> creatUser (UserDTO userDTO) {
        if (userDTO.getRole().equalsIgnoreCase("STUDENT")) {
            if (studentService.findById(userDTO.getUsername()).isPresent()) {

                return ResponseEntity.badRequest().body("Student already exists !");

            }else {

                Student student = dtoMapper.userDTOToStudent(userDTO);
                student.setUniversity(universityRepository.getReferenceById(userDTO.getUniversityId()));
                student.setFaculty(facultyRepository.getReferenceById(userDTO.getFacultyId()));
                studentService.add(student);

                return ResponseEntity.ok("User created successfully :\n" + studentService.findById(userDTO.getUsername()).get());
            }

        } else if (userDTO.getRole().equalsIgnoreCase("PROFESSOR")) {
            if (professorService.findById(userDTO.getUsername()).isPresent()) {

                return ResponseEntity.badRequest().body("Professor already exists !");

            }else {

                Professor professor = dtoMapper.userDTOToProfessor(userDTO);
                professor.setUniversity(universityRepository.getReferenceById(userDTO.getUniversityId()));
                professor.setFaculty(facultyRepository.getReferenceById(userDTO.getFacultyId()));
                professorService.add(professor);

                return ResponseEntity.ok("User created successfully :\n" + professorService.findById(userDTO.getUsername()).get());
            }
        }else
            return ResponseEntity.badRequest().body("Unrecognized entries !");
    }
    //-------------------------------------------------------------------------------


}
