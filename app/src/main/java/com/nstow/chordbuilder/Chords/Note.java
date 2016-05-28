package com.nstow.chordbuilder.Chords;

/**
 * Created by nikolasstow on 11/8/15.
 */
public class Note {
    String letter = ""; // Letter of the note
    int halfStep = 0; // Indicates half step distance from A
    boolean duplicate = false; // Whether or not the note is a duplicate

    public Note(String l, int hS) { // Setting letter and halfstep values
        letter = l;
        halfStep = hS;
    }
    public void isDuplicate() { // Setting duplicate boolean to true with a function
        duplicate = true;
    }
    public void upOctave() { // Move the note an octave up
        halfStep += 12;
    }
    public void downOctave() { // Move the note an octave down
        halfStep -= 12;
    }
}