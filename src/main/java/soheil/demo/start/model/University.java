package soheil.demo.start.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "university")
public class University {

    //Attributes.
    //-------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name = "name")
    private String name;
    //-------------------------------------------------------------------------------

    //Relational Attributes.
    //-------------------------------------------------------------------------------
    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<Professor> Professors;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<Student> Students;
    //-------------------------------------------------------------------------------

    //Constructors.
    //-------------------------------------------------------------------------------
    public University() {}

    public University(String name) {
        this.name = name;
    }

    public University(int id,
                      String name)
    {
        this.id = id;
        this.name = name;
    }
    //-------------------------------------------------------------------------------

    //Getters & Setters.
    //-------------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Professor> getProfessors() {
        return Professors;
    }

    public void setProfessors(List<Professor> Professors) {
        this.Professors = Professors;
    }

    public List<Student> getStudents() {
        return Students;
    }

    public void setStudents(List<Student> Students) {
        this.Students = Students;
    }
    //-------------------------------------------------------------------------------

    //toString Method.
    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    //-------------------------------------------------------------------------------
}
