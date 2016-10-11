package com.example.infs3634project2.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.model.Tutorial;
import com.example.infs3634project2.storage.DBOpenHelper;
import com.example.infs3634project2.storage.StudentsContract;
import com.example.infs3634project2.storage.TutorialsContract;

public class NewStudent extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private Button confirmStudentAddButton;
    private int tutorialID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        tutorialID = (int) getIntent().getSerializableExtra("TutorialID");
        Log.d("New Student Tutorial ID", Integer.toString(tutorialID));

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        confirmStudentAddButton = (Button) findViewById(R.id.confirmStudentAddButton);

        confirmStudentAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student(firstNameEditText.getText().toString(), lastNameEditText.getText().toString(), tutorialID);

                DBOpenHelper helper = new DBOpenHelper(NewStudent.this);
                StudentsContract studentsContract = new StudentsContract(helper);

                int studentID = (int) studentsContract.insertNewStudent(student);

                //Have to change this so it redirects you to the new students page, or just back to the list??
                Intent showClasses = new Intent(NewStudent.this, StudentsActivity.class);
                showClasses.putExtra("TutorialID", tutorialID);
                startActivity(showClasses);

            }
        });
    }
}
