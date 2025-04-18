package soheil.demo.start.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "student")
public class Student extends User {

    //Attributes.
    //-------------------------------------------------------------------------------
    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "last_name")
    private String last_name;

    @Column(nullable = false, name = "student_id_number")
    private int student_id_number;
    //-------------------------------------------------------------------------------

    //Relational Attributes.
    //-------------------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_name")
    private University university;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_name")
    private Faculty faculty;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<MarkCourseStudent> markCourseStudents;
    //-------------------------------------------------------------------------------

    //Constructors.
    //-------------------------------------------------------------------------------
    public Student() {
        super();
    }

    public Student(String name,
                   String last_name,
                   int student_id_number)
    {
        this.name = name;
        this.last_name = last_name;
        this.student_id_number = student_id_number;
    }

    public Student(String name,
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

    public List<MarkCourseStudent> getMarkCourseStudents() {
        return markCourseStudents;
    }

    public void setMarkCourseStudents(List<MarkCourseStudent> markCourseStudents) {
        this.markCourseStudents = markCourseStudents;
    }
    //-------------------------------------------------------------------------------

    //toString Method.
    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", student_id_number=" + student_id_number +
                ", university=" + (university != null ? university.getName() : null) +
                ", faculty=" + (faculty != null ? faculty.getName() : null) +
                ", markCourseStudents=" + (markCourseStudents != null ? markCourseStudents.toString() : null) +
                '}';
    }
    //-------------------------------------------------------------------------------
}
