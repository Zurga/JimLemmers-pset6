package com.example.jim.hackernewsreader;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by jim on 12/9/16.
 */

public class JSONGetter {
    public String buildQuery(String baseUrl, String itemNumber){
        //URL encode the query from the user
        try {
            String encoded_query = URLEncoder.encode(itemNumber, "UTF-8");
            return String.format(baseUrl + "item/%s.json",
                    encoded_query);
        } catch (UnsupportedEncodingException e) {
            Log.d("BUILDQUIRY", "FAYL", e);
            return "";
            // TODO create a Toast that says that the user can't type.
        }
    }

    public String getJSON(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200){
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null){
                    stringBuilder.append(line);
                    //Log.d("JSON", line);
                }
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download");
            }
        } catch (Exception e) {
            Log.d("readJsonFEED", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }
}
