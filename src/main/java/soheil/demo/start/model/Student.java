package soheil.demo.start.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Set;


@Entity
@Table(name = "student")
public class Student extends User {

    //Attributes.
    //-------------------------------------------------------------------------------
    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "last_name")
    private String last_name;

    @Column(nullable = false, name = "student_id_number")
    private int student_id_number;
    //-------------------------------------------------------------------------------

    //Relational Attributes.
    //-------------------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_name")
    private University university;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_name")
    private Faculty faculty;

    @ManyToMany(mappedBy = "students")
    private Set<Professor> professorList;
    //-------------------------------------------------------------------------------


    //Constructors.
    //-------------------------------------------------------------------------------
    public Student() {
        super();
    }

    public Student(String name,
                   String last_name,
                   int student_id_number)
    {
        this.name = name;
        this.last_name = last_name;
        this.student_id_number = student_id_number;
    }

    public Student(String name,
                   String last_name,
                   int student_id_number,
                   String username,
                   String password,
                   String role)
    {
        super(username, password, role);
        this.name = name;
        this.last_name = last_name;
        this.student_id_number = student_id_number;
    }
    //-------------------------------------------------------------------------------

    //Getters & Setters.
    //-------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getStudent_id_number() {
        return student_id_number;
    }

    public void setStudent_id_number(int student_id_number) {
        this.student_id_number = student_id_number;
    }

    public Set<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(Set<Professor> professorList) {
        this.professorList = professorList;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    //-------------------------------------------------------------------------------

    //toString Method.
    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", student_id_number=" + student_id_number +
                ", university=" + university +
                ", faculty=" + faculty +
                ", professorList=" + professorList +
                '}';
    }
    //-------------------------------------------------------------------------------
}
