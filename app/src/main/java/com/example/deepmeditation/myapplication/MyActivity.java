package com.example.deepmeditation.myapplication;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.Intent;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        final Synth fmSynth = new Synth();
        final String scaleCarrier = DataHolder.getInstance().getScaleCarrier();
        final int octave = DataHolder.getInstance().getOctave();
        final Scale scale = new Scale(scaleCarrier,octave);
        final Pallete pallete = new Pallete();
        final int[] buttonColors = pallete.getColors("fancy");
        final int[] notes = scale.getNotes();
        final String[] noteSymbols = scale.getNoteSymbols();
        LinearLayout buttonsLayout = (LinearLayout)findViewById(R.id.buttons_layout);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        final Button musicButtons[] = new Button[notes.length];

        for(int i=0;i<notes.length;i++){
            musicButtons[i] = new Button(this);
            final Button theButton = musicButtons[i];
            theButton.setId(notes[i]);
            theButton.setLayoutParams(param);
            theButton.setText(noteSymbols[i]);
            Log.d("Button Color",buttonColors[i]+"");
            theButton.setBackgroundColor(buttonColors[i]);
            buttonsLayout.addView(theButton);

            theButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v,MotionEvent event){
                    float yPos;
                    switch(event.getAction())
                    {
                        case MotionEvent.ACTION_DOWN:
                            yPos = event.getY();
                            fmSynth.keyPressed = true;
                            fmSynth.setMidiFreq(theButton.getId());
                            fmSynth.playSound();
                        break;
                        case MotionEvent.ACTION_MOVE:
                        break;
                        case MotionEvent.ACTION_UP:
                            fmSynth.stopProcess();
                        break;
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if (id == R.id.action_config_scale) {
            Intent intent = new Intent(this, ScaleActivity.class);
            startActivity(intent);
            return true;
        }else  if (id == R.id.action_config_synth) {
            Intent intent = new Intent(this, SynthSettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


