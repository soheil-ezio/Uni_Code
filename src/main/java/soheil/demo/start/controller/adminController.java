package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soheil.demo.start.model.university;
import soheil.demo.start.service.universityService;

@RestController
@RequestMapping("/admin")
public class adminController {

    private final universityService universityService;

    public adminController(universityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping("add-university")
    public ResponseEntity<university> addUniversity(@RequestBody university university) {
        return ResponseEntity.ok(universityService.addUniversity(university));
    }
}
