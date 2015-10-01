package com.example.deepmeditation.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class SynthSettingsActivity extends Activity {
    SeekBar lfo_bar;
    SeekBar fm_bar;
    SeekBar fm_depth_bar;
    DataHolder data = DataHolder.getInstance();
    TextView lfoMonitor;
    TextView fmMonitor;
    TextView fm_depth_monitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synth_settings);
        lfoMonitor = (TextView) findViewById(R.id.lfo_monitor);
        lfoMonitor.setText(data.getLfo()+"Hz");
        fmMonitor = (TextView) findViewById(R.id.fm_monitor);
        fm_bar = (SeekBar) findViewById(R.id.fm_bar);
        fm_depth_bar = (SeekBar) findViewById(R.id.fm_depth_bar);
        fm_depth_bar.setProgress(data.getFmDepthProgressValue());
        fm_depth_monitor = (TextView) findViewById(R.id.fm_depth_monitor);
        fm_depth_monitor.setText(data.getFmDepth()+"");
        int fmProgress = data.getFmProgValue();
        fm_bar.setProgress(data.getFmProgValue());
        fmProgress = fmProgress - 5;
        String fmRatioMonitor = (fmProgress > 0 ? "1:"+(fmProgress+1) : (Math.abs(fmProgress) + 1) +":1");
        fmMonitor.setText(fmRatioMonitor);
        lfo_bar = (SeekBar) findViewById(R.id.lfo_progress);
        int lfoProgress = (int)(data.getLfo() * 10);
        lfo_bar.setProgress(lfoProgress);
        lfo_bar.setOnSeekBarChangeListener(onSeekBarChange);
        fm_bar.setOnSeekBarChangeListener(onSeekBarChange);
        fm_depth_bar.setOnSeekBarChangeListener(onSeekBarChange);
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChange =
        new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                if(seekBar.equals(lfo_bar)){
                    float lfoValue = (float)(progress / 10.0);
                    lfoMonitor.setText(lfoValue+"Hz");
                    data.setLfo(lfoValue);
                }
                else  if(seekBar.equals(fm_bar)){
                    int fmProgress = (progress - 5);
                    String ratioMonitor = (fmProgress > 0 ? "1:"+(fmProgress + 1)  :(Math.abs(fmProgress)+1)+":1");
                    data.setFmProgValue(progress);
                    fmMonitor.setText(ratioMonitor);
                }
                else if(seekBar.equals(fm_depth_bar)){
                    float fmDepth = (float) (progress / 100.0);
                    fm_depth_monitor.setText(fmDepth+"");
                    data.setFmDepth(fmDepth);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar lfo){
            }
            @Override
            public void onStopTrackingTouch(SeekBar lfo){
            }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.configure_synth, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_piano) {
            Intent intent = new Intent(this, MyActivity.class);
            startActivity(intent);
            return true;
        }else  if (id == R.id.action_config_scale) {
            Intent intent = new Intent(this, ScaleActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
