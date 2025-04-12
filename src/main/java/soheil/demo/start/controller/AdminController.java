package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.DTO.UserDTO;
import soheil.demo.start.service.AdminService;


import java.util.Objects;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    //Service & repository declaration.
    //-------------------------------------------------------------------------------
    private final AdminService adminService;

    //Constructor.
    //-------------------------------------------------------------------------------
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    //-------------------------------------------------------------------------------

    //Methods.
    //-------------------------------------------------------------------------------
    @GetMapping("/welcome")
    public ResponseEntity<?> welcome() {
        return ResponseEntity.ok("Welcome");
    }

    @PostMapping("/create/user")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        return adminService.creatUser(userDTO);
    }

    @GetMapping("/average-mark-of-faculty/{facultyName}")
    public ResponseEntity<String> averageMarkOfFaculty(@PathVariable String facultyName) {
        String response = adminService.AverageMarkOfFaculty(facultyName);
        if (Objects.equals(response, "No marks available !")) {
            return ResponseEntity.badRequest().body(response);
        } else if (response.equals("Faculty does not exist !")) {
            return ResponseEntity.badRequest().body(response);
        }else
            return ResponseEntity.ok(response);
    }

    @GetMapping("/average-mark-of-course/{courseName}/{credit}")
    public ResponseEntity<String> averageMarkOfCourse(@PathVariable String courseName, @PathVariable short credit) {
        String response = adminService.averageMarkOfCourse(courseName, credit);
        return switch (response) {
            case "No marks available !",
                 "Course does not exist !",
                 "Enrollment does not exist !"
                    -> ResponseEntity.badRequest().body(response);
            default -> ResponseEntity.ok(response);
        };
    }

    @GetMapping("/average-mark-of-student/{studentUserName}")
    public ResponseEntity<String> averageMarkOfStudent(@PathVariable String studentUserName) {
        String response = adminService.averageMarkOfStudent(studentUserName);
        return switch (response) {
            case "No marks available !",
                 "Student does not exist !",
                 "No Enrollment available !"
                    -> ResponseEntity.badRequest().body(response);
            default -> ResponseEntity.ok(response);
        };
    }

    @PutMapping("/set-course-for-student/{studentName}/{courseName}")
    public ResponseEntity<String> setCourseForStudent(@PathVariable String studentName,
                                                      @PathVariable String courseName)
    {
        String response = adminService.setCourseForStudent(studentName, courseName);
        return switch (response) {
            case "Student does not exist !",
                 "Course does not exist !",
                 "Student already is a part of this course"
                    -> ResponseEntity.badRequest().body(response);
            default -> ResponseEntity.ok(response);
        };
    }

    @PutMapping("/set-mark-for-student/{studentName}/{courseName}/{mark}")
    public ResponseEntity<String> setMarkForStudent(@PathVariable String studentName,
                                                    @PathVariable String courseName,
                                                    @PathVariable short mark)
    {
        String response = adminService.setMarkForStudent(studentName, courseName, mark);
        return switch (response) {
            case "Student does not exist !",
                 "Course does not exist !"
                    -> ResponseEntity.badRequest().body(response);
            default -> ResponseEntity.ok(response);
        };
    }

    @PutMapping("/set-course-for-professor/{professorUsername}/{courseName}")
    public ResponseEntity<String> setCourseForProfessor(@PathVariable String professorUsername,
                                                        @PathVariable String courseName)
    {
        // Setting a Course for Professor.
    }
    //-------------------------------------------------------------------------------
}
