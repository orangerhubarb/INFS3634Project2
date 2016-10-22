package com.example.infs3634project2.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.infs3634project2.Data.GitHubCallback;
import com.example.infs3634project2.Data.GitHubDataProvider;
import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Projects;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.recyclerviews.ProjectsAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class FragmentTwo extends Fragment implements GitHubCallback<ArrayList<Projects>> {

    private TextView studentStrength;
    private TextView studentWeakness;
    private RecyclerView projectsRecyclerView;
    private ProjectsAdapter projectsAdapter;
    private LinearLayoutManager projectsLinearLayoutManager;
    private Student mStudent;

    public FragmentTwo() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_two, container, false);

        mStudent = ((StudentProfileTabs) this.getActivity()).getStudent();

        studentStrength = (TextView) view.findViewById(R.id.strengthsTextView);
        studentWeakness = (TextView) view.findViewById(R.id.weaknessesTextView);
        projectsRecyclerView = (RecyclerView) view.findViewById(R.id.projectsRecyclerView);

        studentStrength.setText(mStudent.getStrengths());
        studentWeakness.setText(mStudent.getWeaknesses());

        String githubUser = mStudent.getGithubUsername();
        GitHubDataProvider gitHubDataProvider = new GitHubDataProvider(this.getContext());
        gitHubDataProvider.getGitProject(githubUser, this);

        return view;
    }


    @Override
    public void onTaskCompleted(ArrayList<Projects> listOfProjects) {
        Log.d("Debug", "URL has been passed");
        //Obviously after this has been obtained, then we set all the layout shit.

        projectsAdapter = new ProjectsAdapter(listOfProjects);
        projectsRecyclerView.setAdapter(projectsAdapter);

        projectsLinearLayoutManager = new LinearLayoutManager(this.getContext());
        projectsRecyclerView.setLayoutManager(projectsLinearLayoutManager);
        Log.d("Student List Return", listOfProjects.toString());
    }

    @Override
    public void onFailure(String fail) {

    }
}
