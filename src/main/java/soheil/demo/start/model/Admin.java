package soheil.demo.start.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    //Constructors.
    //-------------------------------------------------------------------------------
    protected Admin() {}

    public Admin(String username, String password, String role) {
        super(username, password, role);
    }
    //-------------------------------------------------------------------------------
}
