package edu.gatech.seclass.words6300;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Start extends AppCompatActivity {

    TextView textView;
    Button button_start;
    private String[] args = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_window);

        textView = findViewById(R.id.textView);
        button_start = findViewById(R.id.button_start);

        button_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                start(args);
            }
        });
    }



    public void start(String[] args) {
        SimManager monitorSim = new SimManager();
        int trackTurnsCompleted = 0;
        Boolean showState = Boolean.FALSE;

        // check for the test scenario file name
        if (args.length < 1) {
            textView.setText("This is it so far :)");
            //System.out.println("ERROR: Test scenario file name not found.");
            return;
        }

        if (args.length >= 2 && (args[1].equals("-v") || args[1].equals("-verbose"))) { showState = Boolean.TRUE; }

        monitorSim.uploadStartingFile(args[0]);

        // run the simulation for a fixed number of steps
        for(int turns = 0; turns < monitorSim.simulationDuration(); turns++) {
            trackTurnsCompleted = turns;

            if (monitorSim.dronesAllStopped()) { break; }

            for (int k = 0; k < monitorSim.droneCount(); k++) {

                if (monitorSim.droneStopped(k)) { continue; }

                monitorSim.pollDroneForAction(k);
                monitorSim.validateDroneAction(k);
                monitorSim.displayActionAndResponses(k);

                // render the state of the space region after each command
                if (showState) { monitorSim.renderRegion(); }
            }
        }

        monitorSim.finalReport(trackTurnsCompleted);
    }

}
