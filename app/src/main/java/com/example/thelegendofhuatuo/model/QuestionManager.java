package com.example.thelegendofhuatuo.model;

import com.example.thelegendofhuatuo.R;

import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {
    private int questionCount;
    private Question[] questions = new Question[]{
        new Question(R.string.q1, new int[]{R.string.q1_c1, R.string.q1_c2, R.string.q1_c3, R.string.q1_c4}, R.string.q1_c3),
        new Question(R.string.q2, new int[]{R.string.q2_c1, R.string.q2_c2, R.string.q2_c3, R.string.q2_c4}, R.string.q2_c3),
        new Question(R.string.q3, new int[]{R.string.q3_c1, R.string.q3_c2, R.string.q3_c3, R.string.q3_c4}, R.string.q3_c1),
        new Question(R.string.q4, new int[]{R.string.q4_c1, R.string.q4_c2, R.string.q4_c3, R.string.q4_c4}, R.string.q4_c1),
        new Question(R.string.q5, new int[]{R.string.q5_c1, R.string.q5_c2, R.string.q5_c3, R.string.q5_c4}, R.string.q5_c1),
        new Question(R.string.q6, new int[]{R.string.q6_c1, R.string.q6_c2, R.string.q6_c3, R.string.q6_c4}, R.string.q6_c2),
        new Question(R.string.q7, new int[]{R.string.q7_c1, R.string.q7_c2, R.string.q7_c3, R.string.q7_c4}, R.string.q7_c4),
        new Question(R.string.q8, new int[]{R.string.q8_c1, R.string.q8_c2, R.string.q8_c3, R.string.q8_c4}, R.string.q8_c4),
        new Question(R.string.q9, new int[]{R.string.q9_c1, R.string.q9_c2, R.string.q9_c3, R.string.q9_c4}, R.string.q9_c4),
        new Question(R.string.q10, new int[]{R.string.q10_c1, R.string.q10_c2, R.string.q10_c3, R.string.q10_c4}, R.string.q10_c4),
        new Question(R.string.q11, new int[]{R.string.q11_c1, R.string.q11_c2, R.string.q11_c3, R.string.q11_c4}, R.string.q11_c4),
        new Question(R.string.q12, new int[]{R.string.q12_c1, R.string.q12_c2, R.string.q12_c3, R.string.q12_c4}, R.string.q12_c4),
        new Question(R.string.q13, new int[]{R.string.q13_c1, R.string.q13_c2, R.string.q13_c3, R.string.q13_c4}, R.string.q13_c4),
        new Question(R.string.q15, new int[]{R.string.q15_c1, R.string.q15_c2, R.string.q15_c3, R.string.q15_c4}, R.string.q15_c4),
        new Question(R.string.q16, new int[]{R.string.q16_c1, R.string.q16_c2, R.string.q16_c3, R.string.q16_c4}, R.string.q16_c4),
        new Question(R.string.q17, new int[]{R.string.q17_c1, R.string.q17_c2, R.string.q17_c3, R.string.q17_c4}, R.string.q17_c4),
        new Question(R.string.q18, new int[]{R.string.q18_c1, R.string.q18_c2, R.string.q18_c3, R.string.q18_c4}, R.string.q18_c4),
        new Question(R.string.q19, new int[]{R.string.q19_c1, R.string.q19_c2, R.string.q19_c3, R.string.q19_c4}, R.string.q19_c4),
        new Question(R.string.q20, new int[]{R.string.q20_c1, R.string.q20_c2, R.string.q20_c3, R.string.q20_c4}, R.string.q20_c4),
        new Question(R.string.q21, new int[]{R.string.q21_c1, R.string.q21_c2, R.string.q21_c3, R.string.q21_c4}, R.string.q21_c4),
        new Question(R.string.q22, new int[]{R.string.q22_c1, R.string.q22_c2, R.string.q22_c3, R.string.q22_c4}, R.string.q22_c4),
        new Question(R.string.q23, new int[]{R.string.q23_c1, R.string.q23_c2, R.string.q23_c3, R.string.q23_c4}, R.string.q23_c4),
        new Question(R.string.q25, new int[]{R.string.q25_c1, R.string.q25_c2, R.string.q25_c3, R.string.q25_c4}, R.string.q25_c4),
        new Question(R.string.q26, new int[]{R.string.q26_c1, R.string.q26_c2, R.string.q26_c3, R.string.q26_c4}, R.string.q26_c4),
        new Question(R.string.q27, new int[]{R.string.q27_c1, R.string.q27_c2, R.string.q27_c3, R.string.q27_c4}, R.string.q27_c4),
        new Question(R.string.q28, new int[]{R.string.q28_c1, R.string.q28_c2, R.string.q28_c3, R.string.q28_c4}, R.string.q28_c4),
        new Question(R.string.q30, new int[]{R.string.q30_c1, R.string.q30_c2, R.string.q30_c3, R.string.q30_c4}, R.string.q30_c4),
        new Question(R.string.q31, new int[]{R.string.q31_c1, R.string.q31_c2, R.string.q31_c3, R.string.q31_c4}, R.string.q31_c4),
        new Question(R.string.q32, new int[]{R.string.q32_c1, R.string.q32_c2, R.string.q32_c3, R.string.q32_c4}, R.string.q32_c4),
        new Question(R.string.q33, new int[]{R.string.q33_c1, R.string.q33_c2, R.string.q33_c3, R.string.q33_c4}, R.string.q33_c4),
        new Question(R.string.q34, new int[]{R.string.q34_c1, R.string.q34_c2, R.string.q34_c3, R.string.q34_c4}, R.string.q34_c4),
        new Question(R.string.q35, new int[]{R.string.q35_c1, R.string.q35_c2, R.string.q35_c3, R.string.q35_c4}, R.string.q35_c4),
        new Question(R.string.q36, new int[]{R.string.q36_c1, R.string.q36_c2, R.string.q36_c3, R.string.q36_c4}, R.string.q36_c4),
        new Question(R.string.q37, new int[]{R.string.q37_c1, R.string.q37_c2, R.string.q37_c3, R.string.q37_c4}, R.string.q37_c4),
        new Question(R.string.q38, new int[]{R.string.q38_c1, R.string.q38_c2, R.string.q38_c3, R.string.q38_c4}, R.string.q38_c4),
        new Question(R.string.q39, new int[]{R.string.q39_c1, R.string.q39_c2, R.string.q39_c3, R.string.q39_c4}, R.string.q39_c4),
        new Question(R.string.q40, new int[]{R.string.q40_c1, R.string.q40_c2, R.string.q40_c3, R.string.q40_c4}, R.string.q40_c4),
        new Question(R.string.q41, new int[]{R.string.q41_c1, R.string.q41_c2, R.string.q41_c3, R.string.q41_c4}, R.string.q41_c4),
        new Question(R.string.q42, new int[]{R.string.q42_c1, R.string.q42_c2, R.string.q42_c3, R.string.q42_c4}, R.string.q42_c4),
        new Question(R.string.q43, new int[]{R.string.q43_c1, R.string.q43_c2, R.string.q43_c3, R.string.q43_c4}, R.string.q43_c4),
        new Question(R.string.q44, new int[]{R.string.q44_c1, R.string.q44_c2, R.string.q44_c3, R.string.q44_c4}, R.string.q44_c4),
    };
    private ArrayList<Question> generatedQuestions = new ArrayList<Question>();

    public QuestionManager() {

    }

    public QuestionManager(int count) {
        generateQuestion(count);
    }

    public void generateQuestion(int count) {
        randomQuestion();
        int cnt = questions.length < count ? questions.length : count;
        for (int i = 0; i < cnt; i++) {
            questions[i].randomChoice();
            generatedQuestions.add(questions[i]);
        }
    }

    public Question getQuestion(int index) {
        if (index >= generatedQuestions.size()) {
            return null;
        }

        return generatedQuestions.get(index);
    }

    public int getQuestionCount() {return generatedQuestions.size();}

    private void randomQuestion() {
        Random rand = new Random();
        int count = questions.length;

        for (int i = count - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Question temp = questions[i];;
            questions[i] = questions[j];
            questions[j] = temp;
        }
    }
}
