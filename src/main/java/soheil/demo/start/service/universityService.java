package soheil.demo.start.service;

import org.springframework.stereotype.Service;
import soheil.demo.start.Repository.universityRepository;
import soheil.demo.start.model.university;

import java.util.List;

@Service
public class universityService {

    //Repository declaration.
    //-------------------------------------------------------------------------------
    private final universityRepository universityRepository;

    //Constructor.
    //-------------------------------------------------------------------------------
    public universityService(universityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }
    //-------------------------------------------------------------------------------

    //Methods.
    //-------------------------------------------------------------------------------
    public university getUniversity(long id) {
        if (universityRepository.findById(id).isPresent()) {
            return universityRepository.findById(id).get();
        }
        return null;
    }

    public List<university> getAllUniversities() {
        if (universityRepository.findAll().isEmpty()) {
            return null;
        }
        return universityRepository.findAll();
    }

    public university addUniversity(university university) {
        return universityRepository.save(university);
    }
    //-------------------------------------------------------------------------------
}
