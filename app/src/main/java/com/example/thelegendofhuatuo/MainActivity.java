package com.example.thelegendofhuatuo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.example.thelegendofhuatuo.model.PlayLog;

public class MainActivity extends AppCompatActivity {

    private ImageButton startButton;
    private ImageButton rulesButton;
    private ImageButton exitButton;
    private ImageButton logButton;

    private final float buttonScaleOnClick = 0.85f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.btn_start);
        rulesButton = findViewById(R.id.btn_rules);
        exitButton = findViewById(R.id.btn_exit);
        logButton = findViewById(R.id.btn_log);

        setOnTouchListener();
        PlayLog.openDatabase(this);
    }

    private void setOnTouchListener() {
        startButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    startButton.setImageResource(R.drawable.start_button_click);
                    startButton.setScaleX(buttonScaleOnClick);
                    startButton.setScaleY(buttonScaleOnClick);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    startButton.setImageResource(R.drawable.start_button);
                    startButton.setScaleX(1f);
                    startButton.setScaleY(1f);
                    startGame();
                    return true;
                }
                return false;
            }
        });

        rulesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    rulesButton.setImageResource(R.drawable.rules_click);
                    rulesButton.setScaleX(buttonScaleOnClick);
                    rulesButton.setScaleY(buttonScaleOnClick);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    rulesButton.setImageResource(R.drawable.rules);
                    rulesButton.setScaleX(1f);
                    rulesButton.setScaleY(1f);
                    showRules();
                    return true;
                }
                return false;
            }
        });

        logButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    logButton.setImageResource(R.drawable.log_button_click);
                    logButton.setScaleX(buttonScaleOnClick);
                    logButton.setScaleY(buttonScaleOnClick);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    logButton.setImageResource(R.drawable.log_button);
                    logButton.setScaleX(1f);
                    logButton.setScaleY(1f);
                    showLog();
                    return true;
                }
                return false;
            }
        });

        exitButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    exitButton.setImageResource(R.drawable.exit_button_click);
                    exitButton.setScaleX(buttonScaleOnClick);
                    exitButton.setScaleY(buttonScaleOnClick);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    exitButton.setImageResource(R.drawable.exit_button);
                    exitButton.setScaleX(1f);
                    exitButton.setScaleY(1f);
                    finish();
                    System.exit(0);
                    return true;
                }
                return false;
            }
        });
    }

    private void startGame() {
        Intent intent = new Intent(MainActivity.this, Play.class);
        startActivity(intent);
    }

    private void showRules() {
        Intent intent = new Intent(MainActivity.this, Rules.class);
        startActivity(intent);
    }

    private void showLog() {
        Intent intent = new Intent(MainActivity.this, GameLog.class);
        startActivity(intent);
    }
}