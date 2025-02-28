package soheil.demo.start.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class user {

    //Attributes.
    //-------------------------------------------------------------------------------
    @Id
    private String username;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "role")
    private String role;
    //-------------------------------------------------------------------------------

    //Constructors.
    //-------------------------------------------------------------------------------
    public user() {}

    public user(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    //-------------------------------------------------------------------------------

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
    //-------------------------------------------------------------------------------

    //toString Method.
    //-------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "user{" +
                "username=" + username +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
    //-------------------------------------------------------------------------------
}
