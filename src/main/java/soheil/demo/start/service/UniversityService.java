package soheil.demo.start.service;

import org.springframework.stereotype.Service;
import soheil.demo.start.repository.UniversityRepository;
import soheil.demo.start.model.University;
import soheil.demo.start.service.general_service.CrudService;

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
    public University getUniversity(String name) {
        if (universityRepository.findById(name).isPresent()) {
            return universityRepository.findById(name).get();
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

    public void addUniversities(List<University> universities) {
        universityRepository.saveAll(universities);
    }

    public boolean isPresent(String name) {
        return universityRepository.findById(name).isPresent();
    }

    public void removeUniversity(String name) {
        universityRepository.deleteById(name);
    }
    //-------------------------------------------------------------------------------
}
