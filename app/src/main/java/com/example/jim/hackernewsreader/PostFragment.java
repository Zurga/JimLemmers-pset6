package com.example.jim.hackernewsreader;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import org.json.JSONArray;
import org.json.JSONException;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.jim.hackernewsreader.R.id.container;

/**
 * Created by jim on 12/9/16.
 */

public class PostFragment extends Fragment {
    private String postType;
    private String urlSuffix;
    private JSONArray postIDS;
    private ArrayList<Post> posts ;
    private PostAdapter adapter;
    private String baseUrl;
    FragmentActivity listener;
    private JSONGetter jsonGetter = new JSONGetter();

    public static PostFragment newInstance(String postType, String urlSuffix, String baseUrl) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putString("baseUrl", baseUrl);
        args.putString("postType", postType);
        args.putString("urlSuffix", urlSuffix);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        posts = new ArrayList<Post>();
        adapter = new PostAdapter(getActivity(), posts);
        baseUrl = getArguments().getString("baseUrl");
        postType = getArguments().getString("postType");
        urlSuffix = getArguments().getString("urlSuffix");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, parent, false);
        TextView sectionLabel = (TextView) rootView.findViewById(R.id.section_label);
        sectionLabel.setText(postType);
        Log.d("POSTFRAGMENT", postType);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        if (adapter.getCount() != 30) {
            if (this.postType != "Favourites") {
                new ReadStoriesJsonTask(adapter, baseUrl).execute(baseUrl + urlSuffix);
            } else {
                new ReadFavourites(adapter);
            }
            ListView resultList = (ListView) view.findViewById(R.id.result_list);
            resultList.setAdapter(adapter);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        adapter.clear();
        adapter.notifyDataSetChanged();
    }
}
