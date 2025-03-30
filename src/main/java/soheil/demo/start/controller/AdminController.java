package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.DTO.UserDTO;
import soheil.demo.start.model.University;
import soheil.demo.start.service.AdminService;
import soheil.demo.start.service.UniversityService;

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

    @PostMapping("/create/university")
    public ResponseEntity<University> addUniversity(@RequestBody University university) {
        return ResponseEntity.ok(universityService.addUniversity(university));
    }

    @PostMapping("/create/user")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        return adminService.creatUser(userDTO);
    }
    //-------------------------------------------------------------------------------
}
