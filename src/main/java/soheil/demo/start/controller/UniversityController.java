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
    private final UniversityService UNIVERSITY_SERVICE;

    //Constructor.
    //-------------------------------------------------------------------------------
    public UniversityController(UniversityService UNIVERSITY_SERVICE) {
        this.UNIVERSITY_SERVICE = UNIVERSITY_SERVICE;
    }
    //-------------------------------------------------------------------------------

    //End-Points. ( <C-R-U-D> End-points )
    //-------------------------------------------------------------------------------
    @GetMapping("/details/{id}")
    public ResponseEntity<University> getUniversity(@PathVariable Long id) {
        if (UNIVERSITY_SERVICE.getUniversity(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UNIVERSITY_SERVICE.getUniversity(id));
    }

    @GetMapping
    public ResponseEntity<List<University>> getUniversities() {
        if (UNIVERSITY_SERVICE.getAllUniversities() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UNIVERSITY_SERVICE.getAllUniversities());
    }

    @PostMapping("/create")
    public ResponseEntity<University> addUniversity(@RequestBody University university) {
        return ResponseEntity.ok(UNIVERSITY_SERVICE.addUniversity(university));
    }
    //-------------------------------------------------------------------------------
}
