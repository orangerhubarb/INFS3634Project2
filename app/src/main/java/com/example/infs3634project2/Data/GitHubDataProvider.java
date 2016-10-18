package com.example.infs3634project2.Data;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by student on 17/10/2016.
 */

public class GitHubDataProvider{

    private Context context;
    ArrayList<String> listOfProjects = new ArrayList<>();

    public GitHubDataProvider(Context context) {
        this.context = context;
    }

    public void getGitProject(final String username, final GitHubCallback<ArrayList<String>> callback) {
        Log.d("Debug", username);
        String apiURL = "https://api.github.com/users/" + username + "/repos";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        final JsonArrayRequest listObject = new JsonArrayRequest(Request.Method.GET, apiURL,

                new Response.Listener<JSONArray>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for(int i = 0 ; i < response.length() ; i++) {
                                JSONObject currentProject = response.getJSONObject(i);
                                listOfProjects.add(currentProject.getString("name"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.d("Debug", listOfProjects.toString());
                        callback.onTaskCompleted(listOfProjects);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(listObject);
    }
}
