package soheil.demo.start.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Class")
public class UniClass {

    //Attributes.
    //-------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "class_number")
    private short classNumber;
    //-------------------------------------------------------------------------------

    //Relational Attributes.
    //-------------------------------------------------------------------------------
    @OneToOne(mappedBy = "uniClass")
    private Course course;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "uniClass")
    private List<Student> students;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "uniClass")
    private List<Professor> professors;
    //-------------------------------------------------------------------------------

    //Constructor.
    //-------------------------------------------------------------------------------
    protected UniClass() {}

    public UniClass(int id) {
        this.id = id;
    }

    public UniClass(int id,
                    short classNumber)
    {
        this.id = id;
        this.classNumber = classNumber;
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

    public short getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(short classNumber) {
        this.classNumber = classNumber;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }
    //-------------------------------------------------------------------------------

    //toString Method.
    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "UniClass{" +
                "id=" + id +
                ", classNumber=" + classNumber +
                ", course=" + course +
                ", students=" + students +
                ", professors=" + professors +
                '}';
    }
    //-------------------------------------------------------------------------------
}
