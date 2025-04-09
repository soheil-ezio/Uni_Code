package soheil.demo.start.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import soheil.demo.start.DTO.DtoMapper;
import soheil.demo.start.DTO.UserDTO;
import soheil.demo.start.model.*;


import java.util.*;

@Service
public class AdminService {


    //Service & repository declaration.
    //-------------------------------------------------------------------------------
    private final StudentService studentService;
    private final ProfessorService professorService;
    private final UniversityService universityService;
    private final FacultyService facultyService;
    private final CourseService courseService;
    private final DtoMapper dtoMapper;
    //-------------------------------------------------------------------------------

    //Constructor
    //-------------------------------------------------------------------------------
    public AdminService(StudentService studentService,
                        ProfessorService professorService,
                        UniversityService universityService,
                        FacultyService facultyService,
                        CourseService courseService,
                        DtoMapper dtoMapper)
    {
        this.studentService = studentService;
        this.professorService = professorService;
        this.universityService = universityService;
        this.facultyService = facultyService;
        this.courseService = courseService;
        this.dtoMapper = dtoMapper;
    }
    //-------------------------------------------------------------------------------

    //Methods.
    //-------------------------------------------------------------------------------
    public ResponseEntity<String> creatUser (UserDTO userDTO) {
        if (userDTO.getRole().equalsIgnoreCase("STUDENT")) {
            if (studentService.findById(userDTO.getUsername()).isPresent()) {

                return ResponseEntity.badRequest().body("Student already exists !");

            }else {

                Student student = dtoMapper.userDTOToStudent(userDTO);
                student.setUniversity(universityService.get(userDTO.getUniversityName()));
                student.setFaculty(facultyService.get(userDTO.getFacultyName()));
                studentService.add(student);

                return ResponseEntity.ok("User created successfully :\n" + studentService.findById(userDTO.getUsername()).get());
            }

        } else if (userDTO.getRole().equalsIgnoreCase("PROFESSOR")) {
            if (professorService.findById(userDTO.getUsername()).isPresent()) {

                return ResponseEntity.badRequest().body("Professor already exists !");

            }else {

                Professor professor = dtoMapper.userDTOToProfessor(userDTO);
                professor.setUniversity(universityService.get(userDTO.getUniversityName()));
                professor.setFaculty(facultyService.get(userDTO.getFacultyName()));
                professorService.add(professor);

                return ResponseEntity.ok("User created successfully :\n" + professorService.findById(userDTO.getUsername()).get());
            }
        }else
            return ResponseEntity.badRequest().body("Unrecognized entries !");
    }

//    public String addUniversities(List<String> universityNames) {
//        universityNames.removeIf(name -> universityService.isPresent(name));
//        if (universityNames.isEmpty()) {
//            return "universities already exist !";
//        }else {
//            List<University> universities = new ArrayList<>();
//            for (String universityName : universityNames) {
//                universities.add(universityService.getUniversity(universityName));
//            }
//            universityService.addUniversities(universities);
//            return "Universities created successfully :\n" + universityNames;
//        }
//    }

//    public ResponseEntity<String> addUniversity(String universityName) {
//        if (universityService.isPresent(universityName)) {
//            return ResponseEntity.badRequest().body("University already exists !");
//        }
//        University university = new University(universityName);
//        universityService.addUniversity(university);
//        return ResponseEntity.ok("University created successfully :\n" + universityName);
//    }

//    public ResponseEntity<String> addFaculties(List<String> facultyNames, String universityName) {
//        List<String> faculties = new ArrayList<>();
//        if (universityService.isPresent(universityName)) {
//            for (Faculty faculty : universityService.get(universityName).getFaculty()) {
//                faculties.add(faculty.getName());
//            }
//        }else {
//            return ResponseEntity.badRequest().body("university does not exist !");
//        }
//        faculties.removeIf(name -> facultyService.isPresent(name));
//        if (faculties.isEmpty()) {
//            return ResponseEntity.badRequest().body("Faculties already exist !");
//        }else {
//            List<Faculty> faculties1 = new ArrayList<>();
//            for (String facultyName : faculties) {
//                faculties1.add(facultyService.getFaculty(facultyName));
//            }
//            facultyService.addFaculties(faculties1);
//            return ResponseEntity.ok().body("Faculties created successfully :\n" + faculties);
//        }
//    }
//
//    public ResponseEntity<String> addFaculty(String facultyName) {
//        if (facultyService.isPresent(facultyName)) {
//            return ResponseEntity.badRequest().body("Faculty already exists !");
//        }
//        Faculty faculty = new Faculty(facultyName);
//        facultyService.addFaculty(faculty);
//        return ResponseEntity.ok("Faculty created successfully :\n" + facultyName);
//    }

    public ResponseEntity<String> addCourses(HashMap<String, Short> courses) {
        for (String courseName : courses.keySet()) {
            if (courseService.isPresent(courseName)) {
                courses.remove(courseName);
            }
        }
        if (courses.isEmpty()) {
            return ResponseEntity.badRequest().body("Courses already exist !");
        }else {
            for (String courseName : courses.keySet()) {
                courseService.addCourse(courseName, (short) courses.get(courseName));
            }
            return ResponseEntity.ok("Courses created successfully :\n" + courses);
        }
    }

    public ResponseEntity<String> addCourse(String name, short credit) {
        if (courseService.isPresent(name)) {
            return ResponseEntity.badRequest().body("Course already exists !");
        }
        courseService.addCourse(name, credit);
        return ResponseEntity.ok("Course created successfully :\n" + name);
    }
    //-------------------------------------------------------------------------------

    //Required methods.
    //-------------------------------------------------------------------------------
    public ResponseEntity<String> AverageMarkOfFaculty(String facultyName) {
        if (facultyService.isPresent(facultyName)) {
            Faculty faculty = facultyService.get(facultyName);
            List<Short> marks = new ArrayList<>();

            if (!faculty.getCourses().isEmpty()) {
                for (Course course : faculty.getCourses()) {
                    if (!course.getMarkCourseStudents().isEmpty()) {
                        for (MarkCourseStudent markCourseStudent : course.getMarkCourseStudents()) {
                            if (markCourseStudent.getMark() != null) {
                                marks.add(markCourseStudent.getMark());
                            }
                        }
                    }
                }
            }
            if (marks.isEmpty()) {
                return ResponseEntity.badRequest().body("No marks available !");
            }
            OptionalDouble average = marks.stream().mapToInt(Short::intValue).average();
            return ResponseEntity.ok().body("Average of marks of the course: " + average.toString());
        }else {
            return ResponseEntity.badRequest().body("Faculty does not exist !");
        }
    }

    public ResponseEntity<String> averageMarkOfCourse(String courseName, short credit) {
        if (courseService.isPresent(courseName)) {
            Course course = courseService.getCourse(courseName, credit);
            List<MarkCourseStudent> markCourseStudents = course.getMarkCourseStudents();
            if (!markCourseStudents.isEmpty()) {
                OptionalDouble average = markCourseStudents.stream().map(MarkCourseStudent::getMark)
                        .filter(Objects::nonNull)
                        .mapToInt(Short::intValue)
                        .average();
                if (average.isEmpty()) {
                    return ResponseEntity.badRequest().body("No marks available !");
                }
                return ResponseEntity.ok().body("Average of marks of the course: " + average.toString());
            }else {
                return ResponseEntity.badRequest().body("Course does not exist !");
            }
        }else
            return ResponseEntity.badRequest().body("Course does not exist !");
    }

    public ResponseEntity<String> averageMarkOfStudent(String studentUserName) {
        if (studentService.findById(studentUserName).isPresent()) {
            Student student = studentService.findById(studentUserName).get();

            List<MarkCourseStudent> markCourseStudents = student.getMarkCourseStudents();
            if (!markCourseStudents.isEmpty()) {
                OptionalDouble average = markCourseStudents.stream().map(MarkCourseStudent::getMark)
                        .filter(Objects::nonNull)
                        .mapToInt(Short::intValue).average();
                if (average.isEmpty()) {
                    return ResponseEntity.badRequest().body("No marks available !");
                }
                return ResponseEntity.ok().body("Average of marks of the student: " + average.toString());
            }else {
                return ResponseEntity.badRequest().body("No marks available !");
            }
        }else
            return ResponseEntity.badRequest().body("Student does not exist !");
    }
    //-------------------------------------------------------------------------------


}
