package com.example.sequentialclient;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CommunicateActivity extends AppCompatActivity {

    private Socket socket = null;
    private PrintWriter out = null;
    private Scanner in = null;
    private TextView tv;

    // Connect to the server. Waits for user input.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);
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

        int port;
        String hostname;

        // Get the host name from the intent.

        Intent intent = getIntent();
        hostname = intent.getStringExtra(MainActivity.HOST_NAME);

        // Get the port number from the intent.

        port = intent.getIntExtra(MainActivity.PORT, 4000);

        // Get a handle on the Text View for displaying the answers.

        tv = findViewById(R.id.text_answer);

        // Try to open the connection to the server.

        try
        {

            socket = new Socket(hostname, port);

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(new InputStreamReader(socket.getInputStream()));

            tv.setText("Connected to server.");

        }

        catch(IOException e) // Catch socket problems.
        {

            tv.setText("Problem: " + e.toString());
            socket = null;

        }

    }

    // Method to send user-inputted information to the server. Server will
    // read the information and return the result after processing.

    public void sendInfo(View view)
    {

        EditText et1;
        EditText et2;
        EditText et3;
        String operation;
        String genome;
        String sequence;
        String occur_answer;
        String score_answer;
        String align_answer;

        et1 = findViewById(R.id.edit_operation);
        operation = et1.getText().toString();

        et2 = findViewById(R.id.edit_genome);
        genome = et2.getText().toString();

        et3 = findViewById(R.id.edit_sequence);
        sequence = et3.getText().toString();

        // Are we connected?

        if (socket == null)
        {

            tv.setText("Not connected.");

        }

        else
        {

            // If user enters "occur" as the operation, send the inputted
            // operation, genome, and sequence to the server to be processed.
            // Returned answer will be displayed in the text view.

            // Occur operation displays the number of times a sequence appears
            // in the genome.

            if (operation.equals("occur"))
            {

                out.println(operation);
                out.println(genome);
                out.println(sequence);

                occur_answer = in.nextLine();

                tv.setText("Occurrences: " + occur_answer);

            }

            // If user enters "score" as the operation, send the inputted
            // operation, genome, and sequence to the server to be processed.
            // Returned answer will be displayed in the text view.

            // Score operation determines the best similarity score for a
            // genome and sequence.

            else if (operation.equals("score"))
            {

                out.println(operation);
                out.println(genome);
                out.println(sequence);

                score_answer = in.nextLine();

                tv.setText("Similarity score: " + score_answer);

            }

            // If user enters "align" as the operation, send the inputted
            // operation, genome, and sequence to the server to be processed.
            // Returned answer will be displayed in the text view.

            // Align operation matches the first occurrence of a sequence in
            // the genome and displays it as a new string. However, the rest
            // of the string is filled with dashes, or gaps.

            else if (operation.equals("align"))
            {

                out.println(operation);
                out.println(genome);
                out.println(sequence);

                align_answer = in.nextLine();

                tv.setText("Alignment: " + align_answer);

            }

            // If user enters an operation that does not match any of the above
            // ones, display error message. Do not send any of the inputted
            // information to the server.

            else
            {

                tv.setText("Not a valid operation.");

            }

        }

    }

}
