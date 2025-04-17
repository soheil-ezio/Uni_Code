package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.DTO.DtoMapper;
import soheil.demo.start.DTO.UserDTO;
import soheil.demo.start.model.Professor;
import soheil.demo.start.service.ProfessorService;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    //Service declaration.
    //-------------------------------------------------------------------------------
    private final ProfessorService professorService;
    private final DtoMapper dtoMapper;
    
    //Constructor.
    //-------------------------------------------------------------------------------
    public ProfessorController(ProfessorService professorService,
                               DtoMapper dtoMapper)
    {
        this.professorService = professorService;
        this.dtoMapper = dtoMapper;
    }
    //-------------------------------------------------------------------------------

    //End-Points. ( <C-R-U-D> End-points )
    //-------------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllProfessors() {
        if (professorService.findAll() == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok
                (
                        professorService
                                .findAll()
                                .stream()
                                .map(dtoMapper::professorToUserDTO)
                                .toList()
                );
    }

    @GetMapping("/details/{username}")
    public ResponseEntity<UserDTO> findProfessor(@PathVariable String username) {
        if (professorService.findById(username).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dtoMapper.professorToUserDTO(professorService.findById(username).get()));
    }

    @PostMapping("/create")
    public ResponseEntity<Professor> addProfessor(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(professorService.add(dtoMapper.userDTOToProfessor(userDTO)));
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<UserDTO> updateProfessor(@RequestBody UserDTO userDTO, @PathVariable String username) {
        if (professorService.findById(username).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(
                dtoMapper.professorToUserDTO(professorService.update(dtoMapper.userDTOToProfessor(userDTO), username)));
    }
    //-------------------------------------------------------------------------------
}
