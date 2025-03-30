package soheil.demo.start.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "university")
public class University {

    //Attributes.
    //-------------------------------------------------------------------------------
    @Id
    @Column(nullable = false, name = "name")
    private String name;
    //-------------------------------------------------------------------------------

    //Relational Attributes.
    //-------------------------------------------------------------------------------
    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<Faculty> faculty;

    @OneToMany(mappedBy = "university",fetch = FetchType.LAZY)
    private List<Professor> professors;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<Student> students;
    //-------------------------------------------------------------------------------

    //Constructors.
    //-------------------------------------------------------------------------------
    protected University() {}

    public University(String name) {
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

    public List<Faculty> getFaculty() {
        return faculty;
    }

    public void setFaculty(List<Faculty> faculty) {
        this.faculty = faculty;
    }
    //-------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", faculty=" + faculty +
                ", professors=" + professors +
                ", students=" + students +
                '}';
    }
    //-------------------------------------------------------------------------------
}
