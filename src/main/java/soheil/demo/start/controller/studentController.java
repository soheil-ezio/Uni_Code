package soheil.demo.start.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soheil.demo.start.service.studentService;

@RestController
@RequestMapping("/student")
public class studentController {

    private final studentService studentService;

    public studentController(studentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/hello")
    public String hello() {
        return studentService.sayHello();
    }

}
