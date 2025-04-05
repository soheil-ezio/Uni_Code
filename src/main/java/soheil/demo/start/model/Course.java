package soheil.demo.start.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    //Attributes.
    //-------------------------------------------------------------------------------
    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "credit")
    private short credit;
    //-------------------------------------------------------------------------------

    //Relational attributes.
    //-------------------------------------------------------------------------------
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MarkCourseStudent> markCourseStudents;

    @ManyToOne(fetch = FetchType.LAZY)
    private University university;

    @ManyToOne(fetch = FetchType.LAZY)
    private Faculty faculty;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_name"),
            inverseJoinColumns = @JoinColumn(name = "student_username")
    )
    private List<Student> students;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_professor",
            joinColumns = @JoinColumn(name = "course_name"),
            inverseJoinColumns = @JoinColumn(name = "professor_username")
    )
    private List<Professor> professors;

    @OneToOne(fetch = FetchType.LAZY)
    private UniClass uniClass;
    //-------------------------------------------------------------------------------

    //Constructors.
    //-------------------------------------------------------------------------------
    protected Course() {}

    public Course(String name) {}

    public Course(String name,
                  short credit)
    {
        this.name = name;
        this.credit = credit;
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

    public short getCredit() {
        return credit;
    }

    public void setCredit(short credit) {
        this.credit = credit;
    }

    public List<MarkCourseStudent> getMarkCourseStudents() {
        return markCourseStudents;
    }

    public void setMarkCourseStudents(List<MarkCourseStudent> markCourseStudents) {
        this.markCourseStudents = markCourseStudents;
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

    public UniClass getUniClass() {
        return uniClass;
    }

    public void setUniClass(UniClass uniClass) {
        this.uniClass = uniClass;
    }
    //-------------------------------------------------------------------------------

    //toString method.
    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", credit=" + credit +
                ", markCourseStudents=" + markCourseStudents +
                ", university=" + university +
                ", faculty=" + faculty +
                ", students=" + students +
                ", professors=" + professors +
                ", uniClass=" + uniClass +
                '}';
    }
    //-------------------------------------------------------------------------------
}
