package com.example.koenraad.Exigentia;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koenraad.Exigentia.R;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MapsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final ImageButton emergencyButton = (ImageButton) findViewById(R.id.imageButton);
        emergencyButton.setVisibility(View.VISIBLE);
        emergencyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //when play is clicked show stop button and hide play button
                emergencyButton.setVisibility(View.GONE);
                PulsatorLayout pulsator = (PulsatorLayout) findViewById(R.id.pulsator);
                pulsator.start();
                display_some_text();

                final Button see_location_button = (Button) findViewById(R.id.location_button);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        see_location_button.setVisibility(View.VISIBLE);
                    }
                }, 5000);


                Server_interaction s_i = new Server_interaction(getApplication());
                s_i.post_request(new VolleyCallback()
                {
                    @Override
                    public void onSuccess(String text)
                    {
                        if(text == null)
                        {
                            Log.i(TAG, "it was null");
                        }
                        else
                        {
                            Log.i(TAG, "not null");
                            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                        }

                    }
                });





                see_location_button.setOnClickListener(new View.OnClickListener(){


                    @Override
                    public void onClick(View v)
                    {
                        Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
                        startActivity(myIntent);
                    }
            });

            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    private void display_some_text()
    {
        final TextView first_text = (TextView) findViewById(R.id.textview1);
        first_text.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                first_text.setVisibility(View.GONE);
            }
        }, 5000);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
