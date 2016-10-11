package com.example.infs3634project2.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Tutorial;
import com.example.infs3634project2.storage.DBOpenHelper;
import com.example.infs3634project2.storage.TutorialsContract;

public class NewTutorial extends AppCompatActivity {

    //Need validation checks, so how do we stop people from entering in dodgy course codes or multiple lines of codes

    private Button addTutorialButton;
    private EditText tutorialName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tutorial);

        addTutorialButton = (Button) findViewById(R.id.addTutorialButton);
        tutorialName = (EditText) findViewById(R.id.tutorialNameEditText);

        addTutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tutorial tutorial = new Tutorial(tutorialName.getText().toString());

                DBOpenHelper helper = new DBOpenHelper(NewTutorial.this);
                TutorialsContract tutorialsContract = new TutorialsContract(helper);

                tutorialsContract.insertNewTutorial(tutorial);

                Intent showClasses = new Intent(NewTutorial.this, TutorialsActivity.class);
                startActivity(showClasses);

            }
        });


    }
}
