package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.model.University;
import soheil.demo.start.service.UniversityService;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    //Service declaration.
    //-------------------------------------------------------------------------------
    private final UniversityService universityService;

    //Constructor.
    //-------------------------------------------------------------------------------
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }
    //-------------------------------------------------------------------------------

    //End-Points. ( <C-R-U-D> End-points )
    //-------------------------------------------------------------------------------
    @GetMapping("/details/{name}")
    public ResponseEntity<String> getUniversity(@PathVariable String name) {
        if (universityService.isPresent(name)) {
            return ResponseEntity.ok(universityService.get(name).toString());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<String> getUniversities() {
        if (universityService.getAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(universityService.getAll());
    }

    @PostMapping("/create/university/{universityName}")
    public ResponseEntity<String> createUniversity(@PathVariable String universityName) {
        String response = universityService.add(universityName, null);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("University already exists !");
    }

    @PostMapping("/create/university/{universityNames}")
    public ResponseEntity<String> createUniversities(@PathVariable List<String> universityNames) {
        String response = universityService.addMultiple(universityNames, null);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("University names already exist !");
    }

    @DeleteMapping("/delete/university/{universityName}")
    public ResponseEntity<String> deleteUniversity(@PathVariable String universityName) {
        if (universityService.isPresent(universityName)) {
            universityService.remove(universityName);
            return ResponseEntity.ok("University " + universityName + " is deleted !");
        }
        return ResponseEntity.badRequest().body("University " + universityName + " Not Found !");
    }
    //-------------------------------------------------------------------------------
}
