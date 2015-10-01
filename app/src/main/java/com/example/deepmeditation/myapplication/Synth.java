package com.example.deepmeditation.myapplication;

import android.util.Log;

/**
 * Created by Deep Meditation on 10/31/2014.
 */
public class Synth {
    /** Native methods, implemented in jni folder */
    public native void play(float carrierFreq,float lfoFreq, double fmValue, float fmDepth, int attack, int release);
    public native void stopProcess();

    static{
        System.loadLibrary("play_audio");
    }

    Thread audioThread;
    //private DataHolder data = DataHolder.getInstance();
    private float  carrierFreq = 440;
    private float lfoFreq = DataHolder.getInstance().getLfo();
    private int fmProgValue = DataHolder.getInstance().getFmProgValue();
    private double fmRatio = calcFmRatio(fmProgValue);
    private float fmDepth = DataHolder.getInstance().getFmDepth();
    private float modulatorFreq = 100;
    public boolean keyPressed = false;

    public void setCarrierFreq(float freq){
        carrierFreq = freq;
    }


    public void setModFreq(float freq){
        carrierFreq = freq;
    }

    private double calcFmRatio(int fmProgress){
        Log.d("Progress",fmProgress+"");
        fmProgress -= 5;
        double fmRatio = (fmProgress > 0 ? fmProgress + 1 :1.0/(Math.abs(fmProgress)+1));
        Log.d("fmRatio",fmRatio+"");
        return fmRatio;
    }

    void setMidiFreq(int midiNote){
        double  exponent =  (midiNote - 69.0)/12.0;
        double midiPower = Math.pow(2.0, exponent);
        carrierFreq = (float) (midiPower * 440);
        Log.d("Freq",carrierFreq+"");
        modulatorFreq = (float) carrierFreq / 4;
    }
    public void playSound() {
        lfoFreq =  DataHolder.getInstance().getLfo();
        //g.d("fmRatio",fmRatio+"");
         audioThread = new Thread() {
            public void run() {
                setPriority(Thread.MAX_PRIORITY);
                play(carrierFreq,lfoFreq,fmRatio,fmDepth,1000,100);
            }
        };
        audioThread.start();
    }
}
