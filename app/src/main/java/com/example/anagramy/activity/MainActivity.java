package com.example.anagramy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.anagramy.R;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendMessagetoRandomActivity(View view)
    {
        Intent intent = new Intent(MainActivity.this, RandomWordActivity.class);
        startActivity(intent);
    }

    public void sendMessageToTypeActivity(View view)
    {
        Intent typeWordIntent = new Intent(MainActivity.this, TypeWordActivity.class);
        startActivity(typeWordIntent);
    }
}
