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

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<Professor> professors;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<Student> Students;
    //-------------------------------------------------------------------------------

    //Constructors.
    //-------------------------------------------------------------------------------
    protected Faculty() {};

    public Faculty(String name) {}
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
        return Students;
    }

    public void setStudents(List<Student> students) {
        Students = students;
    }
    //-------------------------------------------------------------------------------

    //toString Method.
    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", university=" + university +
                ", professors=" + professors +
                ", Students=" + Students +
                '}';
    }
    //-------------------------------------------------------------------------------
}
