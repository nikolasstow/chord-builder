package com.nstow.chordbuilder.Chords;

/**
 * Created by nikolasstow on 5/27/16.
 */
public class getFlatSharpValues {
    public int natural;
    public int flat;
    public int doubleFlat;
    public int sharp;
    public int doubleSharp;

    public getFlatSharpValues(int value) {
        this.natural = value;
        this.flat = checkForNegativeValue(value - 1);
        this.doubleFlat = checkForNegativeValue(value - 2);
        this.sharp = checkForNegativeValue(value + 1);
        this.doubleSharp = checkForNegativeValue(value + 2);
    }

    public int checkForNegativeValue(int value) {
        if (value < 0) value = value + 12;
        return value;
    }
}
