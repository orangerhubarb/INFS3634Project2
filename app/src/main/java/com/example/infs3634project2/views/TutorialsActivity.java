package com.example.infs3634project2.views;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.model.Tutorial;
import com.example.infs3634project2.recyclerviews.TutorialsAdapter;
import com.example.infs3634project2.storage.DBOpenHelper;
import com.example.infs3634project2.storage.TutorialsContract;

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

        DBOpenHelper helper = new DBOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        TutorialsContract tutorialsContract = new TutorialsContract(helper);

        tutorialsList = tutorialsContract.getTutorials();


        mRecyclerView = (RecyclerView) findViewById(R.id.tutorialsRecylerView);
        mAdapter =  new TutorialsAdapter(tutorialsList);
        mRecyclerView.setAdapter(mAdapter);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

    }
}
