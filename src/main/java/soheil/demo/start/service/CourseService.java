package soheil.demo.start.service;

import org.springframework.stereotype.Service;
import soheil.demo.start.model.Course;
import soheil.demo.start.repository.CourseRepository;

import java.util.HashMap;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final FacultyService facultyService;

    public CourseService(CourseRepository courseRepository,
                         FacultyService facultyService)
    {
        this.courseRepository = courseRepository;
        this.facultyService = facultyService;
    }

    public void addCourse(String courseName, short credit) {
        courseRepository.save(new Course(courseName, credit));
    }

    public void addCourses(HashMap<String, Course> courses) {
        courseRepository.saveAll(courses.values());
    }

    public void deleteCourse(String courseName) {
        if (courseRepository.findById(courseName).isPresent()) {
            courseRepository.deleteById(courseName);
        }
    }

    public Course getCourse(String courseName, short credit) {
        return courseRepository.findById(courseName).get();
    }

    public List<Course> getAllCourses() {
            return courseRepository.findAll();
    }

    public boolean isPresent(String name) {
        return courseRepository.findById(name).isPresent();
    }

    public void setFaculty(String courseName, String facultyName) {
        courseRepository.findById(courseName).ifPresent(
                course -> {
                    course.setFaculty(facultyService.getFaculty(facultyName));
                    courseRepository.save(course);
                }
        );
    }
}
