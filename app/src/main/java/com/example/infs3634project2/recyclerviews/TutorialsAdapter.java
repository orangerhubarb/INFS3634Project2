package com.example.infs3634project2.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.model.Tutorial;
import com.example.infs3634project2.views.StudentsActivity;

import java.util.ArrayList;

/**
 * Created by Davian on 6/10/16.
 */
public class TutorialsAdapter extends RecyclerView.Adapter<TutorialsAdapter.TutorialsHolder> {

    private ArrayList<Tutorial> mTutorial;

    public TutorialsAdapter(ArrayList<Tutorial> tutorialList) {
        this.mTutorial = tutorialList;
    }

    @Override
    public TutorialsAdapter.TutorialsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tutorials_item_row, parent, false);
        Log.d("Debug", "View inflated!");

        return new TutorialsHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(TutorialsHolder holder, int position) {
        Tutorial itemTutorial = mTutorial.get(position);
        Log.d("Debug", "View binded!");
        if (itemTutorial.getStudents() != null) {
            Log.d("Debug", itemTutorial.getStudents().toString());
        }
        holder.bindTutorial(itemTutorial);

    }

    @Override
    public int getItemCount() {
        return mTutorial.size();
    }

    public static class TutorialsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tutorialName;
        private Tutorial tutorialItem;

        public TutorialsHolder(View itemView) {
            super(itemView);

            tutorialName = (TextView) itemView.findViewById(R.id.tutorial_name);

            itemView.setOnClickListener(this);


        }

        public void bindTutorial (Tutorial tutorialItem) {
            this.tutorialItem = tutorialItem;
            tutorialName.setText(tutorialItem.getName());

        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            Intent showStudent = new Intent(context, StudentsActivity.class);
            if (tutorialItem.getStudents() != null) {
                ArrayList<Student> studentList;
                studentList = tutorialItem.getStudents();
                Log.d("Debug", studentList.get(0).toString());
                showStudent.putExtra("STUDENTS", studentList);
            }

            context.startActivity(showStudent);
        }
    }
}
