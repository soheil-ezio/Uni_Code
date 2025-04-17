package soheil.demo.start.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

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
    protected User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String role) {
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

    //Implemented Methods
    //-------------------------------------------------------------------------------
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    //-------------------------------------------------------------------------------

    //toString Method.
    //-------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "User{" +
                "username=" + username +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    //-------------------------------------------------------------------------------
}
