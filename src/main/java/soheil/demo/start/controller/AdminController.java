package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.DTO.UserDTO;
import soheil.demo.start.service.AdminService;


import java.util.List;

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
    //-------------------------------------------------------------------------------
}
