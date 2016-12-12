package com.example.jim.hackernewsreader;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by jim on 12/9/16.
 */

public class ReadStoriesJsonTask extends AsyncTask<String, Void, String> {
    JSONGetter jsonGetter = new JSONGetter();
    JSONArray postIDS;
    String baseUrl;
    ArrayAdapter adapter;

    public ReadStoriesJsonTask(ArrayAdapter adapter, String baseUrl) {
        this.adapter = adapter;
        this.baseUrl = baseUrl;
    }
    protected String doInBackground(String... urls) {
        String result =  jsonGetter.getJSON(urls[0]);
        try {
            postIDS = new JSONArray(result);
        }
        catch (JSONException e) {
            Log.d("JSONERROR", "exception", e);
        }
        return "";
    }

    protected void onPostExecute(String result) {
        try {
            for (int i = 0; i < 30; i++) {
                String storyUrl = jsonGetter.buildQuery(baseUrl, postIDS.getString(i));
                new ReadStoryJsonTask(adapter).execute(storyUrl);
            }
        }
        catch (JSONException e) {
            Log.d("JSONERROR", "exception", e);
        }
    }
}
