package com.example.infs3634project2.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.storage.DBOpenHelper;
import com.example.infs3634project2.storage.StudentsContract;

public class NewStudent extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText zIDEditText;
    //Maybe change year of degree to the scroll thing??
    private EditText yearOfDegreeEditText;
    private EditText degreeEditText;
    private EditText githubUsernameEditText;
    private EditText strengths;
    private EditText weaknesses;
    private Button confirmStudentAddButton;
    private int tutorialID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("New Student");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tutorialID = (int) getIntent().getSerializableExtra("TutorialID");
        Log.d("New Student Tutorial ID", Integer.toString(tutorialID));

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        zIDEditText = (EditText) findViewById(R.id.zIDEditText);

        yearOfDegreeEditText = (EditText) findViewById(R.id.yearOfDegreeEditText);
        degreeEditText = (EditText) findViewById(R.id.degreeEditText);
        githubUsernameEditText = (EditText) findViewById(R.id.githubUserEditText);
        strengths = (EditText) findViewById(R.id.strengthsEditText);
        weaknesses = (EditText) findViewById(R.id.weaknessesEditText);

        confirmStudentAddButton = (Button) findViewById(R.id.confirmStudentAddButton);

        confirmStudentAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = firstNameEditText.getText().toString();
                String lName = lastNameEditText.getText().toString();
                String zID = zIDEditText.getText().toString();
                String degree = degreeEditText.getText().toString();
                int yearOfDegree = Integer.parseInt(yearOfDegreeEditText.getText().toString());
                String githubUsername = githubUsernameEditText.getText().toString();
                String strength = strengths.getText().toString();
                String weakness = weaknesses.getText().toString();

                Student student = new Student(fName, lName, tutorialID, zID, yearOfDegree, degree, githubUsername, strength, weakness);

                Log.d("DEBUG NEW", student.getzID() + student.getYearOfDegree() + student.getDegree());

                //Probably chuck in cases here in case they leave stuff blank? But first name, last name, zID is compulsory

                DBOpenHelper helper = new DBOpenHelper(NewStudent.this);
                StudentsContract studentsContract = new StudentsContract(helper);

                int studentID = (int) studentsContract.insertNewStudent(student);

                //Have to change this so it redirects you to the new students page, or just back to the list??
                Intent showStudentProfile = new Intent(NewStudent.this, StudentProfile.class);

                showStudentProfile.putExtra("StudentID", studentID);
                startActivity(showStudentProfile);

            }
        });
    }
}
