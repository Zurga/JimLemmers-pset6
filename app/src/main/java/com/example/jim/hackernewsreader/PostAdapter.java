package com.example.jim.hackernewsreader;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jim on 12/9/16.
 */

public class PostAdapter extends ArrayAdapter<Post> {
    private Context context;
    public PostAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post post = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView comments = (TextView) convertView.findViewById(R.id.comments);
        TextView score = (TextView) convertView.findViewById(R.id.score);
        TextView url = (TextView) convertView.findViewById(R.id.url);
        TextView domain = (TextView) convertView.findViewById(R.id.domain);

        title.setText(post.title);
        comments.setText(post.comments);
        score.setText(post.score);
        url.setText(post.url);
        domain.setText(post.domain);

        convertView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView urlView = (TextView) view.findViewById(R.id.url);
                String url = urlView.getText().toString();

                Intent showArticle = new Intent(getContext(), viewArticle.class);
                showArticle.putExtra("url", url);
                context.startActivity(showArticle);
            }
        });
        return convertView;

    }
}
