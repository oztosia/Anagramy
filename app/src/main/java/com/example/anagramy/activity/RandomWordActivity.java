package com.example.anagramy.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import com.example.anagramy.R;
import com.example.anagramy.dictionary.RandomWordsCreator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import android.widget.Toast;


public class RandomWordActivity extends AppCompatActivity {

    TextView textView;
    static String random;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomword);
        createRandomWord();
        button = findViewById(R.id.buttonRandomAgain);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRandomWord();
            }
        });

    }


    public void createRandomWord(){

            Context context = getApplicationContext();

            AssetManager assetManager = context.getAssets();
            try {
                InputStream inputStream = assetManager.open("anagramy.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                random = RandomWordsCreator.randomWord(reader);
                textView = (TextView) findViewById(R.id.textView);
                textView.setText("Wylosowane słowo: " + random);
            } catch (IOException e) {
                Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(RandomWordActivity.this, GameActivity.class);
        intent.putExtra("FROM_ACTIVITY", "RANDOM");
        startActivity(intent);
    }

}
