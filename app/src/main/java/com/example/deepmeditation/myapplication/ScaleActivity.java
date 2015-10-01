package com.example.deepmeditation.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

public class ScaleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_scale);

        final Spinner notesSpinner = (Spinner) findViewById(R.id.notes_spinner);
        final Spinner octaveSpinner = (Spinner) findViewById(R.id.octave_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> notesAdapter = ArrayAdapter.createFromResource(this,
                R.array.notes_array, android.R.layout.simple_spinner_item);

        final ArrayAdapter<CharSequence> octaveAdapter = ArrayAdapter.createFromResource(this,
                R.array.octave_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        notesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        octaveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        notesSpinner.setAdapter(notesAdapter);
        octaveSpinner.setAdapter(octaveAdapter);
        //display the currently selected value for spinner
        notesSpinner.setSelection(notesAdapter.getPosition(DataHolder.getInstance().getScaleCarrier()));
        octaveSpinner.setSelection(octaveAdapter.getPosition(String.valueOf(DataHolder.getInstance().getOctave())));

        notesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                DataHolder.getInstance().setScaleCarrier((String) parent.getItemAtPosition(pos));
                Log.v("Scale selected:", (String) parent.getItemAtPosition(pos));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        octaveSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                DataHolder.getInstance().setOctave(pos); //this is real punk attitude
                Log.v("Octave selected:", parent.getItemAtPosition(pos) +"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.configure_scale, menu);
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
        }else  if (id == R.id.action_config_synth) {
            Intent intent = new Intent(this, SynthSettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
