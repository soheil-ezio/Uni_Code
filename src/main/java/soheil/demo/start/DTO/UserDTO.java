package soheil.demo.start.DTO;

public class UserDTO {

    //Main Info.
    //-------------------------------------------------------------------------------
    private String username;
    private String password;
    private String role;
    private long universityId;

    //Students & Professors Essentials.
    //-------------------------------------------------------------------------------
    private String name;
    private String last_Name;
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

    public long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(long universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
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

    //toString Method.
    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", universityId=" + universityId +
                ", name='" + name + '\'' +
                ", last_Name='" + last_Name + '\'' +
                ", student_id_number=" + student_id_number +
                ", professor_id_number=" + professor_id_number +
                '}';
    }
    //-------------------------------------------------------------------------------
}
