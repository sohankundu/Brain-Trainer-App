package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    ConstraintLayout gameLayout;
    Button goButton, button0, button1, button2, button3, buttonPlayAgain;
    TextView resultTextView, scoreTextView, sumTextView, timerTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    public void playAgain(View view){
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        score = 0;
        numberOfQuestions = 0;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        timerTextView.setText("30s");
        buttonPlayAgain.setVisibility(view.INVISIBLE);
        resultTextView.setText("");
        newQuestion();

        new CountDownTimer(20200, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(Long.toString(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {

                timerTextView.setText("0s");
                resultTextView.setText("Done!");
                buttonPlayAgain.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();

    }

    public void chooseAnswer(View view){

        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
        }
        else{
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();

    }

    public void start(View view){

        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
        gameLayout.setVisibility(View.VISIBLE);

    }

    public void newQuestion(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        for(int i = 0; i < 4; i++){

            if(i == locationOfCorrectAnswer){
                answers.add(a + b);
            }
            else{
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == (a + b)){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        goButton = (Button) findViewById(R.id.goButton);
        buttonPlayAgain = (Button) findViewById(R.id.playAgainButton);

        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);

        gameLayout.setVisibility(View.INVISIBLE);


    }
}