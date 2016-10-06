package com.example.infs3634project2.model;

import java.io.Serializable;

/**
 * Created by Davian on 6/10/16.
 */
public class Student implements Serializable {
    private int id;
    private String firstName;
    private String lastName;

    public Student() {

    }

    public Student(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
