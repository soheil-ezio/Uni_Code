package soheil.demo.start.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Entity
@Table(name = "professor")
public class Professor extends User {

    //Attributes.
    //-------------------------------------------------------------------------------
    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "last_name")
    private String last_name;

    @Column(nullable = false, name = "professor_id_number")
    private int professor_id_number;
    //-------------------------------------------------------------------------------

    //Relational Attributes.
    //-------------------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_name")
    private University university;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_name")
    private Faculty faculty;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "professor_student",
            joinColumns = @JoinColumn(name = "professor_id_number"),
            inverseJoinColumns = @JoinColumn(name = "student_id_number")
    )
    private Set<Student> students;
    //-------------------------------------------------------------------------------

    //Constructor
    //-------------------------------------------------------------------------------
    protected Professor() {
        super();
    }

    public Professor(String name,
                     String last_name,
                     int professor_id_number)
    {
        this.name = name;
        this.last_name = last_name;
        this.professor_id_number = professor_id_number;
    }

    public Professor(String name,
                     String last_name,
                     int professor_id_number,
                     String username,
                     String password,
                     String role)
    {
        super(username, password, role);
        this.name = name;
        this.last_name = last_name;
        this.professor_id_number = professor_id_number;
    }
    //-------------------------------------------------------------------------------

    //Setters & Getters.
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

    public int getProfessor_id_number() {
        return professor_id_number;
    }

    public void setProfessor_id_number(int professor_id_number) {
        this.professor_id_number = professor_id_number;
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    //-------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", professor_id_number=" + professor_id_number +
                ", university=" + university +
                ", faculty=" + faculty +
                ", students=" + students +
                '}';
    }
    //-------------------------------------------------------------------------------
}
