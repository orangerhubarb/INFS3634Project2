package com.example.infs3634project2.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.infs3634project2.Data.GitHubCallback;
import com.example.infs3634project2.Data.GitHubDataProvider;
import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.storage.DBOpenHelper;
import com.example.infs3634project2.storage.StudentsContract;

import java.util.ArrayList;

public class StudentProfile extends AppCompatActivity implements GitHubCallback{

    private TextView studentText;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Student");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        studentText = (TextView) findViewById(R.id.studentName);

        int studentID = (int) getIntent().getSerializableExtra("StudentID");
        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        StudentsContract studentsContract = new StudentsContract(dbOpenHelper);

        student = studentsContract.getStudent(studentID);

        studentText.setText(student.getFirstName() + " " + student.getLastName());
        GitHubDataProvider gitHubDataProvider = new GitHubDataProvider(this);
        gitHubDataProvider.setmListener(this);
        gitHubDataProvider.getGitProject("rachellini");
    }

    @Override
    public void onTaskCompleted() {
        Log.d("Debug", "URL has been passed");
        //Obviously after this has been obtained, then we set all the layout shit.
    }

    @Override
    public void onFailure(String fail) {

    }
}
