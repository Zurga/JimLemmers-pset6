package com.example.jim.hackernewsreader;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jim on 12/9/16.
 */

public class ReadStoryJsonTask extends AsyncTask<String, Void, String> {
    JSONGetter jsonGetter = new JSONGetter();
    JSONObject posts;
    ArrayAdapter adapter;

    public ReadStoryJsonTask(ArrayAdapter adapter) {
        this.adapter = adapter;
    }
    protected String doInBackground(String... urls) {
        return jsonGetter.getJSON(urls[0]);
    }

    protected void onPostExecute(String result) {
        try {
            JSONObject postJSON = new JSONObject(result);
            Post post = new Post(postJSON);
            adapter.add(post);
        }
        catch (JSONException e) {
            Log.d("JSON", "Error", e);
        }
    }
}