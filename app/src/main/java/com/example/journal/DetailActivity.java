package com.example.journal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String header = bundle.getString("header");
        String content = bundle.getString("content");
        Integer mood = bundle.getInt("mood");
        String timestamp = bundle.getString("timestamp");

        //assign the strings and ints to appropiate fields
        ImageView moodpic = findViewById(R.id.DetailDrawable);
        TextView headertxt =  findViewById(R.id.DetailHeader);
        TextView contenttxt = findViewById(R.id.DetailText);
        TextView timestamptxt = findViewById(R.id.DetailTimestamp);


        switch (mood) {
            case 1:
                moodpic.setImageResource(R.drawable.mood1);
                break;
            case 2:
                moodpic.setImageResource(R.drawable.mood2);
                break;
            case 3:
                moodpic.setImageResource(R.drawable.mood3);
                break;
            case 4:
                moodpic.setImageResource(R.drawable.mood4);
                break;
        }
        headertxt.setText(header);
        contenttxt.setText(content);
        timestamptxt.setText(timestamp);


    }
}
