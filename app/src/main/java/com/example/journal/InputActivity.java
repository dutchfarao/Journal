package com.example.journal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputActivity extends AppCompatActivity {
JournalEntry journalentry = new JournalEntry();
int mood;
ImageButton moodOne;
ImageButton moodTwo;
ImageButton moodThree;
ImageButton moodFour;
Button btn;
EditText header;
EditText content;
String inputHeader;
String inputContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        moodOne = findViewById(R.id.InputMood1);
        moodTwo = findViewById(R.id.InputMood2);
        moodThree = findViewById(R.id.InputMood3);
        moodFour = findViewById(R.id.InputMood4);

        //create journalentry
        JournalEntry journalentry = new JournalEntry();

        //set onclick listener
        btn = findViewById(R.id.InputButton);
        OnClickListener SubmitListener = new OnClickListener();
        btn.setOnClickListener(SubmitListener);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //putting of mood
        outState.putInt("mood", mood);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        //getting of mood
        mood = inState.getInt("mood");
        switch (mood) {
            case 1:
                moodOne.setBackgroundResource(R.drawable.mood1selected);
                moodTwo.setBackgroundResource(R.drawable.mood2);
                moodThree.setBackgroundResource(R.drawable.mood3);
                moodFour.setBackgroundResource(R.drawable.mood4);
                break;
            case 2:
                moodOne.setBackgroundResource(R.drawable.mood1);
                moodTwo.setBackgroundResource(R.drawable.mood2selected);
                moodThree.setBackgroundResource(R.drawable.mood3);
                moodFour.setBackgroundResource(R.drawable.mood4);
                break;
            case 3:
                moodOne.setBackgroundResource(R.drawable.mood1);
                moodTwo.setBackgroundResource(R.drawable.mood2);
                moodThree.setBackgroundResource(R.drawable.mood3selected);
                moodFour.setBackgroundResource(R.drawable.mood4);
                break;
            case 4:
                moodOne.setBackgroundResource(R.drawable.mood1);
                moodTwo.setBackgroundResource(R.drawable.mood2);
                moodThree.setBackgroundResource(R.drawable.mood3);
                moodFour.setBackgroundResource(R.drawable.mood4selected);
                break;
        }

    }

    public void mood1 (View view){
        moodOne.setBackgroundResource(R.drawable.mood1selected);
        moodTwo.setBackgroundResource(R.drawable.mood2);
        moodThree.setBackgroundResource(R.drawable.mood3);
        moodFour.setBackgroundResource(R.drawable.mood4);
        mood = 1;
    }

    public void mood2 (View view){
        moodOne.setBackgroundResource(R.drawable.mood1);
        moodTwo.setBackgroundResource(R.drawable.mood2selected);
        moodThree.setBackgroundResource(R.drawable.mood3);
        moodFour.setBackgroundResource(R.drawable.mood4);
        mood = 2;
    }

    public void mood3 (View view){
        moodOne.setBackgroundResource(R.drawable.mood1);
        moodTwo.setBackgroundResource(R.drawable.mood2);
        moodThree.setBackgroundResource(R.drawable.mood3selected);
        moodFour.setBackgroundResource(R.drawable.mood4);
        mood = 3;

    }

    public void mood4 (View view){
        moodOne.setBackgroundResource(R.drawable.mood1);
        moodTwo.setBackgroundResource(R.drawable.mood2);
        moodThree.setBackgroundResource(R.drawable.mood3);
        moodFour.setBackgroundResource(R.drawable.mood4selected);
        mood = 4;

    }

    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //get input from user
            header = findViewById(R.id.InputHeader);
            content = findViewById(R.id.InputContent);

            inputHeader = header.getText().toString();
            inputContent = content.getText().toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String string  = dateFormat.format(new Date());


            //set input in entry
            journalentry.setTitle(inputHeader);
            journalentry.setContent(inputContent);
            journalentry.setMood(mood);
            journalentry.setTimestamp(string);

            //add entry and open homepage showing all entries
            addEntry();
            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }

    public void addEntry () {
        EntryDatabase entryDatabase = EntryDatabase.getInstance(this);
        entryDatabase.insert(journalentry);
    }

}
