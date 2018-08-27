package com.example.whichnumberisbigger;

import java.lang.Math;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {


    private Button leftButton;
    private Button rightButton;
    private TextView score;
    private ConstraintLayout bgcolor;
    private int leftNumber;
    private int rightNumber;
    private boolean isLeftBigger;
    private int scoreNumber = 0;
    private TextView failureMessage;
    public static final int MAX_NUM = 1000;
    public static final int MIN_NUM = -1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        wireWidgets();
        updateDisplay();


    }

    @SuppressLint("SetTextI18n")
    private void updateDisplay() {

        String scoreString = getResources().getString(R.string.main_score);
        score.setText(scoreString + scoreNumber);
        randomizeNumbers();



        leftButton.setText(String.valueOf(leftNumber));

        rightButton.setText(String.valueOf(rightNumber));

    }

    private void randomizeNumbers(){

        rightNumber = genNumber();
        leftNumber = genNumber();
        while(leftNumber == rightNumber){
            leftNumber = genNumber();
        }
        if(leftNumber > rightNumber){
            isLeftBigger = true;
        }
        else{
            isLeftBigger = false;
        }

    }


    private int genNumber(){

        int range = MAX_NUM - MIN_NUM + 1;
        return MIN_NUM + (int) (Math.random()*range);

    }

    private void wireWidgets() {

        rightButton = findViewById(R.id.button_main_number2);
        leftButton = findViewById(R.id.button_main_number1);
        score = findViewById(R.id.textView_main_score);
        failureMessage = findViewById(R.id.textView_main_fail);
        bgcolor = findViewById(R.id.background);

    }


    public void onRightClick(View view) {

        if(isLeftBigger == false){

            win();

        }
        else{

            lose();

        }

        updateDisplay();
    }



    private void win() {
        scoreNumber++;
        Toast toast = Toast.makeText(getApplicationContext(), "Correct!!", Toast.LENGTH_SHORT);
        toast.show();

        failureMessage.setVisibility(View.INVISIBLE);

    }
    private void lose() {
        Toast toast = Toast.makeText(getApplicationContext(), "WRONG!!", Toast.LENGTH_SHORT);
        toast.show();
        failureMessage.setVisibility(View.VISIBLE);
        bgcolor.setBackgroundColor(Color.rgb(255, 0, 0));

    }





    public void onLeftClick(View view) {

        if(isLeftBigger == true){

           win();

        }
        else{

            lose();

        }
        updateDisplay();
    }



    public void changeNumbers(){



    }
}
