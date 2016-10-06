package com.example.infs3634project2.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.model.Tutorial;
import com.example.infs3634project2.recyclerviews.StudentsAdapter;
import com.example.infs3634project2.recyclerviews.TutorialsAdapter;

import java.util.ArrayList;

public class StudentsActivity extends AppCompatActivity {

    private ArrayList<Student> studentsList;
    private RecyclerView mRecyclerView;
    private StudentsAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        studentsList = (ArrayList<Student>) getIntent().getSerializableExtra("STUDENTS");
        Log.d("Debug", studentsList.toString());

        mRecyclerView = (RecyclerView) findViewById(R.id.studentsRecyclerView);
        mAdapter =  new StudentsAdapter(studentsList);
        mRecyclerView.setAdapter(mAdapter);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

    }
}
