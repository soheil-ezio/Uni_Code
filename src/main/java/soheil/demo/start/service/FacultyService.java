package soheil.demo.start.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import soheil.demo.start.model.Faculty;
import soheil.demo.start.model.University;
import soheil.demo.start.repository.FacultyRepository;
import soheil.demo.start.service.general_interface.GeneralInterface;

import java.util.ArrayList;
import java.util.List;

@Service("facultyService")
@Transactional
public class FacultyService implements GeneralInterface<Faculty> {

    //repository declaration.
    //-------------------------------------------------------------------------------
    private final FacultyRepository facultyRepository;
    private final GeneralInterface<University> universityService;
    private final ProfessorService professorService;

    //Constructor.
    //-------------------------------------------------------------------------------
    public FacultyService(FacultyRepository facultyRepository,
                          @Qualifier("universityService") GeneralInterface<University> universityService,
                          ProfessorService professorService)
    {
        this.facultyRepository = facultyRepository;
        this.universityService = universityService;
        this.professorService = professorService;
    }
    //-------------------------------------------------------------------------------

    //Methods.
    //-------------------------------------------------------------------------------
    public Faculty get(String name) {
        if (facultyRepository.findById(name).isPresent()) {
            return facultyRepository.findById(name).get();
        }
        return null;
    }

    public String getAll() {
        String response =  facultyRepository.findAll().toString();
        if (response.isEmpty()) {
            return null;
        }
        return "Faculties : \n" + response;
    }

    public String add(String facultyName, String universityName) {
        if (isPresent(facultyName)) {
            return "Faculty already exists !";
        }
        Faculty faculty = new Faculty(facultyName);
        faculty.setUniversity(universityService.get(universityName));
        facultyRepository.save(faculty);
        return "Faculty created successfully :\n" + facultyName;
    }

    public String addMultiple(List<String> facultyNames, String universityName) {
        List<String> faculties = new ArrayList<>();
        if (universityService.isPresent(universityName)) {
            for (Faculty faculty : universityService.get(universityName).getFaculty()) {
                faculties.add(faculty.getName());
            }
        }else {
            return "university does not exist !";
        }
        faculties.removeIf(this::isPresent);
        if (faculties.isEmpty()) {
            return "Faculties already exist !";
        }else {
            List<Faculty> faculties1 = new ArrayList<>();
            for (String facultyName : faculties) {
                Faculty faculty = new Faculty(facultyName);
                faculty.setUniversity(universityService.get(universityName));
                faculties1.add(faculty);
            }
            facultyRepository.saveAll(faculties1);
            return "Faculties created successfully :\n" + faculties1;
        }
    }

    public boolean isPresent(String name) {
        return facultyRepository.findById(name).isPresent();
    }

    public void remove(String name) {
        facultyRepository.deleteById(name);
    }

    public void setUniversity(String facultyName, String universityName) {
        facultyRepository.findById(facultyName).ifPresent(
                faculty -> {
                    faculty.setUniversity(universityService.get(universityName));
                    facultyRepository.save(faculty);
                }
        );
    }

    public String setFacultyHead(String facultyName, String ProfessorUserName) {
        if (isPresent(facultyName)) {
            Faculty faculty = get(facultyName);
            if (professorService.findById(ProfessorUserName).isPresent()) {
                faculty.setFacultyHeadProfessor(professorService.findById(ProfessorUserName).get());
                facultyRepository.save(faculty);
                return "Faculty : " + facultyName + "\nHead professor : " + facultyName;
            }else
                return "The Professor does not exist !";
        }else
            return "Faculty does not exist !";
    }
    //-------------------------------------------------------------------------------

    //Default Creation Method !.
    public boolean createFaculty(Faculty faculty) {
        if (isPresent(faculty.getName())) {
            return false;
        }
        facultyRepository.save(faculty);
        return true;
    }
}
