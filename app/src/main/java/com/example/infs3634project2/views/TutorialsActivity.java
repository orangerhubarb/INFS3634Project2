package com.example.infs3634project2.views;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Tutorial;
import com.example.infs3634project2.recyclerviews.TutorialsAdapter;
import com.example.infs3634project2.storage.DBOpenHelper;
import com.example.infs3634project2.storage.TutorialsContract;

import java.util.ArrayList;

public class TutorialsActivity extends AppCompatActivity {

    //The only way to get to the list of tutorials/students has to be through a button otherwise the list doesn't refresh
    private RecyclerView mRecyclerView;
    private TutorialsAdapter mAdapter;
    private ArrayList<Tutorial> tutorialsList;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Classes");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This should redirect to a blank list of the students but working out Tutorials right now
                Intent showClasses = new Intent(TutorialsActivity.this, NewTutorial.class);
                startActivity(showClasses);
            }
        });

        DBOpenHelper helper = new DBOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        TutorialsContract tutorialsContract = new TutorialsContract(helper);

        tutorialsList = tutorialsContract.getTutorials();

        mRecyclerView = (RecyclerView) findViewById(R.id.tutorialsRecylerView);
        mAdapter = new TutorialsAdapter(tutorialsList);
        mRecyclerView.setAdapter(mAdapter);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }
}
