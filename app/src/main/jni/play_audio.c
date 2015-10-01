#include "com_example_deepmeditation_myapplication_Synth.h"
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>
#include <android/log.h>
#include "opensl_io.c"

#define BUFFERFRAMES 512
#define VECSAMPS_MONO 64
#define SR 44100
#define VOICES 4


static int on;
static float amp;
static float carrierFreq;
static long sampleIndex = 0;
static float attackCounter = 0;
static double envelopeAmp;
static float releaseCounter;
static float releaseSamples;
static bool releaseFlag = false;

float sine(long index, float freq){
    return sin(2*M_PI*(index /(SR/freq)));
}

void Java_com_example_deepmeditation_myapplication_Synth_play(JNIEnv* env,jclass clazz,jfloat carrierFreq,jfloat lfoFreq, jdouble fmValue, jfloat fmDepth, jint attack,jint release){
  OPENSL_STREAM  *p;
  int i;
  float attackSamples = msToSamples(attack);
  releaseSamples = msToSamples(release);
  double fmFreq = carrierFreq * fmValue;
  float  outbuffer[BUFFERFRAMES];
  __android_log_print(ANDROID_LOG_INFO, "opening device", "1");
  p = android_OpenAudioDevice(SR,0,2,BUFFERFRAMES);
  if(p == NULL) return;
  on = 1;
  float lfo;

  while(on || releaseFlag == true) {
   //__android_log_print(ANDROID_LOG_INFO, "playing note", "1");
   for(i = 0; i < BUFFERFRAMES; i++){
     sampleIndex++;
     if(lfoFreq <= 0){
        lfo = 1;
     }else{
        lfo = sine(sampleIndex, lfoFreq);
     }
     if(attackCounter < attackSamples && releaseFlag == false){
        attackCounter++;
        envelopeAmp=(double) (attackCounter / attackSamples);
    }
    if(releaseCounter > 0 && releaseFlag == true){
        releaseCounter--;
        envelopeAmp = (double) (envelopeAmp)* (releaseCounter/releaseSamples);
    }else {
        releaseFlag = false;
        releaseCounter = 0;
    }

     outbuffer[i] = (envelopeAmp* (0.9 * lfo )) * sine(sampleIndex,carrierFreq+(fmDepth*sine(sampleIndex,fmFreq)));
   }
   android_AudioOut(p,outbuffer,BUFFERFRAMES);
  }
  releaseFlag = false;
  sampleIndex = 0;
  android_CloseAudioDevice(p);
  __android_log_print(ANDROID_LOG_INFO, "closing device", "1");
}


void Java_com_example_deepmeditation_myapplication_Synth_stopProcess(JNIEnv* env, jclass clazz){
    on = 0;
    attackCounter = 0;
    releaseCounter = releaseSamples;
    releaseFlag = true;
}

int msToSamples(int ms){
   return  ms * (SR/1000);
}


