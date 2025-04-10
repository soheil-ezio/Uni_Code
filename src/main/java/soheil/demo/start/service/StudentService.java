package soheil.demo.start.service;

import org.springframework.stereotype.Service;
import soheil.demo.start.model.Student;
import soheil.demo.start.repository.StudentRepository;
import soheil.demo.start.service.general_service.CrudService;

@Service
public class StudentService extends CrudService<Student, String> {

    private final StudentRepository studentRepository;

    //Constructor.
    //-------------------------------------------------------------------------------
    public StudentService(StudentRepository studentRepository) {
        super(studentRepository);
        this.studentRepository = studentRepository;
    }
    //-------------------------------------------------------------------------------

    //Methods.
    //-------------------------------------------------------------------------------
//    public List<Student> getAllStudents() {
//        return repository.findAll();
//    }
//
//    public Student getStudentById(long id) {
//        if (studentRepository.findById(id).isPresent()) {
//            return studentRepository.findById(id).get();
//        }
//        return null;
//    }
//
//    public Student addStudent(Student student) {
//        return studentRepository.save(student);
//    }
//
//    public Student updateStudent(Student student, long id) {
//        if (studentRepository.findById(id).isPresent()) {
//            Student student1 = studentRepository.findById(id).get();
//
//            student1.setName(student.getName());
//            student1.setLast_name(student.getLast_name());
//            student1.setStudent_id_number(student.getStudent_id_number());
//
//            return studentRepository.save(student1);
//        }
//        return null;
//    }

//    public void setUniversity(Student Student) {
//        st
//    }
    //-------------------------------------------------------------------------------
}
