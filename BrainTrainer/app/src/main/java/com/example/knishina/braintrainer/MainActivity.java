package com.example.knishina.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    ArrayList<Integer> answers = new ArrayList<>();
    List<String> operators = Arrays.asList("+", "-", "*", "/");
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView sumTextView;
    TextView timerTextView;
    RelativeLayout relativeLayout;
    RelativeLayout gameRelativeLayout;

    public void playAgain(View view) {
        generateQuestion();

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");

        playAgainButton.setVisibility(View.INVISIBLE);

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        new CountDownTimer(10100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your score is " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

                playAgainButton.setVisibility(View.VISIBLE);

                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();

    }

    public void generateQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21) + 1;


        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer = 0;

        String operator = operators.get(rand.nextInt(3)); // 整数のため、割り算は省く。
        int result = 0;

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                switch (operator) {
                    case "+":
                        result = a + b;
                        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
                        break;
                    case "-":
                        result = a - b;
                        sumTextView.setText(Integer.toString(a) + " - " + Integer.toString(b));
                        break;
                    case "*":
                        result = a * b;
                        sumTextView.setText(Integer.toString(a) + " * " + Integer.toString(b));
                        break;
                    case "/":
                        result = a / b;
                        sumTextView.setText(Integer.toString(a) + " / " + Integer.toString(b));
                }
                answers.add(result);
            } else {
                switch (operator) {
                    case "+":
                        incorrectAnswer = a + b + rand.nextInt(10) - 5;
                        while(incorrectAnswer == a + b) {
                            incorrectAnswer = a + b + rand.nextInt(10) - 5;
                        }
                        break;
                    case "-":
                        incorrectAnswer = a - b + rand.nextInt(10) - 5;
                        while(incorrectAnswer == a - b) {
                            incorrectAnswer = a - b + rand.nextInt(10) - 5;
                        }
                        break;
                    case "*":
                        incorrectAnswer = a * b + rand.nextInt(10) - 5;
                        while(incorrectAnswer == a * b) {
                            incorrectAnswer = a * b + rand.nextInt(10) - 5;
                        }
                        break;
                    case "/":
                        incorrectAnswer = a / b + rand.nextInt(10) - 5;
                        while(incorrectAnswer == a / b) {
                            incorrectAnswer = a / b + rand.nextInt(10) - 5;
                        }
                }


                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Correct");
        } else {
            resultTextView.setText("Wrong");
        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        resultTextView = findViewById(R.id.resultTextView);
        pointsTextView = findViewById(R.id.pointTextView);
        timerTextView = findViewById(R.id.timerTextView);
        gameRelativeLayout = findViewById(R.id.relativeLayout);
        gameRelativeLayout = findViewById(R.id.gameRelativeLayout);

        generateQuestion();

        new CountDownTimer(10100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your score is " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

                playAgainButton.setVisibility(View.VISIBLE);

                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();
    }
}
