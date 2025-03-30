package soheil.demo.start.service;

import org.springframework.stereotype.Service;
import soheil.demo.start.model.Professor;
import soheil.demo.start.repository.ProfessorRepository;
import soheil.demo.start.service.general_service.CrudService;


import java.util.List;

@Service
public class ProfessorService extends CrudService<Professor, String> {

    //repository declaration.
    private final ProfessorRepository professorRepository;

    //Constructor.
    //-------------------------------------------------------------------------------
    public ProfessorService(ProfessorRepository professorRepository) {
        super(professorRepository);
        this.professorRepository = professorRepository;
    }
    //-------------------------------------------------------------------------------

    //Methods.
    //-------------------------------------------------------------------------------
//    public List<Professor> getAllProfessors() {
//        return professorRepository.findAll();
//    }
//
//    public Professor getProfessorById(long id) {
//        if (professorRepository.findById(id).isPresent()) {
//            return professorRepository.findById(id).get();
//        }
//        return null;
//    }
//
//    public Professor addProfessor(Professor professor) {
//        return professorRepository.save(professor);
//    }
//
//    public Professor updateprofessor(Professor professor, long id) {
//        if (professorRepository.findById(id).isPresent()) {
//            Professor professor1 = professorRepository.findById(id).get();
//
//            professor1.setName(professor.getName());
//            professor1.setLast_name(professor.getLast_name());
//            professor1.setProfessor_id_number(professor.getProfessor_id_number());
//
//            return professorRepository.save(professor1);
//        }
//        return null;
//    }
    //-------------------------------------------------------------------------------
}
