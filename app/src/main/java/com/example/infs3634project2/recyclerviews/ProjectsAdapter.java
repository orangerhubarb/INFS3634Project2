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
import com.example.infs3634project2.views.StudentProfile;
import com.example.infs3634project2.views.StudentsActivity;

import java.util.ArrayList;

/**
 * Created by Davian on 6/10/16.
 */

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsHolder> {

    private ArrayList<String> projects;

    public ProjectsAdapter(ArrayList<String> projects) {
        this.projects = projects;
    }

    @Override
    public ProjectsAdapter.ProjectsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.projects_item_row, parent, false);
        Log.d("Debug", "View inflated!");

        return new ProjectsHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ProjectsHolder holder, int position) {
        String project = projects.get(position);
        Log.d("Debug", "View binded!");
        holder.bindProject(project);
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public static class ProjectsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
        private TextView projectName;

        public ProjectsHolder(View itemView) {
            super(itemView);

            projectName = (TextView) itemView.findViewById(R.id.project_name);

            itemView.setOnClickListener(this);

        }

        public void bindProject(String project) {
            projectName.setText(project);
        }

        @Override
        public void onClick(View v) {

        }
    }
}


