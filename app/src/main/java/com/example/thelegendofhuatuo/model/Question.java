package com.example.thelegendofhuatuo.model;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Question {
    private int questionId;
    private int[] choicesId;
    private int anwserId;

    public Question(int questionId, int[] choicesId, int anwserId) {
        this.questionId = questionId;
        this.choicesId = choicesId;
        this.anwserId = anwserId;
    }

    public int getQuestionId() { return this.questionId; }
    public int[] getChoicesId() { return this.choicesId; }

    public boolean isAnswerTrue(int selectedIndex) {
        if (selectedIndex > choicesId.length)
            return false;

        return choicesId[selectedIndex] == anwserId;
    }

    public void randomChoice() {
        Random rand = new Random();
        int count = choicesId.length;

        for (int i = count - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = choicesId[i];;
            choicesId[i] = choicesId[j];
            choicesId[j] = temp;
        }
    }
}
