package soheil.demo.start.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.List;
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

    @ManyToMany(mappedBy = "professors", fetch = FetchType.LAZY)
    private List<Course> courses;

    @OneToOne(mappedBy = "facultyHeadProfessor")
    private Faculty facultyHeadId;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Faculty getFacultyHeadId() {
        return facultyHeadId;
    }

    public void setFacultyHeadId(Faculty facultyHeadId) {
        this.facultyHeadId = facultyHeadId;
    }
//-------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", professor_id_number=" + professor_id_number +
                ", university=" + (university != null ? university.getName() : null) +
                ", faculty=" + (faculty != null ? faculty.getName() : null) +
                ", courseList=" + (courses != null ? courses.toString() : null) +
                '}';
    }
    //-------------------------------------------------------------------------------
}
