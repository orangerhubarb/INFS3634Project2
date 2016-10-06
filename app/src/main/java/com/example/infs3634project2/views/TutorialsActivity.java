package com.example.infs3634project2.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.model.Tutorial;
import com.example.infs3634project2.recyclerviews.TutorialsAdapter;

import java.util.ArrayList;

public class TutorialsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TutorialsAdapter mAdapter;
    private ArrayList<Tutorial> tutorialsList;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials);

        tutorialsList = new ArrayList<>();

        Tutorial tutorial1 = new Tutorial("INFS3634");
        Tutorial tutorial2 = new Tutorial("INFS2621");

        ArrayList<Student> tutorial1Students = new ArrayList<>();
        tutorial1Students.add(new Student("Davian", "Liem", 1));
        tutorial1Students.add(new Student("Rachel", "Lin", 2));

        tutorial1.setStudents(tutorial1Students);

        ArrayList<Student> tutorial2Students = new ArrayList<>();
        tutorial2Students.add(new Student("Lucas", "Pun", 1));
        tutorial2Students.add(new Student("Bonnie", "Liang", 2));

        tutorial2.setStudents(tutorial2Students);


        tutorialsList.add(tutorial1);
        tutorialsList.add(tutorial2);

        mRecyclerView = (RecyclerView) findViewById(R.id.tutorialsRecylerView);
        mAdapter =  new TutorialsAdapter(tutorialsList);
        mRecyclerView.setAdapter(mAdapter);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

    }
}
