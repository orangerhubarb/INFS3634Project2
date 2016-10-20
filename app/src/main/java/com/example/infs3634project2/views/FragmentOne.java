package com.example.infs3634project2.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class FragmentOne extends Fragment {

    private TextView studentZID;
    private TextView studentDegree;
    private TextView studentYear;
    private Student mStudent;

    public FragmentOne() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);

        mStudent = ((StudentProfileTabs) this.getActivity()).getStudent();

        studentZID = (TextView) view.findViewById(R.id.zIDTextView);
        studentDegree = (TextView) view.findViewById(R.id.degreeTextView);
        studentYear = (TextView) view.findViewById(R.id.yearOfDegreeTextView);

        studentZID.setText("(" + mStudent.getzID() + ")");
        studentDegree.setText(mStudent.getDegree());
        studentYear.setText(String.valueOf(mStudent.getYearOfDegree()));

        return view;
    }
}
