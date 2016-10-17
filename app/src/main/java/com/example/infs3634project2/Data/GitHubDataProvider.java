package com.example.infs3634project2.Data;

import android.content.Context;
import android.util.Log;

/**
 * Created by student on 17/10/2016.
 */

public class GitHubDataProvider{

    private Context context;
    GitHubCallback mCallback = null;

    public GitHubDataProvider(Context context) {
        this.context = context;
    }

    public void setmListener (GitHubCallback callback) {
        mCallback = callback;
    }

    public void getGitProject(final String url) {
        Log.d("Debug", url);
        mCallback.onTaskCompleted();

    }
}
