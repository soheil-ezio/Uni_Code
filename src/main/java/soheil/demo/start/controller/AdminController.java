package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.DTO.UserDTO;
import soheil.demo.start.model.University;
import soheil.demo.start.service.AdminService;
import soheil.demo.start.service.UniversityService;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    //Service & repository declaration.
    //-------------------------------------------------------------------------------
    private final UniversityService universityService;
    private final AdminService adminService;

    //Constructor.
    //-------------------------------------------------------------------------------
    public AdminController(UniversityService universityService,
                           AdminService adminService)
    {
        this.universityService = universityService;
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

    @PostMapping("/create/university/{universityNames}")
    public ResponseEntity<String> createUniversity(@PathVariable List<String> universityNames) {
        return adminService.addUniversities(universityNames);
    }

    @PostMapping("/create/faculty/{facultyNames}/{universityName}")
    public ResponseEntity<String> createFaculty(@PathVariable List<String> facultyNames,
                                                @PathVariable String universityName)
    {
        return adminService.addFaculties(facultyNames, universityName);
    }
    //-------------------------------------------------------------------------------
}
