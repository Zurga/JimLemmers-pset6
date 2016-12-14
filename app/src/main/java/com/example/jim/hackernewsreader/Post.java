package com.example.jim.hackernewsreader;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Post {
    public String title;
    public String score;
    public String comments;
    public String url;
    public String domain;

    public Post() {
        // Required for the datasnapshot.
    }
    public Post(JSONObject post) {
        try {
            this.title = post.getString("title");
            this.score = post.getString("score");
            this.comments = post.getString("descendants");
            if (post.has("url")) {
                this.domain = post.getString("url").replaceAll("\\.[A-z]*(\\/.*)", "");
                this.url = post.getString("url");
            } else {
                this.url = "";
            }
        } catch (JSONException e) {
            Log.d("JSON", "parse error", e);
        }
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("score", score);
        result.put("comments", comments);
        result.put("url", url);
        result.put("domain", domain);
        return result;
    }
}
