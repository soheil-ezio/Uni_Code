package soheil.demo.start.DTO;

public class UserDTO {

    //Main Info.
    //-------------------------------------------------------------------------------
    private String username;
    private String password;
    private String role;
    private String universityName;
    private String facultyName;

    //Students & Professors Essentials.
    //-------------------------------------------------------------------------------
    private String name;
    private String last_name;
    private int student_id_number;
    private int professor_id_number;

    //Getters & Setters.
    //-------------------------------------------------------------------------------

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
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

    public int getProfessor_id_number() {
        return professor_id_number;
    }

    public void setProfessor_id_number(int professor_id_number) {
        this.professor_id_number = professor_id_number;
    }

    //-------------------------------------------------------------------------------

    //toString Method.(This part if experimental !)
    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", universityId=" + universityName +
                ", name='" + name + '\'' +
                ", last_Name='" + last_name + '\'' +
                ", student_id_number=" + student_id_number +
                ", professor_id_number=" + professor_id_number +
                '}';
    }
    //-------------------------------------------------------------------------------
}
