package com.example.thelegendofhuatuo;

import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.thelegendofhuatuo.model.LogData;
import com.example.thelegendofhuatuo.model.PlayLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameLog extends AppCompatActivity {

    private ListView logList;
    private ImageView returnButton;
    private ImageView clearButton;
    private TextView noRecordText;

    private final float buttonScaleOnClick = 0.85f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        logList = findViewById(R.id.list_log);
        returnButton = findViewById(R.id.btn_log_return);
        clearButton = findViewById(R.id.btn_clear);
        noRecordText = findViewById(R.id.txt_norecord);

        setOnTouchListener();
        showPlayLogData();
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

        clearButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    clearButton.setImageResource(R.drawable.trash_button_click);
                    clearButton.setScaleX(buttonScaleOnClick);
                    clearButton.setScaleY(buttonScaleOnClick);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    clearButton.setImageResource(R.drawable.trash_button);
                    clearButton.setScaleX(1f);
                    clearButton.setScaleY(1f);
                    clearAllRecord();
                    return true;
                }
                return false;
            }
        });
    }

    private void showPlayLogData() {
        LogData logData = PlayLog.getAllLog();
        if (logData == null) {
            logList.setVisibility(View.INVISIBLE);
            noRecordText.setVisibility(View.VISIBLE);
            return;
        }


        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();

        for (int i = logData.length - 1; i >= 0; i--) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("id", logData.id[i]);
            showitem.put("date", logData.dateTime[i]);
            showitem.put("money", "$" + logData.money[i]);
            listitem.add(showitem);
        }

        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), listitem, R.layout.log_item,
                new String[]{"id", "date", "money"},
                new int[]{R.id.txt_id, R.id.txt_date, R.id.txt_got_money});

        logList.setAdapter(adapter);
    }

    private void clearAllRecord() {
        PlayLog.clearAllLog();
        logList.setVisibility(View.INVISIBLE);
        noRecordText.setVisibility(View.VISIBLE);
    }
}
