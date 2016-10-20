package com.example.infs3634project2.views;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.storage.DBOpenHelper;
import com.example.infs3634project2.storage.StudentsContract;

import org.w3c.dom.Text;

public class NewStudent extends AppCompatActivity {

    private EditText firstNameEditText;
    private TextInputLayout firstNameError;

    private EditText lastNameEditText;
    private TextInputLayout lastNameError;

    private EditText zIDEditText;
    private TextInputLayout zIDError;

    //Maybe change year of degree to the scroll thing??
    private EditText yearOfDegreeEditText;
    private EditText degreeEditText;
    private EditText githubUsernameEditText;
    private EditText strengths;
    private EditText weaknesses;
    private Button confirmStudentAddButton;
    private int tutorialID;

    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("New Student");
        setSupportActionBar(toolbar);

        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent backToTutorialList = new Intent(NewStudent.this, TutorialsActivity.class);
                backToTutorialList.putExtra("TutorialID", tutorialID);
                startActivity(backToTutorialList);
            }
        });

        tutorialID = (int) getIntent().getSerializableExtra("TutorialID");
        Log.d("New Student TID", Integer.toString(tutorialID));

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        firstNameError = (TextInputLayout) findViewById(R.id.firstNameTextInput);

        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        lastNameError = (TextInputLayout) findViewById(R.id.lastNameTextInput);

        zIDEditText = (EditText) findViewById(R.id.zIDEditText);
        zIDError = (TextInputLayout) findViewById(R.id.zIDTextInput);

        yearOfDegreeEditText = (EditText) findViewById(R.id.yearOfDegreeEditText);
        degreeEditText = (EditText) findViewById(R.id.degreeEditText);
        githubUsernameEditText = (EditText) findViewById(R.id.githubUserEditText);
        strengths = (EditText) findViewById(R.id.strengthsEditText);
        weaknesses = (EditText) findViewById(R.id.weaknessesEditText);

        confirmStudentAddButton = (Button) findViewById(R.id.confirmStudentAddButton);

        confirmStudentAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean noError = true;

                String fName = firstNameEditText.getText().toString();

                String lName = lastNameEditText.getText().toString();

                String zID = zIDEditText.getText().toString();
                String degree = degreeEditText.getText().toString();
                int yearOfDegree = Integer.parseInt(yearOfDegreeEditText.getText().toString());
                String githubUsername = githubUsernameEditText.getText().toString();

                //Could we maybe do strengths/weaknesses after?? Just to reduce clustering of the screen
                String strength = strengths.getText().toString();
                String weakness = weaknesses.getText().toString();

                if(fName.matches("")) {
                    firstNameError.setErrorEnabled(true);
                    firstNameError.setError("You have not provided a first name.");
                    noError = false;
                }

                if(lName.matches("")) {
                    lastNameError.setErrorEnabled(true);
                    lastNameError.setError("You have not provided a last name.");
                    noError = false;
                }

                //Need to work out the regex here to match z followed by any 8 numbers
                if(zID.matches("") || !zID.matches("z[0-9]{7}")) {
                    zIDError.setErrorEnabled(true);
                    zIDError.setError("You have not entered a valid zID.");
                    noError = false;
                }

                if(noError == true) {

                    Student student = new Student(fName, lName, tutorialID, zID, yearOfDegree, degree, githubUsername, strength, weakness);

                    //Probably chuck in cases here in case they leave stuff blank? But first name, last name, zID is compulsory

                    DBOpenHelper helper = new DBOpenHelper(NewStudent.this);
                    StudentsContract studentsContract = new StudentsContract(helper);

                    int studentID = (int) studentsContract.insertNewStudent(student);

                    //Have to change this so it redirects you to the new students page, or just back to the list??
                    Intent showStudentProfile = new Intent(NewStudent.this, StudentProfileTabs.class);

                    showStudentProfile.putExtra("StudentID", studentID);
                    showStudentProfile.putExtra("TutorialID", tutorialID);
                    startActivity(showStudentProfile);
                }

            }
        });
    }
}