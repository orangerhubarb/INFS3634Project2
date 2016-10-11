package com.example.infs3634project2.model;

import java.util.ArrayList;

/**
 * Created by Davian on 6/10/16.
 */
public class Tutorial {

    //Perhaps uniquely identify by Day, and time? Because what happens if you put two of the same name in
    private String name;
    private ArrayList<Student> students;
    private int tutorialID;

    public Tutorial(String name) {
        this.name = name;
    }

    public Tutorial() {

    }

    public int getTutorialID() {
        return tutorialID;
    }

    public void setTutorialID(int tutorialID) {
        this.tutorialID = tutorialID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
