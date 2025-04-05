package soheil.demo.start.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollment")
public class MarkCourseStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "mark")
    private Short mark;

    //RelationalAttributes.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_name", referencedColumnName = "name")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_username", referencedColumnName = "username")
    private Student student;

    //Constructors.
    protected MarkCourseStudent() {}

    public MarkCourseStudent(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    public MarkCourseStudent(Course course, Student student, Short mark) {
        this.course = course;
        this.student = student;
        this.mark = mark;
    }

    // Getters & Setters.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getMark() {
        return mark;
    }

    public void setMark(Short mark) {
        this.mark = mark;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    //toString Method.
    @Override
    public String toString() {
        return "MarkCourseStudent{" +
                "id=" + id +
                ", mark=" + mark +
                ", course=" + (course != null ? course.getName() : null) +
                ", student=" + (student != null ? student.getUsername() : null) +
                '}';
    }
}