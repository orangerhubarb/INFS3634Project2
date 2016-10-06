package com.example.infs3634project2.model;

import java.util.ArrayList;

/**
 * Created by Davian on 6/10/16.
 */
public class Tutorial {
    private String name;
    private ArrayList<Student> students;

    public Tutorial(String name) {
        this.name = name;
    }

    public Tutorial() {

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
