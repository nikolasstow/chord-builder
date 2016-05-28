package com.nstow.chordbuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nstow.chordbuilder.Adapters.MainListViewAdapter;
import com.nstow.chordbuilder.Chords.ChordBuilder;
import com.nstow.chordbuilder.Chords.NoteValues;
import com.nstow.chordbuilder.Chords.getFlatSharpValues;

public class MainActivity extends AppCompatActivity {

    ListView listView_notes;
    ChordBuilder chordBuilder = new ChordBuilder();
    TextView notes;
    TextView chordText;
    NoteValues[] noteValuesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteValuesData = new NoteValues[] {
                new NoteValues(0,"A"),
                new NoteValues(2,"B"),
                new NoteValues(4,"C"),
                new NoteValues(5,"D"),
                new NoteValues(7,"E"),
                new NoteValues(8,"F"),
                new NoteValues(10,"G")
        };

        MainListViewAdapter adapter = new MainListViewAdapter(this,
                R.layout.listview_notes_item, noteValuesData);

        listView_notes = (ListView)findViewById(R.id.listview_notes);
        listView_notes.setAdapter(adapter);

        notes = (TextView) findViewById(R.id.notes);
        chordText = (TextView) findViewById(R.id.chord);
    }

    public void addNoteButton(View v) {
        NoteValues thisNote = processNote(v);
        chordBuilder.addNote(thisNote.title,thisNote.value);
        updateText();
    }

    public void goBack(View v) {
        chordBuilder.deleteLast(); // Delete last note
        updateText(); // Update text to reflect deletion
    }

    private void updateText() {
        TextView notes = (TextView) findViewById(R.id.notes);
        notes.setText(chordBuilder.getNoteMath()); // Set the text in "Note Math"
        TextView chordText = (TextView) findViewById(R.id.chord);
        chordText.setText("= " + chordBuilder.getChord()); // Set the chord Text
    }

    private NoteValues processNote(View v) {
        LinearLayout parentView = (LinearLayout)v.getParent();
        int position = listView_notes.getPositionForView(parentView);

        getFlatSharpValues noteInput = new getFlatSharpValues(noteValuesData[position].value);

        NoteValues noteOutput = null;

        switch (v.getId()) {
            case R.id.natural:
                noteOutput = new NoteValues(noteInput.natural, noteValuesData[position].title);
                break;
            case R.id.flat:
                noteOutput = new NoteValues(noteInput.flat, noteValuesData[position].title + getString(R.string.flat));
                break;
            case R.id.doubleFlat:
                noteOutput = new NoteValues(noteInput.doubleFlat, noteValuesData[position].title + getString(R.string.doubleFlat));
                break;
            case R.id.sharp:
                noteOutput = new NoteValues(noteInput.sharp, noteValuesData[position].title + getString(R.string.sharp));
                break;
            case R.id.doubleSharp:
                noteOutput = new NoteValues(noteInput.doubleSharp, noteValuesData[position].title + getString(R.string.doubleSharp));
                break;
        }

        return noteOutput;
    }

}
