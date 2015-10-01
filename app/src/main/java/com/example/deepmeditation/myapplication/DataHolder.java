package com.example.deepmeditation.myapplication;

import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.AudioFormat;
import android.util.Log;

/**
 * Created by Deep Meditation on 10/22/2014.
 */

public class DataHolder {
    private String scaleCarrier = "C";
    private int octave = 4;

    private float lfo = 0;
    private int fmProgValue = 5;
    private float fmDepth = 0;

    public float getLfo(){
        return this.lfo;
    }
    public void setLfo(float lfo){
        this.lfo = lfo;
    }

    public int getFmProgValue(){
        return this.fmProgValue;
    }
    public void setFmProgValue(int fm){
        this.fmProgValue = fm;
    }

    public float getFmDepth(){
        return this.fmDepth;
    }
    public int getFmDepthProgressValue(){
        return (int)(this.fmDepth * 100);
    }
    public void setFmDepth(float fmDepth ){
        this.fmDepth = fmDepth;
    }

    public String getScaleCarrier(){
        return this.scaleCarrier;
    }

    public void setScaleCarrier(String carrier){
        this.scaleCarrier = carrier;
    }

    public void setOctave(int octave){
        this.octave = octave;
    }

    public int getOctave(){
        return this.octave;
    }

    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance(){
        return holder;
    }
}
