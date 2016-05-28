package com.nstow.chordbuilder.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nstow.chordbuilder.Chords.NoteValues;
import com.nstow.chordbuilder.R;

/**
 * Created by nikolasstow on 5/26/16.
 */
public class MainListViewAdapter extends ArrayAdapter<NoteValues> {

    Context context;
    int layoutResourceId;
    NoteValues data[] = null;

    public MainListViewAdapter(Context context, int layoutResourceId, NoteValues[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RelativeLayout title = null;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            title = (RelativeLayout)row.findViewById(R.id.natural);

            row.setTag(title);
        } else {
            title = (RelativeLayout) row.getTag();
        }

        NoteValues chord = data[position];

        TextView titleText = (TextView) title.getChildAt(0);
        titleText.setText(chord.title);

        return row;

    }
}
