package soheil.demo.start.model;

import jakarta.persistence.*;


@Entity
@Table(name = "student")
public class student {

    //Attributes.
    //-------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false,unique = true,name = "id")
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
    //nothing yet
    //-------------------------------------------------------------------------------


    //Constructors.
    //-------------------------------------------------------------------------------

    public student() {

    }

    public student(long id, String name, String last_name, int student_id_number) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.student_id_number = student_id_number;
    }

    //-------------------------------------------------------------------------------

    //Getters & Setters.
    //-------------------------------------------------------------------------------

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setStudent_id_number(int student_id_number) {
        this.student_id_number = student_id_number;
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
