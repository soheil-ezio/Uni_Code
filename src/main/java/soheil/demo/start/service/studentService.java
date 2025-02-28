package soheil.demo.start.service;

import org.springframework.stereotype.Service;
import soheil.demo.start.model.student;
import soheil.demo.start.model.studentRepository;

import java.util.List;

@Service
public class studentService {

    //Repository declaration.
    private final studentRepository studentRepository;

    //Constructor.
    //-------------------------------------------------------------------------------
    studentService(studentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    //-------------------------------------------------------------------------------

    //Methods.
    //-------------------------------------------------------------------------------
    public List<student> getAllStudents() {
        return studentRepository.findAll();
    }

    public student getStudentById(long id) {
        if (studentRepository.findById(id).isPresent()) {
            return studentRepository.findById(id).get();
        }
        return null;
    }

    public student addStudent(student student) {
        return studentRepository.save(student);
    }

    public student updateStudent(student student, long id) {
        if (studentRepository.findById(id).isPresent()) {
            student student1 = studentRepository.findById(id).get();

            student1.setName(student.getName());
            student1.setLast_name(student.getLast_name());
            student1.setStudent_id_number(student.getStudent_id_number());

            return studentRepository.save(student1);
        }
        return null;
    }
    //-------------------------------------------------------------------------------
}
