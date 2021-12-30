package com.example.thelegendofhuatuo;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Rules extends AppCompatActivity {

    private ImageButton returnButton;

    private final float buttonScaleOnClick = 0.85f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        returnButton = findViewById(R.id.btn_rules_return);
        setOnTouchListener();
    }

    private void setOnTouchListener() {
        returnButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    returnButton.setImageResource(R.drawable.return_button_click);
                    returnButton.setScaleX(buttonScaleOnClick);
                    returnButton.setScaleY(buttonScaleOnClick);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    returnButton.setImageResource(R.drawable.return_button);
                    returnButton.setScaleX(1f);
                    returnButton.setScaleY(1f);
                    finish();
                    return true;
                }
                return false;
            }
        });
    }
}
