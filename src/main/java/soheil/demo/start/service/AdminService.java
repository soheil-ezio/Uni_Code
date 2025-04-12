package soheil.demo.start.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import soheil.demo.start.DTO.DtoMapper;
import soheil.demo.start.DTO.UserDTO;
import soheil.demo.start.model.*;
import soheil.demo.start.repository.MarkCourseStudentRepository;


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
    private final MarkCourseStudentRepository markCourseStudentRepository;
    private final DtoMapper dtoMapper;
    //-------------------------------------------------------------------------------

    //Constructor
    //-------------------------------------------------------------------------------
    public AdminService(StudentService studentService,
                        ProfessorService professorService,
                        UniversityService universityService,
                        FacultyService facultyService,
                        CourseService courseService,
                        MarkCourseStudentRepository markCourseStudentRepository,
                        DtoMapper dtoMapper)
    {
        this.studentService = studentService;
        this.professorService = professorService;
        this.universityService = universityService;
        this.facultyService = facultyService;
        this.courseService = courseService;
        this.markCourseStudentRepository = markCourseStudentRepository;
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

//    public ResponseEntity<String> addCourses(HashMap<String, Short> courses) {
//        for (String courseName : courses.keySet()) {
//            if (courseService.isPresent(courseName)) {
//                courses.remove(courseName);
//            }
//        }
//        if (courses.isEmpty()) {
//            return ResponseEntity.badRequest().body("Courses already exist !");
//        }else {
//            for (String courseName : courses.keySet()) {
//                courseService.addCourse(courseName, (short) courses.get(courseName));
//            }
//            return ResponseEntity.ok("Courses created successfully :\n" + courses);
//        }
//    }
//
//    public ResponseEntity<String> addCourse(String name, short credit) {
//        if (courseService.isPresent(name)) {
//            return ResponseEntity.badRequest().body("Course already exists !");
//        }
//        courseService.addCourse(name, credit);
//        return ResponseEntity.ok("Course created successfully :\n" + name);
//    }
    //-------------------------------------------------------------------------------

    //Required methods.
    //-------------------------------------------------------------------------------
    public String AverageMarkOfFaculty(String facultyName) {
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
                return "No marks available !";
            }
            OptionalDouble average = marks.stream().mapToInt(Short::intValue).average();
            return "Average of marks of the course: " + average.toString();
        }else {
            return "Faculty does not exist !";
        }
    }

    public String averageMarkOfCourse(String courseName, short credit) {
        if (courseService.isPresent(courseName)) {
            Course course = courseService.get(courseName);
            List<MarkCourseStudent> markCourseStudents = course.getMarkCourseStudents();
            if (!markCourseStudents.isEmpty()) {
                OptionalDouble average = markCourseStudents.stream().map(MarkCourseStudent::getMark)
                        .filter(Objects::nonNull)
                        .mapToInt(Short::intValue)
                        .average();
                if (average.isEmpty()) {
                    return "No marks available !";
                }
                return "Average of marks of the course: " + average;
            }else {
                return "Enrollment does not exist !";
            }
        }else
            return "Course does not exist !";
    }

    public String averageMarkOfStudent(String studentUserName) {
        if (studentService.findById(studentUserName).isPresent()) {
            Student student = studentService.findById(studentUserName).get();

            List<MarkCourseStudent> markCourseStudents = student.getMarkCourseStudents();
            if (!markCourseStudents.isEmpty()) {
                OptionalDouble average = markCourseStudents.stream().map(MarkCourseStudent::getMark)
                        .filter(Objects::nonNull)
                        .mapToInt(Short::intValue).average();
                if (average.isEmpty()) {
                    return "No marks available !";
                }
                return "Average of marks of the student: " + average;
            }else {
                return "No Enrollment available !";
            }
        }else
            return "Student does not exist !";
    }

    public String setCourseForStudent(String studentUserName, String courseName) {
        Student student;
        Course course;
        if (studentService.findById(studentUserName).isPresent()) {
            student = studentService.findById(studentUserName).get();
        }else {
            return "Student does not exist !";
        }

        if (courseService.isPresent(courseName)) {
            course = courseService.get(courseName);
        }else {
            return "Course does not exist !";
        }

        if (student.getMarkCourseStudents()
                .stream()
                .anyMatch(match
                -> match
                .getCourse()
                .getName()
                .equals(courseName)))
        {
            return "Student already is a part of this course";
        }else {
            MarkCourseStudent markCourseStudent = new MarkCourseStudent(course, student);
            markCourseStudentRepository.save(markCourseStudent);
            return "student : " + student.getName() +
                    " " + student.getLast_name() +
                    "\nis taking part in course : " + course.getName();
        }
    }

    public String setMarkForStudent(String studentUserName, String courseName, short mark) {
        // Setting a Mark for a Student in a specific Course.
        Student student;
        Course course;
        if (studentService.findById(studentUserName).isPresent()) {
            student = studentService.findById(studentUserName).get();
        }else  {
            return "Student does not exist !";
        }
        if (courseService.isPresent(courseName)) {
            course = courseService.get(courseName);
        }else {
            return "Course does not exist !";
        }

        MarkCourseStudent markCourseStudent = student.getMarkCourseStudents()
                .stream().filter(
                        match ->
                                match
                                        .getCourse()
                                        .getName()
                                        .equals(courseName)
                )
                .findFirst()
                .orElse(null);

        if (markCourseStudent == null) {
            markCourseStudent = new MarkCourseStudent(course, student, mark);
        }else {
            markCourseStudent.setMark(mark);
        }

        markCourseStudentRepository.save(markCourseStudent);
        return "mark : " + mark +
                " of course: " +
                course.getName() +
                "is know set for student : " + student.getName();
    }

    public String setCourseForProfessor(String professorName, String courseName) {
        // Setting Course for a Professor.
    }
    //-------------------------------------------------------------------------------


}
