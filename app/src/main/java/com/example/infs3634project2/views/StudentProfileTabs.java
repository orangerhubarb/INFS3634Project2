package com.example.infs3634project2.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.storage.DBOpenHelper;
import com.example.infs3634project2.storage.StudentsContract;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentProfileTabs extends AppCompatActivity {

    private DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
    private StudentsContract studentsContract = new StudentsContract(dbOpenHelper);
    private Student student;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Button editStudentButton;
    private TextView studentName;

    private int studentID;
    private int tutorialID;

    private ImageView studentPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile_tabs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        studentID = (int) getIntent().getSerializableExtra("StudentID");
        tutorialID = (int) getIntent().getSerializableExtra("TutorialID");
        student = studentsContract.getStudent(studentID);

        editStudentButton = (Button) findViewById(R.id.editStudentButton);
        editStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showEdit = new Intent(StudentProfileTabs.this, EditStudent.class);
                Bundle extras = new Bundle();
                extras.putInt("STUDENT_ID", studentID);
                extras.putString("FNAME", student.getFirstName());
                extras.putString("LNAME", student.getLastName());
                extras.putInt("TUTORIAL_ID", student.getTutorialID());
                extras.putString("ZID", student.getzID());
                extras.putInt("YEAR_OF_DEGREE", student.getYearOfDegree());
                extras.putString("DEGREE", student.getDegree());
                extras.putString("GITHUB_USER", student.getGithubUsername());
                extras.putString("STRENGTHS", student.getStrengths());
                extras.putString("WEAKNESSES", student.getWeaknesses());

                if(student.getStudentPicture() != null) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    student.getStudentPicture().compress(Bitmap.CompressFormat.PNG, 0, outputStream);
                    showEdit.putExtra("STUDENT_PICTURE", outputStream.toByteArray());
                }
                showEdit.putExtras(extras);
                startActivity(showEdit);
            }
        });

        studentName = (TextView) findViewById(R.id.studentName);
        studentName.setText(student.getFirstName() + " " + student.getLastName());
        studentPicture = (ImageView) findViewById(R.id.studentPictureProfile);


        if(student.getStudentPicture() != null) {
            studentPicture.setImageBitmap(student.getStudentPicture());
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentOne(), "ONE");
        adapter.addFragment(new FragmentTwo(), "TWO");
        adapter.addFragment(new FragmentThree(), "THREE");
        viewPager.setAdapter(adapter);
    }

    public Student getStudent() {
        return this.student;
    }

   public int getID() {
       Log.d("ID", String.valueOf(studentID));
       return studentID;
   }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
