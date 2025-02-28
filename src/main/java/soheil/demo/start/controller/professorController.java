package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.model.professor;
import soheil.demo.start.service.professorService;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class professorController {

    //Service declaration.
    //-------------------------------------------------------------------------------
    private final professorService professorService;
    
    //Constructor.
    //-------------------------------------------------------------------------------
    public professorController(professorService professorService) {
        this.professorService = professorService;
    }
    //-------------------------------------------------------------------------------

    //End-Points. ( <C-R-U-D> End-points )
    //-------------------------------------------------------------------------------
    @GetMapping("/professors-list")
    public ResponseEntity<List<professor>> getAllProfessors() {
        if (professorService.getAllProfessors() == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(professorService.getAllProfessors());
    }

    @GetMapping("/find-professor-{id}")
    public ResponseEntity<professor> findProfessor(@PathVariable long id) {
        if (professorService.getProfessorById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(professorService.getProfessorById(id));
    }

    @PostMapping("/add-professor")
    public ResponseEntity<professor> addProfessor(@RequestBody professor professor) {
        return ResponseEntity.ok(professorService.addprofessor(professor));
    }

    @PutMapping("/update-professor-{id}")
    public ResponseEntity<professor> updateProfessor(@RequestBody professor professor, @PathVariable long id) {
        if (professorService.getProfessorById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(professorService.updateprofessor(professor, id));
    }
    //-------------------------------------------------------------------------------
}
