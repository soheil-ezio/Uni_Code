package soheil.demo.start.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.Set;


@Entity
@Table(name = "student")
public class student extends user{

    //Attributes.
    //-------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false,unique = true,name = "id")
    @Cascade(org.hibernate.annotations.CascadeType.DETACH)
    private long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "last_name")
    private String last_name;

    @Column(nullable = false, name = "student_id_number")
    private int student_id_number;
    //-------------------------------------------------------------------------------

    //Relational Attributes.
    //-------------------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name = "university_id")
    private university university;

    @ManyToMany(mappedBy = "students")
    private Set<professor> professorList;
    //-------------------------------------------------------------------------------


    //Constructors.
    //-------------------------------------------------------------------------------
    public student() {}

    public student(long id,
                   String name,
                   String last_name,
                   int student_id_number)
    {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.student_id_number = student_id_number;
    }

    public student(String name,
                   String last_name,
                   int student_id_number)
    {
        this.name = name;
        this.last_name = last_name;
        this.student_id_number = student_id_number;
    }

    public student(String name,
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

    public int getStudent_id_number() {
        return student_id_number;
    }

    public void setStudent_id_number(int student_id_number) {
        this.student_id_number = student_id_number;
    }

    public soheil.demo.start.model.university getUniversity() {
        return university;
    }

    public void setUniversity(soheil.demo.start.model.university university) {
        this.university = university;
    }

    public Set<professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(Set<professor> professorList) {
        this.professorList = professorList;
    }
    //-------------------------------------------------------------------------------

    //toString Method.
    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", student_id_number=" + student_id_number +
                '}';
    }
    //-------------------------------------------------------------------------------
}
