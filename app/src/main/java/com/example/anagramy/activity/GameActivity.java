package com.example.anagramy.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.anagramy.R;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    Button showList;
    EditText editText;
    TextView textView;
    TextView anagramsListTextView;
    TextView typedWordsList;
    TextView counterView;
    String random = RandomWordActivity.random;
    public static ArrayList<String> listOfAnagrams = new ArrayList<String>();
    public static ArrayList<String> listOfGuessedWords = new ArrayList<String>();
    public static final int PLEASE_WAIT_DIALOG = 1;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        new DictionaryCalculation(this).execute();
        setWord();
        editText= findViewById(R.id.editTextView);
        editText.setText(" ");

     showList= findViewById(R.id.buttonShow);
        showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              anagramsListTextView = findViewById(R.id.anagramsListTextView);
              anagramsListTextView.setText(listOfAnagrams.toString()
                      .replace("[", "")
                      .replace(", ", "\n")
                      .replace("]", "")
              );
                showList.setEnabled(false);
                editText.setEnabled(false);

            }
        });


        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String anagram = editText.getText().toString().replaceAll("\\s","").toLowerCase();

                    if (anagram.length() == 0){
                        return;
                    }
                    listOfGuessedWords.add(anagram);
                    editText.setText("");

                counter = listOfAnagrams.size();
                counterView = findViewById(R.id.counterView);
                counterView.setText(String.format("Do odgadnięcia: " + counter));

                for (String x: listOfGuessedWords){
                    if (listOfAnagrams.contains(x)){
                        counter --;
                        counterView.setText(String.format("Do odgadnięcia: " + counter));
                    }
                    if (counter == 0){
                        Toast toast = Toast.makeText(GameActivity.this, "GRATULACJE!!! WSZYSTKIE ANAGRAMY ZOSTAŁY ODGADNIĘTE!!!", Toast.LENGTH_LONG);
                        toast.show();
                        editText.setEnabled(false);
                    }

                }

                typedWordsList = findViewById(R.id.typedWordsTextView);
                String wrongWord = "<font color=#800000>";
                String correctWord = "<font color=#008000>";
                String colorEnd = "</font><BR>";
                    if (listOfAnagrams.contains(anagram)) {
                        typedWordsList.append(Html.fromHtml(correctWord + anagram + "  ✓" + colorEnd));
                    }
                    else
                        typedWordsList.append(Html.fromHtml(wrongWord + anagram + "  ✕" + colorEnd));
            }
            });

    }

    public void setWord (){
        textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        String previousActivity= intent.getStringExtra("FROM_ACTIVITY");
        if (previousActivity.equals("TYPED")) {
            String typed = TypeWordActivity.editTextView.getText().toString().replaceAll("\\s","").toLowerCase();
            textView.setText("Znajdź jak najwięcej anagramów dla słowa: " + '\n' + typed);
        }
        else if (previousActivity.equals("RANDOM")) {
            textView.setText("Znajdź jak najwięcej anagramów dla słowa: " + '\n' + random);
        }
    }

    @Override
    public void onBackPressed() {
       Intent intent = new Intent(this, MainActivity.class);
        listOfAnagrams.clear();
        listOfGuessedWords.clear();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public Dialog onCreateDialog(int dialogId) {

        switch (dialogId) {
            case PLEASE_WAIT_DIALOG:
                ProgressDialog dialog = new ProgressDialog(this);
                dialog.setTitle("Wyszukiwanie anagramów");
                dialog.setMessage("Proszę czekać....");
                dialog.setCancelable(false);
                return dialog;
            default:
                break;
        }
        return null;
    }

}
