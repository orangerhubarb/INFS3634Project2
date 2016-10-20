package com.example.infs3634project2.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.storage.DBOpenHelper;
import com.example.infs3634project2.storage.StudentsContract;

public class EditStudent extends AppCompatActivity {

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
    private Button confirmStudentSaveButton;

    private String firstNameText;
    private String lastNameText;
    private String zIDText;
    private int yearOfDegreeText;
    private String degreeText;
    private String githubUsernameText;
    private String strengthsText;
    private String weaknessesText;
    private int tutorialID;
    private int studentID;

    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Edit Student");

        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent backToProfile = new Intent(EditStudent.this, StudentProfile.class);
                backToProfile.putExtra("StudentID", studentID);
                backToProfile.putExtra("TutorialID", tutorialID);
                startActivity(backToProfile);
            }
        });

        studentID = (int) getIntent().getSerializableExtra("STUDENT_ID");
        firstNameText = (String) getIntent().getSerializableExtra("FNAME");
        lastNameText = (String) getIntent().getSerializableExtra("LNAME");
        zIDText = (String) getIntent().getSerializableExtra("ZID");
        yearOfDegreeText = (int) getIntent().getSerializableExtra("YEAR_OF_DEGREE");
        degreeText = (String) getIntent().getSerializableExtra("DEGREE");
        githubUsernameText = (String) getIntent().getSerializableExtra("GITHUB_USER");
        strengthsText = (String) getIntent().getSerializableExtra("STRENGTHS");
        weaknessesText = (String) getIntent().getSerializableExtra("WEAKNESSES");
        tutorialID = (int) getIntent().getSerializableExtra("TUTORIAL_ID");

        Log.d("STUDENT DETAILS DEBUG", firstNameText + lastNameText + zIDText + yearOfDegreeText + degreeText + githubUsernameText + strengthsText + weaknessesText + tutorialID);

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        firstNameEditText.setText(firstNameText);
        firstNameError = (TextInputLayout) findViewById(R.id.firstNameTextInput);

        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        lastNameEditText.setText(lastNameText);
        lastNameError = (TextInputLayout) findViewById(R.id.lastNameTextInput);

        zIDEditText = (EditText) findViewById(R.id.zIDEditText);
        zIDEditText.setText(zIDText);
        zIDError = (TextInputLayout) findViewById(R.id.zIDTextInput);

        yearOfDegreeEditText = (EditText) findViewById(R.id.yearOfDegreeEditText);
        yearOfDegreeEditText.setText(String.valueOf(yearOfDegreeText));
        degreeEditText = (EditText) findViewById(R.id.degreeEditText);
        degreeEditText.setText(degreeText);
        githubUsernameEditText = (EditText) findViewById(R.id.githubUserEditText);
        githubUsernameEditText.setText(githubUsernameText);
        strengths = (EditText) findViewById(R.id.strengthsEditText);
        strengths.setText(strengthsText);
        weaknesses = (EditText) findViewById(R.id.weaknessesEditText);
        weaknesses.setText(weaknessesText);

        confirmStudentSaveButton = (Button) findViewById(R.id.confirmStudentSaveButton);

        confirmStudentSaveButton.setOnClickListener(new View.OnClickListener() {

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
                }

                //Need to work out the regex here to match z followed by any 8 numbers
                if(zID.matches("")) {
                    zIDError.setErrorEnabled(true);
                    zIDError.setError("You have not entered a valid zID.");
                }

                if(noError == true) {

                    Student student = new Student(fName, lName, tutorialID, zID, yearOfDegree, degree, githubUsername, strength, weakness);

//                    //Probably chuck in cases here in case they leave stuff blank? But first name, last name, zID is compulsory
//
                    DBOpenHelper helper = new DBOpenHelper(EditStudent.this);
                    StudentsContract studentsContract = new StudentsContract(helper);

                    studentsContract.updateEditStudent(student, studentID);

                    //Have to change this so it redirects you to the new students page, or just back to the list??
                    Intent showStudentProfile = new Intent(EditStudent.this, StudentProfile.class);
                    showStudentProfile.putExtra("StudentID", studentID);
                    showStudentProfile.putExtra("TutorialID", tutorialID);
                    startActivity(showStudentProfile);
                }
            }
        });
    }

}
