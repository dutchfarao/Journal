package com.example.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {
    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView header = view.findViewById(R.id.EntryHeader);
        TextView timestamp = view.findViewById(R.id.EntryTimestamp);
        ImageView image = view.findViewById(R.id.EntryImage);
        header.setText(cursor.getString(1));
        timestamp.setText(cursor.getString(4));
        int mood = cursor.getInt(3);
        if (mood == 1){
            image.setImageResource(R.drawable.mood1);
        }
        if (mood == 2){
            image.setImageResource(R.drawable.mood2);
        }
        if (mood == 3){
            image.setImageResource(R.drawable.mood3);
        }
        if (mood == 4){
            image.setImageResource(R.drawable.mood4);
        }


    }
}
