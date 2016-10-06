package com.example.infs3634project2.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;

import java.util.ArrayList;

public class StudentProfile extends AppCompatActivity {

    private TextView studentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        studentText = (TextView) findViewById(R.id.studentID);

        int studentID = (int) getIntent().getSerializableExtra("STUDENT");

        studentText.setText(Integer.toString(studentID));
    }
}
