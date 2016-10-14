package com.example.infs3634project2.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.storage.DBOpenHelper;
import com.example.infs3634project2.storage.StudentsContract;

import java.util.ArrayList;

public class StudentProfile extends AppCompatActivity {

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
    }
}
