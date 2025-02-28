package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.model.university;
import soheil.demo.start.service.universityService;

import java.util.List;

@RestController
@RequestMapping("/university")
public class universityController {

    //Service declaration.
    //-------------------------------------------------------------------------------
    private final universityService universityService;

    //Constructor.
    //-------------------------------------------------------------------------------
    public universityController(universityService universityService) {
        this.universityService = universityService;
    }
    //-------------------------------------------------------------------------------

    //End-Points. ( <C-R-U-D> End-points )
    //-------------------------------------------------------------------------------
    @GetMapping("/get-university-{id}")
    public ResponseEntity<university> getUniversity(@PathVariable Long id) {
        if (universityService.getUniversity(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(universityService.getUniversity(id));
    }

    @GetMapping("get-universities")
    public ResponseEntity<List<university>> getUniversities() {
        if (universityService.getAllUniversities() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(universityService.getAllUniversities());
    }

    @PostMapping("/add-university")
    public ResponseEntity<university> addUniversity(@RequestBody university university) {
        return ResponseEntity.ok(universityService.addUniversity(university));
    }
    //-------------------------------------------------------------------------------
}
