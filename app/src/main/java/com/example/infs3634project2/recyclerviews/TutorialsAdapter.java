package com.example.infs3634project2.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.model.Tutorial;
import com.example.infs3634project2.storage.DBOpenHelper;
import com.example.infs3634project2.storage.StudentsContract;
import com.example.infs3634project2.views.StudentsActivity;
import com.example.infs3634project2.views.TutorialsActivity;

import java.util.ArrayList;

import static android.view.View.VISIBLE;

/**
 * Created by Davian on 6/10/16.
 */
public class TutorialsAdapter extends RecyclerView.Adapter<TutorialsAdapter.TutorialsHolder> {

    private ArrayList<Tutorial> mTutorial;
    public TutorialsActivity tutorialsActivity;

    public TutorialsAdapter(ArrayList<Tutorial> tutorialList, TutorialsActivity tutorialsActivity) {
        this.mTutorial = tutorialList;
        this.tutorialsActivity = tutorialsActivity;
    }

    @Override
    public TutorialsAdapter.TutorialsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tutorials_item_row, parent, false);

        return new TutorialsHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(TutorialsHolder holder, int position) {
        Tutorial itemTutorial = mTutorial.get(position);
        if (itemTutorial.getStudents() != null) {
            Log.d("Debug", itemTutorial.getStudents().toString());
        }
        holder.bindTutorial(itemTutorial);
    }

    @Override
    public int getItemCount() {
        return mTutorial.size();
    }

    public class TutorialsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tutorialName;
        private TextView tutorialDay;
        private TextView tutorialStudentCount;
        private TextView tutorialTime;

        private Tutorial tutorialItem;

        public TutorialsHolder(View itemView) {
            super(itemView);
            tutorialName = (TextView) itemView.findViewById(R.id.tutorial_name);
            tutorialDay = (TextView) itemView.findViewById(R.id.tutorial_day);
            tutorialTime = (TextView) itemView.findViewById(R.id.tutorial_time);
            tutorialStudentCount = (TextView) itemView.findViewById(R.id.tutorial_student_count);

            itemView.setOnClickListener(this);
        }

        public void bindTutorial (Tutorial tutorialItem) {
            this.tutorialItem = tutorialItem;
            tutorialName.setText(tutorialItem.getName());
            tutorialTime.setText(tutorialItem.getTime());
            tutorialDay.setText(tutorialItem.getDay());
            tutorialStudentCount.setText(String.valueOf(tutorialItem.getStudentCount()));
        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();

            DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
            StudentsContract studentsContract = new StudentsContract(dbOpenHelper);

            if (studentsContract.getStudentsList(tutorialItem.getTutorialID()) != null) {
                Log.d("TutAdapt tutID", String.valueOf(tutorialItem.getTutorialID()));
                tutorialsActivity.updateFragmentList(tutorialItem.getTutorialID());
            }
            tutorialsActivity.setStudentListTitle(tutorialItem.getName());

            Button newStudentButton = (Button) tutorialsActivity.findViewById(R.id.newStudentButton);
            newStudentButton.setVisibility(VISIBLE);
        }
    }
}
