package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.model.Professor;
import soheil.demo.start.service.ProfessorService;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    //Service declaration.
    //-------------------------------------------------------------------------------
    private final ProfessorService PROFESSOR_SERVICE;
    
    //Constructor.
    //-------------------------------------------------------------------------------
    public ProfessorController(ProfessorService PROFESSOR_SERVICE) {
        this.PROFESSOR_SERVICE = PROFESSOR_SERVICE;
    }
    //-------------------------------------------------------------------------------

    //End-Points. ( <C-R-U-D> End-points )
    //-------------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<Professor>> getAllProfessors() {
        if (PROFESSOR_SERVICE.getAllProfessors() == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(PROFESSOR_SERVICE.getAllProfessors());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Professor> findProfessor(@PathVariable long id) {
        if (PROFESSOR_SERVICE.getProfessorById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PROFESSOR_SERVICE.getProfessorById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Professor> addProfessor(@RequestBody Professor professor) {
        return ResponseEntity.ok(PROFESSOR_SERVICE.addProfessor(professor));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Professor> updateProfessor(@RequestBody Professor professor, @PathVariable long id) {
        if (PROFESSOR_SERVICE.getProfessorById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PROFESSOR_SERVICE.updateprofessor(professor, id));
    }
    //-------------------------------------------------------------------------------
}
