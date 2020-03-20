package com.example.anagramy.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.example.anagramy.R;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;


public class TypeWordActivity extends AppCompatActivity {


    static EditText editTextView;
    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_type);

        editTextView = findViewById(R.id.editTextView);
        play = findViewById(R.id.buttonTypePlay);

        play.setEnabled(false);

        editTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    play.setEnabled(false);
                } else {
                    play.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
    }

    public void sendMessageFromTypeToGame(View view)
    {
        Intent intent = new Intent(TypeWordActivity.this, GameActivity.class);
        intent.putExtra("FROM_ACTIVITY", "TYPED");
        startActivity(intent);
    }
}
