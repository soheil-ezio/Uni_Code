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
    public ResponseEntity<University> getUniversity(@PathVariable String name) {
        if (universityService.getUniversity(name) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(universityService.getUniversity(name));
    }

    @GetMapping
    public ResponseEntity<List<University>> getUniversities() {
        if (universityService.getAllUniversities() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(universityService.getAllUniversities());
    }

    @PostMapping("/create")
    public ResponseEntity<University> addUniversity(@RequestBody University university) {
        return ResponseEntity.ok(universityService.addUniversity(university));
    }
    //-------------------------------------------------------------------------------
}
