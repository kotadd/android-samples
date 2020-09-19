package com.example.knishina.timerdemo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    boolean counterIsActive = false;
    Button controllerButton;
    CountDownTimer countDownTimer;
    ImageView imageView;

    public void stopTimer() {
//        timerTextView.setText("0:30");
//        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("Start");
        timerSeekBar.setEnabled(true);
        counterIsActive = false;
    }

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondsString = Integer.toString(seconds);

        if (seconds < 10) {
            secondsString = "0" + seconds;
        }
        if (seconds < 1) {
            controllerButton.setEnabled(false);
        } else {
            controllerButton.setEnabled(true);
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondsString);

    }

    public void controlTimer(View view) {

        if (counterIsActive == false) {

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controllerButton.setText("Stop");
            imageView.setImageResource(R.drawable.egg);

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    stopTimer();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.cry);
                    mediaPlayer.start();
                    imageView.setImageResource(R.drawable.crack);

                }
            }.start();
        } else {
            stopTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.timerTextView);
        controllerButton = findViewById(R.id.controllerButton);
        imageView = findViewById(R.id.imageView);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /*
        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("seconds left", String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                Log.i("Done", "CDT fin");
            }
        }.start();
        */

        /*
        final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Log.i("Runnable ran. ", "a second passed");
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(run);
        */
    }
}
