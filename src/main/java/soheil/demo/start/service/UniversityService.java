package soheil.demo.start.service;

import org.springframework.stereotype.Service;
import soheil.demo.start.repository.UniversityRepository;
import soheil.demo.start.model.University;

import java.util.List;

@Service
public class UniversityService {

    //repository declaration.
    //-------------------------------------------------------------------------------
    private final UniversityRepository universityRepository;

    //Constructor.
    //-------------------------------------------------------------------------------
    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }
    //-------------------------------------------------------------------------------

    //Methods.
    //-------------------------------------------------------------------------------
    public University getUniversity(long id) {
        if (universityRepository.findById(id).isPresent()) {
            return universityRepository.findById(id).get();
        }
        return null;
    }

    public List<University> getAllUniversities() {
        if (universityRepository.findAll().isEmpty()) {
            return null;
        }
        return universityRepository.findAll();
    }

    public University addUniversity(University university) {
        return universityRepository.save(university);
    }
    //-------------------------------------------------------------------------------
}
