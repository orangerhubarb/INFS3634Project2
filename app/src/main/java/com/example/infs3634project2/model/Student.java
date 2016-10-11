package com.example.infs3634project2.model;

import java.io.Serializable;

/**
 * Created by Davian on 6/10/16.
 */
public class Student implements Serializable {
    private int tutorialID;
    private String firstName;
    private String lastName;

    public Student() {

    }

    public Student(String firstName, String lastName, int tutorialID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tutorialID = tutorialID;
    }

    public int getTutorialID() { return tutorialID; };

    public void setTutorialID(int tutorialID) { this.tutorialID = tutorialID; }

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


}
