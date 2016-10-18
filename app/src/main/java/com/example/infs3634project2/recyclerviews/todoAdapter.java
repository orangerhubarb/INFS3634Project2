package com.example.infs3634project2.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.model.Tutorial;
import com.example.infs3634project2.views.StudentProfile;
import com.example.infs3634project2.views.StudentsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Davian on 6/10/16.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoHolder> {

    private static List<String> todoList;
    private StudentProfile studentProfile;

    public TodoAdapter(List<String> todoList, StudentProfile studentProfile) {
        this.todoList = todoList;
        this.studentProfile = studentProfile;
    }

    @Override
    public TodoAdapter.TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item_row, parent, false);
        Log.d("Debug", "View inflated!");

        return new TodoHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(TodoHolder holder, int position) {
        String todo = todoList.get(position);
        Log.d("Debug", "View binded!");
        holder.bindProject(todo);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class TodoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //
        private TextView todoItem;
        private ImageButton delete;

        public TodoHolder(View itemView) {
            super(itemView);
            todoItem = (TextView) itemView.findViewById(R.id.todo_item);
            delete = (ImageButton) itemView.findViewById(R.id.rubbishbin);
            delete.setOnClickListener(this);

        }

        public void bindProject(String todo) {
            todoItem.setText(todo);
        }

        @Override
        public void onClick(View v) {
                removeAt(getAdapterPosition());
        }

        public void removeAt(int position) {
            todoList.remove(position);
            notifyDataSetChanged();
            studentProfile.recalculateTodo(todoList);
        }
    }
}


