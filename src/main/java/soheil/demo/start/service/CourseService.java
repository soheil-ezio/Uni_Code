package soheil.demo.start.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import soheil.demo.start.model.Course;
import soheil.demo.start.model.Faculty;
import soheil.demo.start.repository.CourseRepository;
import soheil.demo.start.service.general_interface.GeneralInterface;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CourseService {

    //Variable declaration.
    //-------------------------------------------------------------------------------
    private final CourseRepository courseRepository;
    private final GeneralInterface<Faculty> facultyService;
    //-------------------------------------------------------------------------------

    //Constructor.
    //-------------------------------------------------------------------------------
    public CourseService(CourseRepository courseRepository,
                         @Qualifier("facultyService") GeneralInterface<Faculty> facultyService)
    {
        this.courseRepository = courseRepository;
        this.facultyService = facultyService;
    }
    //-------------------------------------------------------------------------------

    //Methods.
    //-------------------------------------------------------------------------------
    public Course get(String courseName) {
        if (courseRepository.findById(courseName).isPresent()) {
            return courseRepository.getReferenceById(courseName);
        }
        return null;
    }

    public String getAll() {
        String response = courseRepository.findAll().toString();
        if (response.isEmpty()) {
            return null;
        }
        return "Course list :" + response;
    }

    public String add(String courseName, String credit) {
        if (isPresent(courseName)) {
            return "Course already exists !" + courseName;
        }
        Course course = new Course(courseName, Short.parseShort(credit));
        creatCourse(course);
        return "Course created successfully :\n" + courseName;
    }

    public String addMultiple(List<String> names, String name) {
        return "Nothing";
    }

    public String addCourses(HashMap<String, Short> courses, String facultyName) {
        for (String courseName : courses.keySet()) {
            if (isPresent(courseName)) {
                courses.remove(courseName);
            }
        }
        if (courses.isEmpty()) {
            return "Courses already exist !";
        }else {
            for (String courseName : courses.keySet()) {
                Course course = new Course(courseName, (short) courses.get(courseName));
                Faculty faculty = facultyService.get(facultyName);
                if (faculty.getUniversity() == null) {
                    return "Faculty does not belong to any University!";
                }
                course.setFaculty(facultyService.get(facultyName));
                courseRepository.save(new Course(courseName, (short) courses.get(courseName)));
            }
            return "Courses created successfully :\n" + courses;
        }
    }

    public void remove(String courseName) {
        if (courseRepository.findById(courseName).isPresent()) {
            courseRepository.deleteById(courseName);
        }
    }

    public boolean isPresent(String name) {
        return courseRepository.findById(name).isPresent();
    }

    public void setFaculty(String courseName, String facultyName) {
        courseRepository.findById(courseName).ifPresent(
                course -> {
                    course.setFaculty(facultyService.get(facultyName));
                    courseRepository.save(course);
                }
        );
    }
    //-------------------------------------------------------------------------------

    //DefaultCreationMethod.
    public boolean creatCourse(Course course) {
        if (isPresent(course.getName())) {
            return false;
        }
        courseRepository.save(course);
        return true;
    }
}
