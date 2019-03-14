package com.example.journal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static com.example.journal.EntryDatabase.getInstance;

public class MainActivity extends AppCompatActivity {
    EntryDatabase db;
    EntryAdapter adapter;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create listener for floating action button
        FloatingActionButton btn = findViewById(R.id.fab);
        onClick onButtonClick = new onClick();
        btn.setOnClickListener(onButtonClick);

        //create listeners for both normal click and long click
        ListView listView = findViewById(R.id.ListView);
        //normal
        ListviewListener OnListClick = new ListviewListener();
        listView.setOnItemClickListener(OnListClick);
        //long
        LongListviewListener OnLongListClick = new LongListviewListener();
        listView.setOnItemLongClickListener(OnLongListClick);



        db = getInstance(getApplicationContext());
        cursor = db.selectAll();
        adapter = new EntryAdapter (this, cursor);



        listView.setAdapter(adapter);

    }

    //creation of onclick for button
    private class onClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intent);

        }
    }


    private class ListviewListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //set cursor to clicked journal entry and get all info
            Cursor clickedJournalEntry = (Cursor) parent.getItemAtPosition(position);
            String header = clickedJournalEntry.getString(1);
            String content = clickedJournalEntry.getString(2);
            Integer mood = clickedJournalEntry.getInt(3);
            String timestamp = clickedJournalEntry.getString(4);

            //set intent and create bundle
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("header", header);
            bundle.putString("content", content);
            bundle.putInt("mood", mood);
            bundle.putString("timestamp", timestamp);
            //open detail page
            intent.putExtras(bundle);
            startActivity(intent);

        }

    }

    private class LongListviewListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            //check which item is being long clicked
            Cursor LongClickedJournalEntry = (Cursor) parent.getItemAtPosition(position);
            Integer index = LongClickedJournalEntry.getColumnIndex("_id");
            Integer columnId = LongClickedJournalEntry.getInt(index);
            //delete row and update
            db.deleteRow(columnId);
            updater();



            return true;
        }
    }

    private void updater () {
        adapter.swapCursor(db.selectAll());
    }
}
