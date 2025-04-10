package soheil.demo.start.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import soheil.demo.start.model.Course;
import soheil.demo.start.service.CourseService;

import java.util.HashMap;

@Controller("/api/courses")
public class CourseController {

    //Service declaration.
    //-------------------------------------------------------------------------------
    private final CourseService courseService;
    //-------------------------------------------------------------------------------

    //Constructor.
    //-------------------------------------------------------------------------------
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    //-------------------------------------------------------------------------------

    //End-Points.
    //-------------------------------------------------------------------------------
    @GetMapping("/details/{name}")
    public ResponseEntity<String> getFaculty(@PathVariable String name) {
        if (courseService.isPresent(name)) {
            return ResponseEntity.ok(courseService.get(name).toString());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<String> getFaculties() {
        String response = courseService.getAll();
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courseService.getAll());
    }

    @PostMapping("/create/course/{courseName}/{credit}")
    public ResponseEntity<String> createFaculty(@PathVariable String courseName,
                                                @PathVariable String credit) {
        String response = courseService.add(courseName, credit);
        if (!response.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("faculty already exists !");
    }

    @PostMapping("/create/course/{facultyName}")
    public ResponseEntity<String> createCourses(@RequestParam HashMap<String, Short> courseNames,
                                                @PathVariable String facultyName) {
        String response = courseService.addCourses(courseNames, facultyName);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("University names already exist !");
    }

    @GetMapping("/is-present/{courseName}")
    public ResponseEntity<String> isPresent(@PathVariable String courseName) {
        if (courseService.isPresent(courseName)) {
            return ResponseEntity.ok().body(courseService.get(courseName).toString());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/course/{courseName}")
    public ResponseEntity<String> deleteCourse(@PathVariable String courseName) {
        if (courseService.isPresent(courseName)) {
            courseService.remove(courseName);
            return ResponseEntity.ok("Course " + courseName + " is deleted !");
        }
        return ResponseEntity.badRequest().body("University " + courseName + " Not Found !");
    }

    @PostMapping("/default-creation/course/{courseName}/{credit}")
    public ResponseEntity<String> createDefaultCourse(@PathVariable String courseName,
                                                      @PathVariable String credit) {
        Course course = new Course(courseName, Short.parseShort(credit));
        if (courseService.creatCourse(course)) {
            return ResponseEntity.ok(course.toString());
        }
        return ResponseEntity.badRequest().body("Course " + courseName + " already exists !");
    }
    //-------------------------------------------------------------------------------
}
