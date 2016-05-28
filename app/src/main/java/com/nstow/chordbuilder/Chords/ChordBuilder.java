package com.nstow.chordbuilder.Chords;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolasstow on 11/8/15.
 */
public class ChordBuilder {

    ArrayList<Note> notes = new ArrayList<Note>(); // Notes array, in order by input
    ArrayList<Note> arrangedNotes = new ArrayList<Note>(); // Notes arranged in order by value

    public void addNote(String letter, int halfStep) {
        if(notes.size() < 4) { // Only takes up to 4 notes at the moment, should be updated later to accept ninth chords
            notes.add(new Note(letter, halfStep)); // Create new note object with values above and add it to the note array
            analyzeNotes(); // Send the notes off for analysis
        }
    }

    public void deleteLast() {
        if(notes.size() > 0) { // Only delete if there is something to delete
            notes.remove(notes.size() - 1); // remove the last note from the notes array
            analyzeNotes(); // Reanalyze the notes
        }
    }

    private void analyzeNotes() {
        for(int i = 0; i < notes.size(); i++)
            for(int e = 0; e < notes.size(); e++)
                if(i > e && notes.get(i).letter.equals(notes.get(e).letter))
                    notes.get(i).isDuplicate(); //indentify duplicates

        if(notes.size() > 2) { // No point in rearranging notes if there is only two of them
            rearrangeNotes();
        } else if(notes.size() == 2) { // Just a diad not a chord
            arrangedNotes.clear();
            int noteA = Integer.valueOf(notes.get(0).halfStep);
            int noteB = Integer.valueOf(notes.get(1).halfStep);
            arrangedNotes.add(new Note(notes.get(0).letter, 0));
            arrangedNotes.add(new Note(notes.get(0).letter,
                    ((noteB + (noteA < noteB ? 0 : 12)) - noteA)));
        } else {
            arrangedNotes = (ArrayList<Note>) notes.clone();
        }
    }

    private void rearrangeNotes() {
        ArrayList<Note> arrangedNotesTemp = new ArrayList<Note>(); // Notes arranged in order
        arrangedNotesTemp.add(notes.get(0)); // Adding the first note to the arranged notes, as a base not to add to either before or after
        List<Note> unarrangedNotes = (ArrayList<Note>) notes.clone(); // Notes that have not yet been arranged
        unarrangedNotes.remove(0); // Remove the first note because it's already been arranged
        boolean error = false; // No errors yet
        while (unarrangedNotes.size() > 0 && !error) { // Repeat as long as there are unarranged notes, and no errors
            error = true; // "Guilty" until proven "innocent"
            for(int i = 0; i < unarrangedNotes.size(); i++) { // Run through every note that has not been
                int currentNote = unarrangedNotes.get(i).halfStep; // Get the current unarranged note
                int firstNote = arrangedNotesTemp.get(0).halfStep; // Get the first arranged note
                int lastNote = arrangedNotesTemp.get(arrangedNotesTemp.size() - 1).halfStep; // get the last arranged note
                error = false; // We've made it this far, so we can assume "innocent" for the time being
                if(unarrangedNotes.get(i).duplicate) { // Skip duplicate notes
                    unarrangedNotes.remove(i); // Delete note from unarranged list
                    i--; // Adjust counter
                }else if(currentNote + 3 == firstNote || currentNote + 4 == firstNote || currentNote - 9 == firstNote || currentNote - 8 == firstNote){ // Note before first note
                    if(currentNote > firstNote) unarrangedNotes.get(i).downOctave();
                    arrangedNotesTemp.add(0,unarrangedNotes.get(i)); // Add the unarranged note before the first arranged note
                    unarrangedNotes.remove(i); // Now that the note has been added to the arranged note, it can be removed from the unarranged list
                    i--; // Adjust counter
                }else if(currentNote - 3 == lastNote || currentNote - 4 == lastNote || currentNote + 9 == lastNote || currentNote + 8 == lastNote){ // Note after last note
                    if(currentNote < lastNote) unarrangedNotes.get(i).upOctave();
                    arrangedNotesTemp.add(unarrangedNotes.get(i)); // Add the unarranged note after the last arranged note
                    unarrangedNotes.remove(i); // Now that the note has been added to the arranged note, it can be removed from the unarranged list
                    i--; // Adjust counter
                }else {
                    error = true; // Nope, "Guilty". As far as my algorithm can detect, no chord has been built.
                }
            }
        }
        if(!error) {
            arrangedNotes = arrangedNotesTemp; //Change the notes list to the new arranged list
            int bassNum = arrangedNotes.get(0).halfStep * -1; // Get the opposite of the bass note's "Halfstep" value to use to reset to zero
            for(int i = 0; i < arrangedNotes.size(); i++)
                arrangedNotes.set(i,
                        new Note(arrangedNotes.get(i).letter,
                                (Integer.valueOf(arrangedNotes.get(i).halfStep) + bassNum))); // Resets the halfsteps to begin at zero
        } else arrangedNotes.clear(); // Clears the array, because nothing has been properly arranged
    }

    public String getNoteMath() {
        String noteMath = "";
        for (int i = 0; i < notes.size(); i++)
            noteMath += (i == 0 ? "" : " + ") + notes.get(i).letter; // Add the notes together with "+" symbols

        return String.valueOf(noteMath);
    }
    public String getChord() {
        if(notes.size() > 0) {
            ChordType builtChord = new ChordType(arrangedNotes);
            String rootNote = (arrangedNotes.size() > 0 ? arrangedNotes.get(0).letter : notes.get(0).letter); // Get the root note of the chord
            String bassNote = notes.get(0).letter; // get the bass note (the first note)
            return (builtChord.type == "None" ? "None" : (builtChord.type.equals("o7") || builtChord.type.equals("+") ? bassNote + String.valueOf(builtChord.type) : rootNote + String.valueOf(builtChord.type) + (!rootNote.equals(bassNote) ? "/" + bassNote : ""))); // Determine chord
        } else {
            return "None"; //Not a chord
        }
    }
}
