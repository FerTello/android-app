package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity{

    Button button_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_play = findViewById(R.id.button_login);

        button_play.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                playStarSearchActivity();
            }
        });
    }

    public void playStarSearchActivity(){
        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }

}
