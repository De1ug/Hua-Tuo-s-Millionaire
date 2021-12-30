package com.example.thelegendofhuatuo;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thelegendofhuatuo.model.PlayLog;
import com.example.thelegendofhuatuo.model.Question;
import com.example.thelegendofhuatuo.model.QuestionManager;
import com.example.thelegendofhuatuo.model.Stage;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import androidx.appcompat.app.AppCompatActivity;

public class Play extends AppCompatActivity {

    private int questionCount = 15;
    private int currentQuestionIndex = -1;
    private int selectedIndex = -1;
    private int currentStage = -1;
    private int currentStageTime = 0;
    private int maxTipCount = 5;
    private int currentTipCount = 0;
    private boolean isTriggeredTip = false;
    private TextView questionText;
    private AnswerElement answerEle;
    private TextView moneyText;
    private TextView timeText;
    private ImageButton submitButton;
    private ImageButton returnButton;
    private ImageButton tipButton;
    private QuestionManager questionManager;
    private Handler timerHandler;
    private Runnable countTime;
    private int waitingTime = 3000;
    private CountDownTimer waitingTimer = null;
    private ImageView endingBackground;
    private ImageView endingResult;
    private TextView endingMoney;
    private ImageButton endingReturn;
    private TextView tipRemainText;
    private boolean isGameEnded = false;

    private final float ansButtonScaleOnClick = 0.85f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        answerEle = new AnswerElement();
        questionText = (TextView) findViewById(R.id.txt_question);
        answerEle.ansBtns[0].buttonInstance = (ImageButton) findViewById(R.id.btn_ans1);
        answerEle.ansBtns[1].buttonInstance = (ImageButton) findViewById(R.id.btn_ans2);
        answerEle.ansBtns[2].buttonInstance = (ImageButton) findViewById(R.id.btn_ans3);
        answerEle.ansBtns[3].buttonInstance = (ImageButton) findViewById(R.id.btn_ans4);
        answerEle.ansTexts[0] = (TextView) findViewById(R.id.txt_ans1);
        answerEle.ansTexts[1] = (TextView) findViewById(R.id.txt_ans2);
        answerEle.ansTexts[2] = (TextView) findViewById(R.id.txt_ans3);
        answerEle.ansTexts[3] = (TextView) findViewById(R.id.txt_ans4);

        moneyText = findViewById(R.id.txt_money);
        submitButton = findViewById(R.id.btn_submit);
        returnButton = findViewById(R.id.btn_return);
        tipButton = findViewById(R.id.btn_tip);
        timeText = findViewById(R.id.txt_time);
        endingBackground = findViewById(R.id.img_end);
        endingResult = findViewById(R.id.img_end_res);
        endingMoney = findViewById(R.id.txt_gotmoney);
        endingReturn = findViewById(R.id.btn_res_return);
        tipRemainText = findViewById(R.id.txt_tip_remain);

        questionManager = new QuestionManager(questionCount);

        timerHandler = new Handler();
        countTime = new Runnable() {
            @Override
            public void run() {
                currentStageTime -= 1000;
                updateTime();
                timerHandler.postDelayed(this, 1000);
            }
        };

        currentTipCount = maxTipCount;
        tipRemainText.setText(String.valueOf(currentTipCount));

        setOnClickListener();
        setOnTouchListener();

        updateStage();
    }

    private void reset() {
        questionCount = 1;
        currentQuestionIndex = -1;
        selectedIndex = -1;
        currentStage = -1;
    }

    private void updateStage() {
        if (currentStage + 1 >= questionManager.getQuestionCount()) {
            popupResult(false);
            return;
        }

        currentStage++;
        waitingTimer = null;
        isTriggeredTip = false;
        moneyText.setText("$" + String.valueOf(Stage.getCurrentStatgeMoney(currentStage)));
        updateQuestion();
        updateStageTime();
        updateButton();
        updateEnding();
    }

    private void updateStageTime() {
        currentStageTime = Stage.getTime();
        updateTime();
        timerHandler.postDelayed(countTime, 1000);
    }

    private void updateButton() {
        for (int i = 0; i < 4; i++) {
            AnswerElement.ButtonAttribute btn = answerEle.getButton(i);
            TextView text = answerEle.getTextView(i);
            if (btn == null || text == null)
                continue;

            btn.isTriggeredTip = false;
            btn.isClicked = false;
            btn.buttonInstance.setImageResource(R.drawable.ans_button);
            btn.buttonInstance.setEnabled(true);
            btn.buttonInstance.setClickable(true);
            updateButtonStyle(btn.buttonInstance, text, false, btn.isTriggeredTip);
        }

        submitButton.setEnabled(true);
        submitButton.setClickable(true);
    }

    private void updateQuestion() {
        currentQuestionIndex++;
        Question question = questionManager.getQuestion(currentQuestionIndex);
        if (question == null)
            return;

        questionText.setText(question.getQuestionId());
        int[] choicesId = question.getChoicesId();
        for (int i = 0; i < choicesId.length; i++) {
            answerEle.setAnswerText(i, choicesId[i]);
        }
    }

    private void updateEnding() {
        endingBackground.setVisibility(View.INVISIBLE);
        endingResult.setVisibility(View.INVISIBLE);
        endingMoney.setVisibility(View.INVISIBLE);
        endingReturn.setVisibility(View.INVISIBLE);
    }

    private void updateTime() {
        if (currentStageTime <= 0) {
            stopTimer();
            HandleGameResult(false, true);
            return;
        }

        timeText.setText(String.valueOf(currentStageTime / 1000));
    }

    private void HandleClickedAnswer(int index) {
        if (index >= 4)
            return;

        AnswerElement.ButtonAttribute btn = answerEle.getButton(index);
        if (btn == null)
            return;

        selectedIndex = index;
        boolean isSelected = false;
        boolean isClicked = !btn.isClicked;
        for (int i = 0; i < answerEle.ansBtns.length; i++) {
            answerEle.getButton(i).isClicked = i == index ? isClicked : false;
        }

        for (int i = 0; i < answerEle.ansBtns.length; i++) {
            AnswerElement.ButtonAttribute button = answerEle.getButton(i);
            TextView text = answerEle.getTextView(i);
            updateButtonStyle(button.buttonInstance, text, button.isClicked, button.isTriggeredTip);

            if (button.isClicked)
                isSelected = true;
        }

        if (!isSelected)
            selectedIndex = -1;
    }

    private void setOnClickListener() {

        for (int i = 0; i < 4; i++) {
            AnswerElement.ButtonAttribute btn = answerEle.getButton(i);
            TextView text = answerEle.getTextView(i);
            if (btn == null || text == null)
                continue;

            btn.buttonInstance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HandleClickedAnswer(answerEle.getButtonIndex(btn));
                }
            });
        }
    }

    private void setOnTouchListener() {
        returnButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    updateButtonStyle(returnButton, R.drawable.return_button_click, ansButtonScaleOnClick);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    updateButtonStyle(returnButton, R.drawable.return_button, 1f);
                    finish();
                    return true;
                }
                return false;
            }
        });

        tipButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    updateButtonStyle(tipButton, R.drawable.tip_button_click, ansButtonScaleOnClick);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    updateButtonStyle(tipButton, R.drawable.tip_button, 1f);
                    triggerTip();
                    return true;
                }
                return false;
            }
        });

        submitButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    updateButtonStyle(submitButton, R.drawable.submit_click, ansButtonScaleOnClick);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    updateButtonStyle(submitButton, R.drawable.submit, 1f);
                    submitAnswer();
                    return true;
                }
                return false;
            }
        });

        endingReturn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    updateButtonStyle(endingReturn, R.drawable.return_button_click, ansButtonScaleOnClick);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    updateButtonStyle(endingReturn, R.drawable.return_button, 1f);
                    finish();
                    return true;
                }
                return false;
            }
        });
    }

    private void updateButtonStyle(ImageButton button, TextView text, boolean isClicked, boolean isTriggeredTip) {
        if (button == null || text == null)
            return;

        Resources recources = getResources();
        int textColor = 0;
        int image = 0;
        float scale = 0f;

        if (isClicked && !isTriggeredTip) {
            image = R.drawable.ans_button_click;
            textColor = recources.getColor(R.color.text_onclick);
            scale = ansButtonScaleOnClick;
        } else if (!isClicked && isTriggeredTip) {
            image = R.drawable.ans_button;
            textColor = recources.getColor(R.color.text_onclick);
            scale = 1f;
        } else {
            image = R.drawable.ans_button;
            textColor = recources.getColor(R.color.white);
            scale = 1f;
        }

        button.setImageResource(image);
        button.setScaleX(scale);
        button.setScaleY(scale);
        text.setTextColor(textColor);

    }

    private void updateButtonStyle(ImageButton button, int resId, float scale) {
        if (button == null || getResources().getDrawable(resId) == null)
            return;

        button.setImageResource(resId);
        button.setScaleX(scale);
        button.setScaleY(scale);
    }

    private void submitAnswer() {
        if (selectedIndex < 0) {
            Toast.makeText(this, R.string.chose_ans, Toast.LENGTH_SHORT).show();
            return;
        }

        Question question = questionManager.getQuestion(currentQuestionIndex);
        if (question == null)
            return;

        stopTimer();
        boolean isCorrect = question.isAnswerTrue(selectedIndex);

        for (int i = 0; i < 4; i++) {
            AnswerElement.ButtonAttribute btn = answerEle.getButton(i);
            TextView text = answerEle.getTextView(i);
            if (btn == null)
                continue;

            btn.buttonInstance.setEnabled(false);
            btn.buttonInstance.setClickable(false);
            btn.buttonInstance.setScaleX(1f);
            btn.buttonInstance.setScaleY(1f);
            text.setTextColor(getResources().getColor(R.color.white));

            if (selectedIndex == i && isCorrect)
                btn.buttonInstance.setImageResource(R.drawable.ans_correct);
            else if (selectedIndex == i && !isCorrect)
                btn.buttonInstance.setImageResource(R.drawable.ans_wrong);
            else if (selectedIndex != i && !isCorrect && question.isAnswerTrue(i))
                btn.buttonInstance.setImageResource(R.drawable.ans_correct);
        }

        submitButton.setEnabled(false);
        submitButton.setClickable(false);
        HandleGameResult(isCorrect, false);
    }

    private void stopTimer() {
        Message msg = new Message();
        msg.what = 1;
        timerHandler.sendMessage(msg);
        timerHandler.removeCallbacks(countTime);
    }

    private void HandleGameResult(boolean isCorrect, boolean isTimeout) {
        if (waitingTimer != null && !isTimeout && isGameEnded)
            return;

        if (isTimeout) {
            popupResult(true);
            return;
        }

        waitingTimer = new CountDownTimer(waitingTime, 100) {
            @Override
            public void onTick(long l) {
                timeText.setText(String.valueOf(l / 1000 + 1));
            }

            @Override
            public void onFinish() {
                if (isCorrect)
                    updateStage();
                else
                    popupResult(true);
            }
        }.start();
    }

    private void popupResult(boolean isFailed) {
        if (isGameEnded)
            return;

        isGameEnded = true;

        if (isFailed)
            currentStage--;

        if(waitingTimer != null)
            waitingTimer.cancel();

        PlayLog.AddLog(Stage.getStageMoney(currentStage));
        endingMoney.setText("$" + String.valueOf(Stage.getStageMoney(currentStage)));
        tipButton.setEnabled(false);
        tipButton.setClickable(false);
        returnButton.setEnabled(false);
        returnButton.setClickable(false);
        endingBackground.setVisibility(View.VISIBLE);
        endingResult.setVisibility(View.VISIBLE);
        endingMoney.setVisibility(View.VISIBLE);
        endingReturn.setVisibility(View.VISIBLE);
    }

    private void triggerTip() {
        if (isTriggeredTip || currentTipCount <= 0)
            return;

        Question question = questionManager.getQuestion(currentQuestionIndex);
        if (question == null)
            return;

        int cnt = 0;
        int[] wrongAns = new int[3];
        int wrongAnsCount = 0;

        for (int i = 0; i < 4; i++) {
            AnswerElement.ButtonAttribute btn = answerEle.getButton(i);
            TextView text = answerEle.getTextView(i);
            if (btn == null || text == null)
                continue;

            btn.isClicked = false;
            updateButtonStyle(btn.buttonInstance, text, false, btn.isTriggeredTip);
            selectedIndex = -1;

            if (!question.isAnswerTrue(i))
                wrongAns[wrongAnsCount++] = i;
        }

        if (wrongAnsCount > 0) {
            Random rand = new Random();
            int count = wrongAnsCount;

            for (int j = count - 1; j > 0; j--) {
                int l = rand.nextInt(j + 1);
                int temp = wrongAns[j];;
                wrongAns[j] = wrongAns[l];
                wrongAns[l] = temp;
            }

            for (int i = 0; i < wrongAnsCount; i++) {
                if (cnt >= 2)
                    break;

                AnswerElement.ButtonAttribute btn = answerEle.getButton(wrongAns[i]);
                TextView text = answerEle.getTextView(wrongAns[i]);
                if (btn == null || text == null)
                    continue;

                if (!question.isAnswerTrue(i)) {
                    btn.isTriggeredTip = true;
                    btn.buttonInstance.setEnabled(false);
                    btn.buttonInstance.setClickable(false);
                    text.setTextColor(getResources().getColor(R.color.text_onclick));
                    cnt++;
                }
            }
        }


        currentTipCount--;
        tipRemainText.setText(String.valueOf(currentTipCount));
        isTriggeredTip = true;
    }
}
