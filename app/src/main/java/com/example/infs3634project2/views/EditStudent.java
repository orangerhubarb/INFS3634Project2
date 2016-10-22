package com.example.infs3634project2.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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


    private EditText degreeEditText;
    private EditText githubUsernameEditText;
    private EditText strengths;
    private EditText weaknesses;
    private EditText phonenumber;
    private EditText email;
    private Spinner yearOfDegreeSpinner;
    private ImageView studentPictureEdit;
    private Button confirmStudentSaveButton;

    private String firstNameText;
    private String lastNameText;
    private String zIDText;
    private int yearOfDegreeText;
    private String degreeText;
    private String githubUsernameText;
    private String strengthsText;
    private String weaknessesText;
    private String phonenumberText;
    private String emailText;

    private int tutorialID;
    private int studentID;

    private byte[] studentPictureByteArray;
    private Bitmap studentPictureBitmap;
    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    private Button takeNewPhoto;

    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent backToProfile = new Intent(EditStudent.this, StudentProfileTabs.class);
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
        phonenumberText = (String) getIntent().getSerializableExtra("PHONE_NUMBER");
        emailText = (String) getIntent().getSerializableExtra("EMAIL");

        studentPictureEdit = (ImageView) findViewById(R.id.studentPictureEdit);
        takeNewPhoto = (Button) findViewById(R.id.takePhoto);

        if (getIntent().hasExtra("STUDENT_PICTURE")) {
            studentPictureByteArray = getIntent().getByteArrayExtra("STUDENT_PICTURE");
            studentPictureBitmap = BitmapFactory.decodeByteArray(studentPictureByteArray, 0, studentPictureByteArray.length);
            studentPictureEdit.setImageBitmap(studentPictureBitmap);

        }

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

        yearOfDegreeSpinner = (Spinner) findViewById(R.id.yearOfDegreeSpinnerEdit);
        ArrayAdapter<CharSequence> yearOfDegreeAdapter = ArrayAdapter.createFromResource(this,
                R.array.yearOfDegree_array, android.R.layout.simple_spinner_item);
        yearOfDegreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearOfDegreeSpinner.setAdapter(yearOfDegreeAdapter);

        int spinnerPosition = yearOfDegreeAdapter.getPosition(String.valueOf(yearOfDegreeText));
        yearOfDegreeSpinner.setSelection(spinnerPosition);

        degreeEditText = (EditText) findViewById(R.id.degreeEditText);
        degreeEditText.setText(degreeText);
        githubUsernameEditText = (EditText) findViewById(R.id.githubUserEditText);
        githubUsernameEditText.setText(githubUsernameText);
        strengths = (EditText) findViewById(R.id.strengthsEditText);
        strengths.setText(strengthsText);
        weaknesses = (EditText) findViewById(R.id.weaknessesEditText);
        weaknesses.setText(weaknessesText);

        phonenumber = (EditText) findViewById(R.id.phonenumberEditText);
        phonenumber.setText(phonenumberText);

        email = (EditText) findViewById(R.id.emailEditText);
        email.setText(emailText);


        takeNewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }

                else {
                    Intent imageIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(imageIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                    Log.d("IMAGE", "IMAGE SNAPPED");
                }

            }
        });

        confirmStudentSaveButton = (Button) findViewById(R.id.confirmStudentSaveButton);

        confirmStudentSaveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean noError = true;

                String fName = firstNameEditText.getText().toString();

                String lName = lastNameEditText.getText().toString();

                String zID = zIDEditText.getText().toString();
                String degree = degreeEditText.getText().toString();
                int yearOfDegree = Integer.parseInt(yearOfDegreeSpinner.getSelectedItem().toString());
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
                    if(studentPictureBitmap != null) {
                        student.setStudentPicture(studentPictureBitmap);
                    }
                    student.setPhoneNumber(phonenumber.getText().toString());
                    student.setEmail(email.getText().toString());

                    DBOpenHelper helper = new DBOpenHelper(EditStudent.this);
                    StudentsContract studentsContract = new StudentsContract(helper);

                    studentsContract.updateEditStudent(student, studentID);

                    Intent showStudentProfile = new Intent(EditStudent.this, StudentProfileTabs.class);
                    showStudentProfile.putExtra("StudentID", studentID);
                    showStudentProfile.putExtra("TutorialID", tutorialID);
                    startActivity(showStudentProfile);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {

                //use imageUri here to access the image

                Bundle extras = data.getExtras();

                studentPictureBitmap = (Bitmap) extras.get("data");

                studentPictureEdit.setImageBitmap(studentPictureBitmap);


                // here you will get the image as bitmap


            }
            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT);
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent imageIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(imageIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                Log.d("IMAGE", "IMAGE SNAPPED");

            }
            else {

            }
        }
    }

}