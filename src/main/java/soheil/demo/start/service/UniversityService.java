package soheil.demo.start.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import soheil.demo.start.repository.UniversityRepository;
import soheil.demo.start.model.University;
import soheil.demo.start.service.general_interface.GeneralInterface;

import java.util.ArrayList;
import java.util.List;

@Service("universityService")
@Transactional
public class UniversityService implements GeneralInterface<University> {

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
    public University get(String name) {
        if (universityRepository.findById(name).isPresent()) {
            return universityRepository.getReferenceById(name);
        }
        return null;
    }

    public String getAll() {
        if (universityRepository.findAll().isEmpty()) {
            return null;
        }
        return "Universities :\n" + universityRepository.findAll();
    }

    public String add(String universityName, String name) {
        if (isPresent(universityName)) {
            universityRepository.save(new University(universityName));
            return ("University created successfully :\n" + universityName);
        }
        return null;
    }

    public String addMultiple(List<String> universityNames, String name) {
        universityNames.removeIf(this::isPresent);
        if (universityNames.isEmpty()) {
            return null;
        }else {
            List<University> universities = new ArrayList<>();
            for (String universityName : universityNames) {
                universities.add(universityRepository.getReferenceById(universityName));
            }
            universityRepository.saveAll(universities);
            return ("Universities created successfully :\n" + universityNames);
        }
    }

    public boolean isPresent(String name) {
        return universityRepository.findById(name).isPresent();
    }

    public void remove(String name) {
        universityRepository.deleteById(name);
    }
    //-------------------------------------------------------------------------------
}
