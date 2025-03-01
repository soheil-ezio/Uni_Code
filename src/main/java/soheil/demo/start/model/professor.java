package soheil.demo.start.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "professor")
public class professor extends user{

    //Attributes.
    //-------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "last_name")
    private String last_name;

    @Column(nullable = false, name = "professor_id_number")
    private int professor_id_number;
    //-------------------------------------------------------------------------------

    //Relational Attributes.
    //-------------------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name = "university_id")
    private university university;

    @ManyToMany
    @JoinTable(
            name = "professor_student",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<student> students;
    //-------------------------------------------------------------------------------

    //Constructor
    //-------------------------------------------------------------------------------
    public professor() {}

    public professor(long id,
                     String name,
                     String last_name,
                     int professor_id_number)
    {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.professor_id_number = professor_id_number;
    }

    public professor(String name,
                     String last_name,
                     int professor_id_number)
    {
        this.name = name;
        this.last_name = last_name;
        this.professor_id_number = professor_id_number;
    }

    public professor(String name,
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
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public soheil.demo.start.model.university getUniversity() {
        return university;
    }

    public void setUniversity(soheil.demo.start.model.university university) {
        this.university = university;
    }

    public Set<student> getStudents() {
        return students;
    }

    public void setStudents(Set<student> students) {
        this.students = students;
    }
    //-------------------------------------------------------------------------------

    //toString Method.
    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "professor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", professor_id_number=" + professor_id_number +
                '}';
    }
    //-------------------------------------------------------------------------------
}
