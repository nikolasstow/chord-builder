package com.nstow.chordbuilder.Chords;

import java.util.ArrayList;

/**
 * Created by nikolasstow on 5/27/16.
 */
public class ChordType {
    ArrayList<Note> notes;
    ArrayList<Integer> halfSteps = new ArrayList<Integer>();
    String type;

    public ChordType(ArrayList<Note> notes) {
        this.notes = notes;
        this.halfSteps = new ArrayList<Integer>();

        for(int i = 0; i < notes.size(); i++)
            halfSteps.add(notes.get(i).halfStep); // Separate halfsteps from arranged notes

        String steps = String.valueOf(halfSteps); // Convert to string
        switch (steps) {
            case "[0, 1]": type = "m2"; /* Minor Second */
                break;
            case "[0, 2]": type = "M2"; /* Major Second */
                break;
            case "[0, 3]": type = "m3"; /* Minor Third */
                break;
            case "[0, 4]": type = "M3"; /* Major Third */
                break;
            case "[0, 5]": type = "4"; /* Perfect Fourth */
                break;
            case "[0, 6]": type = "TT"; /* Tritone */
                break;
            case "[0, 7]": type = "5"; /* Fifth AKA Power Chord */
                break;
            case "[0, 8]": type = "m6"; /* Minor Sixth */
                break;
            case "[0, 9]": type = "M6"; /* Major Sixth */
                break;
            case "[0, 10]": type = "m7"; /* Minor Seventh */
                break;
            case "[0, 11]": type = "M7"; /* Major Seventh */
                break;
            case "[0, 3, 7]": type = "m"; /* Minor Chord */
                break;
            case "[0, 3, 6]": type = "o"; /* Diminished Chord */
                break;
            case "[0, 3, 6, 9]": type = "o7";  /* Fully Diminished 7th Chord */
                break;
            case "[0, 3, 6, 10]": type = "\u00F87";  /* Half Diminished 7th Chord */
                break;
            case "[0, 3, 7, 10]": type = "min7"; /* Minor 7th Chord */
                break;
            case "[0, 3, 7, 11]": type = "mM7";  /* Minor Major 7th Chord */
                break;
            case "[0, 4, 7]": type = ""; /* Major Chord */
                break;
            case "[0, 4, 8]": type = "+"; /* Augmented Chord */
                break;
            case "[0, 4, 7, 10]": type = "7"; /* Dominant 7th Chord */
                break;
            case "[0, 4, 7, 11]": type = "maj7";  /* Major 7th Chord */
                break;
            default: type = "None";
                break;
        }
    }
}
