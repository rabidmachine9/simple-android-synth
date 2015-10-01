package com.example.deepmeditation.myapplication;

import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Deep Meditation on 10/21/2014.
 */
public class Scale {

    private final String noteSymbols[] = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","B"};
    private int octave = 4;
    private final int major[] = {0,2,4,5,7,9,11};
    private final int notes[] = new int[7] ;
    private int carrier = 0;

    public Scale(String carrierNote, int octave){
        this.carrier = Arrays.asList(noteSymbols).indexOf(carrierNote);
        this.octave = octave;
    }

    public int[] getNotes(){
        for(int i =0;i < major.length;i++){
            notes[i] = (octave * 12) + (this.carrier + major[i]);
        }
        Log.d("Scale notes:", Arrays.toString(notes));
        return notes;
    }

    public String[] getNoteSymbols(){
        String symbols[] = new String[major.length];
        for(int i=0;i<major.length;i++){
            int symbolIndex = this.carrier + major[i];
            if( symbolIndex > 11){
                symbolIndex -= 12;
            }
            symbols[i] = noteSymbols[symbolIndex];
        }
        return symbols;
    }

    public void setOctave(int octave){
        this.octave = octave;
    }



}
