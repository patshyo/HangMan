package com.example.myapplication;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String word;
    private String correctLetters = "";
    private String wrongLetters = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView text = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
         Button check = findViewById(R.id.check);
        EditText editText = findViewById(R.id.editTest);
        Button restartButton = findViewById(R.id.restartButton);

        check.setVisibility(View.GONE);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 setWord(String.valueOf(editText.getText()));
                button.setVisibility(View.GONE);
                check.setVisibility(View.VISIBLE);
                editText.setText("");
            }

        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = String.valueOf(editText.getText());
                text.setText(getPlace(input));
                editText.setText("");

            }

        });
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word = "";
                correctLetters = "";
                wrongLetters = "";
                text.setText("Hello World!");
                button.setVisibility(View.VISIBLE);
                check.setVisibility(View.GONE);
                editText.setText("");
            }
        });






    }
    private void setWord(String word){
        this.word = word;
    }
    private String getPlace(String guess){
        if(this.word.contains(guess)){
            this.correctLetters += guess;
        }
        else{
            this.wrongLetters += guess + " ";
        }
        String place = "";
        for(int i = 0; i < this.word.length(); i++){
            if (this.word.charAt(i) == ' '){
                place += " |  ";
            }
            else if (this.correctLetters.contains(String.valueOf(this.word.charAt(i)))){
                place += this.word.charAt(i);
            }
            else{
                place += "_ ";
            }
        }
        return (String.format("%s%nWrong Guesses:%s", place, this.wrongLetters));

    }




}