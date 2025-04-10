package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.model.Faculty;
import soheil.demo.start.service.FacultyService;
import soheil.demo.start.service.general_interface.GeneralInterface;

import java.util.List;

@RestController("/api/faculties")
public class FacultyController {

    //Service declaration.
    //-------------------------------------------------------------------------------
    private final GeneralInterface<Faculty> facultyService;
    private final FacultyService facultyService2;
    //-------------------------------------------------------------------------------

    //Constructor.
    //-------------------------------------------------------------------------------
    public FacultyController(GeneralInterface<Faculty> facultyService,
                             FacultyService facultyService2)
    {
        this.facultyService = facultyService;
        this.facultyService2 = facultyService2;
    }
    //-------------------------------------------------------------------------------

    //End-Points.
    //-------------------------------------------------------------------------------
    @GetMapping("/details/{name}")
    public ResponseEntity<String> getFaculty(@PathVariable String name) {
        if (facultyService.isPresent(name)) {
            return ResponseEntity.ok(facultyService.get(name).toString());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<String> getFaculties() {
        String response = facultyService.getAll();
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyService.getAll());
    }

    @PostMapping("/create/faculty/{facultyName}/{universityName}")
    public ResponseEntity<String> createFaculty(@PathVariable String facultyName,
                                                @PathVariable String universityName) {
        String response = facultyService.add(facultyName, universityName);
        if (!response.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("faculty already exists !");
    }

    @PostMapping("/create/faculty/{facultyNames}/{universityName}")
    public ResponseEntity<String> createFaculties(@PathVariable List<String> facultyNames,
                                                  @PathVariable String universityName) {
        String response = facultyService.addMultiple(facultyNames, universityName);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("University names already exist !");
    }

    @GetMapping("/is-present/{facultyName}")
    public ResponseEntity<String> isPresent(@PathVariable String facultyName) {
        if (facultyService.isPresent(facultyName)) {
            return ResponseEntity.ok().body(facultyService.get(facultyName).toString());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/faculty/{facultyName}")
    public ResponseEntity<String> deleteFaculty(@PathVariable String facultyName) {
        if (facultyService.isPresent(facultyName)) {
            facultyService.remove(facultyName);
            return ResponseEntity.ok("Faculty " + facultyName + " is deleted !");
        }
        return ResponseEntity.badRequest().body("Faculty " + facultyName + " Not Found !");
    }

    @PostMapping("/default-creation/faculty/{facultyName}")
    public ResponseEntity<String> createDefaultFaculty(@PathVariable String facultyName) {
        Faculty faculty = new Faculty(facultyName);
        if (facultyService2.createFaculty(faculty)) {
            return ResponseEntity.ok("Faculty " + facultyName + " is Created !");
        }
        return ResponseEntity.badRequest().body("Faculty " + facultyName + " already exists !");
    }
    //-------------------------------------------------------------------------------
}
