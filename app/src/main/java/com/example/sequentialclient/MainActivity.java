///////////////////////////////////////////////////////////////
//                                                           //
//                                                           //
// Samantha Tolentino                                        //
// IT114, Section 003                                        //
// Dr. Halper                                                //
// App Project #2                                            //
// Dec. 4, 2019                                              //
//                                                           //
// This app is designed to perform a series of genomics      //
// string operations. The user will interact with this       //
// app, aka the client, and the inputted information will    //
// be sent to the server (seqserver.java) to be processed.   //
// The answers will then be returned and displayed in the    //
// Text View.                                                //
//                                                           //
//                                                           //
///////////////////////////////////////////////////////////////

package com.example.sequentialclient;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.StrictMode;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String HOST_NAME = "com.example.sequentialclient.HOSTNAME";
    public final static String PORT = "com.example.sequentialclient.PORT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Allows network access.

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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

    // Method to connect to the server.

    public void connectServer(View view)
    {

        EditText et1;
        EditText et2;
        String hostname;
        int port;
        Intent intent = new Intent(this, CommunicateActivity.class);

        // Get the host name and the port number.

        et1 = findViewById(R.id.edit_host);
        hostname = et1.getText().toString();

        et2 = findViewById(R.id.edit_port);
        port = Integer.parseInt(et2.getText().toString());

        // Start the Communicate Activity. This permits communication between
        // the server and the client.

        // Pass it the host name and the port in the intent.

        intent.putExtra(HOST_NAME, hostname);
        intent.putExtra(PORT, port);

        startActivity(intent);

    }

}
