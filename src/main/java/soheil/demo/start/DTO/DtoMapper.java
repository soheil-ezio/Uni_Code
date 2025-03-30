package soheil.demo.start.DTO;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import soheil.demo.start.model.Professor;
import soheil.demo.start.model.Student;
import soheil.demo.start.model.User;

@Component
public class DtoMapper {

    private final PasswordEncoder passwordEncoder;

    DtoMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //Methods.
    //-------------------------------------------------------------------------------

    //Student Mapper.
    public UserDTO studentToUserDTO(Student student) {

        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(student.getUsername());
        userDTO.setPassword("Private !");
        userDTO.setRole(student.getRole());
        userDTO.setName(student.getName());
        userDTO.setLast_name(student.getLast_name());
        userDTO.setStudent_id_number(student.getStudent_id_number());

        return userDTO;
    }

    public Student userDTOToStudent(UserDTO userDTO) {

        return new Student(
                userDTO.getName(),
                userDTO.getLast_name(),
                userDTO.getStudent_id_number(),
                userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword()),
                "STUDENT"
        );
    }

    //Professor Mapper.
    public UserDTO professorToUserDTO(Professor professor) {

        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(professor.getUsername());
        userDTO.setPassword("Private !");
        userDTO.setRole(professor.getRole());
        userDTO.setName(professor.getName());
        userDTO.setLast_name(professor.getLast_name());
        userDTO.setStudent_id_number(professor.getProfessor_id_number());

        return userDTO;
    }

    public Professor userDTOToProfessor(UserDTO userDTO) {

        return new Professor(
                userDTO.getName(),
                userDTO.getLast_name(),
                userDTO.getProfessor_id_number(),
                userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword()),
                "PROFESSOR"
        );
    }

    //-------------------------------------------------------------------------------
}
