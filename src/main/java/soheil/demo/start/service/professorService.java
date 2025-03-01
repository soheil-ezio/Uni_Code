package soheil.demo.start.service;

import org.springframework.stereotype.Service;
import soheil.demo.start.model.professor;
import soheil.demo.start.Repository.professorRepository;


import java.util.List;

@Service
public class professorService {

    //Repository declaration.
    private final professorRepository professorRepository;

    //Constructor.
    //-------------------------------------------------------------------------------
    professorService(professorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
    //-------------------------------------------------------------------------------

    //Methods.
    //-------------------------------------------------------------------------------
    public List<professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    public professor getProfessorById(long id) {
        if (professorRepository.findById(id).isPresent()) {
            return professorRepository.findById(id).get();
        }
        return null;
    }

    public professor addProfessor(professor professor) {
        return professorRepository.save(professor);
    }

    public professor updateprofessor(professor professor, long id) {
        if (professorRepository.findById(id).isPresent()) {
            professor professor1 = professorRepository.findById(id).get();

            professor1.setName(professor.getName());
            professor1.setLast_name(professor.getLast_name());
            professor1.setProfessor_id_number(professor.getProfessor_id_number());

            return professorRepository.save(professor1);
        }
        return null;
    }
    //-------------------------------------------------------------------------------
}
