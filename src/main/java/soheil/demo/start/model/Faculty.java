package soheil.demo.start.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "faculty")
public class Faculty {

    //Attributes.
    //-------------------------------------------------------------------------------
    @Id
    private String name;
    //-------------------------------------------------------------------------------

    //Relational Attributes.
    //-------------------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university")
    private University university;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Professor> professors;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Course> courses;

    @OneToOne(fetch = FetchType.LAZY)
    private Professor facultyHeadProfessor;
    //-------------------------------------------------------------------------------

    //Constructors.
    //-------------------------------------------------------------------------------
    protected Faculty() {};

    public Faculty(String name) {
        this.name = name;
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Professor getFacultyHeadProfessor() {
        return facultyHeadProfessor;
    }

    public void setFacultyHeadProfessor(Professor facultyHeadProfessor) {
        this.facultyHeadProfessor = facultyHeadProfessor;
    }
    //-------------------------------------------------------------------------------

    //toString Method.
    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", university=" + (university != null ? university.getName() : null) +
                ", professors=" + (professors != null ? professors.toString() : null) +
                ", Students=" + (students != null ? students.toString() : null) +
                ", courses=" + (courses != null ? courses.toString() : null) +
                ", facultyHeadProfessor=" + (facultyHeadProfessor != null ? facultyHeadProfessor.getName() : null) +
                '}';
    }
    //-------------------------------------------------------------------------------
}
