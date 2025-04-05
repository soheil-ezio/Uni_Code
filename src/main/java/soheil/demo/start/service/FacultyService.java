package soheil.demo.start.service;

import org.springframework.stereotype.Service;
import soheil.demo.start.model.Faculty;
import soheil.demo.start.model.University;
import soheil.demo.start.repository.FacultyRepository;
import soheil.demo.start.repository.UniversityRepository;
import soheil.demo.start.service.general_service.CrudService;

import java.util.List;

@Service
public class FacultyService {

    //repository declaration.
    //-------------------------------------------------------------------------------
    private final FacultyRepository facultyRepository;
    private final UniversityService universityService;

    //Constructor.
    //-------------------------------------------------------------------------------
    public FacultyService(FacultyRepository facultyRepository,
                          UniversityService universityService)
    {
        this.facultyRepository = facultyRepository;
        this.universityService = universityService;
    }
    //-------------------------------------------------------------------------------

    //Methods.
    //-------------------------------------------------------------------------------
    public Faculty getFaculty(String name) {
        if (facultyRepository.findById(name).isPresent()) {
            return facultyRepository.findById(name).get();
        }
        return null;
    }

    public List<Faculty> getAllFaculties() {
        if (facultyRepository.findAll().isEmpty()) {
            return null;
        }
        return facultyRepository.findAll();
    }

    public void addFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    public void addFaculties(List<Faculty> faculties) {
        facultyRepository.saveAll(faculties);
    }

    public boolean isPresent(String name) {
        return facultyRepository.findById(name).isPresent();
    }

    public void removeFaculty(String name) {
        facultyRepository.deleteById(name);
    }

    public void setUniversity(String facultyName, String universityName) {
        facultyRepository.findById(facultyName).ifPresent(
                faculty -> {
                    faculty.setUniversity(universityService.getUniversity(universityName));
                    facultyRepository.save(faculty);
                }
        );
    }
    //-------------------------------------------------------------------------------

}
