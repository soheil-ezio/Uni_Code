package soheil.demo.start.model;

import jakarta.persistence.Entity;

@Entity
public class admin extends user{

    //Constructors.
    //-------------------------------------------------------------------------------
    public admin() {}

    public admin(long username, String password, String role) {
        super(username, password, role);
    }
    //-------------------------------------------------------------------------------
}
