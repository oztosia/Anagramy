package com.example.anagramy.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.anagramy.dictionary.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.anagramy.activity.RandomWordActivity.random;

public class DictionaryCalculation extends AsyncTask<Void, Void, Void> {


    private Activity dictionaryCalculationActivity;
    String word = new String();
    public static BufferedReader reader;

    public DictionaryCalculation(Activity wywolujaceActivity) {
        this.dictionaryCalculationActivity = wywolujaceActivity;
    }


    @Override
    protected void onPreExecute() {
        dictionaryCalculationActivity.showDialog(GameActivity.PLEASE_WAIT_DIALOG);
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        AssetManager assetManager = dictionaryCalculationActivity.getAssets();
        try {
            InputStream inputStream = assetManager.open("anagramy.txt");
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            Dictionary.isAnagram(setWord(), reader);
        } catch (IOException e) {
            Toast toast = Toast.makeText(dictionaryCalculationActivity, "Nie można wczytać słownika", Toast.LENGTH_LONG);
            toast.show();
        }
        return null;

    }

    @Override
    protected void onPostExecute(Void result) {
        dictionaryCalculationActivity.removeDialog(GameActivity.PLEASE_WAIT_DIALOG);
        Toast.makeText(dictionaryCalculationActivity, "Wyszukano!", Toast.LENGTH_SHORT).show();
    }

    public String setWord (){
        Intent intent = dictionaryCalculationActivity.getIntent();
        String previousActivity= intent.getStringExtra("FROM_ACTIVITY");
        if (previousActivity.equals("TYPED")) {
            String typed = TypeWordActivity.editTextView.getText().toString().replaceAll("\\s","").toLowerCase();
            word = typed;
        }
        else if (previousActivity.equals("RANDOM")) {
            word = random;
        }
        return word;
    }

}