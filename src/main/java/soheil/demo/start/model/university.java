package soheil.demo.start.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "university")
public class university {

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
    @OneToMany(mappedBy = "university")
    private List<professor> professors;

    @OneToMany(mappedBy = "university")
    private List<student> students;
    //-------------------------------------------------------------------------------

    //Constructors.
    //-------------------------------------------------------------------------------
    public university() {}

    public university(int id,
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

    public List<professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<professor> professors) {
        this.professors = professors;
    }

    public List<student> getStudents() {
        return students;
    }

    public void setStudents(List<student> students) {
        this.students = students;
    }
    //-------------------------------------------------------------------------------

    //toString Method.
    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "university{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    //-------------------------------------------------------------------------------
}
