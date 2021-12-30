package com.example.thelegendofhuatuo;

import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AnswerElement {

    public class ButtonAttribute {
        public ImageButton buttonInstance;
        public boolean isClicked;
        public boolean isTriggeredTip;
    }


    public ButtonAttribute[] ansBtns;
    public TextView[] ansTexts;

    public AnswerElement() {
        ansBtns = new ButtonAttribute[4];
        for (int i = 0; i < 4; i++) {
            ansBtns[i] = new ButtonAttribute();
        }

        ansTexts = new TextView[4];
    }

    public ButtonAttribute getButton(int index) {
        if (index >= 4)
            return null;

        return ansBtns[index];
    }

    public int getButtonIndex(ButtonAttribute button) {
        for (int i = 0; i < ansBtns.length; i++) {
            if (ansBtns[i] == button)
                return i;
        }

        return -1;
    }

    public TextView getTextView(int index) {
        if (index >= 4)
            return null;

        return ansTexts[index];
    }

    public boolean isButtonClicked(int index) {
        if (index >= 4)
            return false;

        return ansBtns[index].isClicked;
    }

    public void setButtonClicked(int index, boolean isClicked) {
        if (index >= 4)
            return;

        ansBtns[index].isClicked = isClicked;
    }

    public CharSequence getAnswerText(int index) {
        if (index >= 4)
            return null;

        return ansTexts[index].getText();
    }

    public void setAnswerText(int index, int resId) {
        if (index >= 4)
            return;

        ansTexts[index].setText(resId);
    }
}
