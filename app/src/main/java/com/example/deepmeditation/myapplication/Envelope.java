//package com.example.deepmeditation.myapplication;
//
//import android.util.Log;
//
///**
// * Created by Deep Meditation on 11/5/2014.
// */
//public class Envelope {
//    int releaseTime = 10000;
//    int releaseCounter = releaseTime;
//    private boolean releaseFlag = false;
//    private boolean attackFlag = true;
//    private int attackTime = 10000;
//    private int attackCounter = 0;
//    double sustain = 1.0;
//    double amp;
//    private DataHolder data = DataHolder.getInstance();
//    private final int sr = data.getSampleRate();
//    private int bufferSize = DataHolder.getInstance().getBufferSize();
//
//    public Envelope(int a, double s, int r){
//        attackTime = this.msToSamples(a);
//        releaseTime = this.msToSamples(r);
//        releaseCounter = releaseTime;
//        sustain = s;
//
//    }
//
//    public double getAmp(){
//        if(attackFlag == true){
//            attackCounter += bufferSize;
//            if(attackCounter >=attackTime){
//                amp = (float) attackCounter / attackTime;
//                return sustain * amp;
//            }else{
//                attackCounter = 0;
//                attackFlag = false;
//            }
//        }else if(releaseFlag == true ){
//            releaseCounter -= bufferSize;
//            if(releaseCounter >=0 || amp > 0.01){
//                amp = (float)releaseCounter /releaseTime;
//                return sustain * amp;
//            }else{
//                releaseFlag = false;
//                releaseCounter = releaseTime;
//                return 0;
//            }
//        }
//        return sustain;
//    }
//
//    private int msToSamples(int ms){
//        return  Math.round(ms * (sr/1000));
//    }
//
//    public void setReleaseFlag(boolean flag){
//        releaseFlag = flag;
//    }
//
//    public boolean getReleaseFlag(){
//        return releaseFlag;
//    }
//
//    public void setAttackFlag(boolean flag){
//        attackFlag = flag;
//    }
//
//    public boolean getAttackFlag(){
//        return attackFlag;
//    }
//
//    public void setSustain(float sustain){
//        this.sustain = sustain;
//    }
//}
