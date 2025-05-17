package com.main.loarlieur;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewQuestionNumberScore;
    private TextView textViewQuestion;
    private Button buttonOption1, buttonOption2, buttonOption3, buttonOption4;

    private String[] questions;
    private String[] options; // Semua opsi digabung
    private int[] answers;

    private int currentQuestionIndex = 0;
    private int score = 0;
    private int totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestionNumberScore = findViewById(R.id.textViewQuestionNumberScore);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        buttonOption1 = findViewById(R.id.buttonOption1);
        buttonOption2 = findViewById(R.id.buttonOption2);
        buttonOption3 = findViewById(R.id.buttonOption3);
        buttonOption4 = findViewById(R.id.buttonOption4);

        buttonOption1.setOnClickListener(this);
        buttonOption2.setOnClickListener(this);
        buttonOption3.setOnClickListener(this);
        buttonOption4.setOnClickListener(this);

        loadQuizData();
        displayQuestion();
    }

    private void loadQuizData() {
        Resources res = getResources();
        questions = res.getStringArray(R.array.quiz_questions);
        options = res.getStringArray(R.array.quiz_options); // Ini berisi semua opsi secara berurutan
        answers = res.getIntArray(R.array.quiz_answers);
        totalQuestions = questions.length;
    }

    private void displayQuestion() {
        if (currentQuestionIndex < totalQuestions) {
            textViewQuestionNumberScore.setText(String.format(getString(R.string.question_number_score),
                    currentQuestionIndex + 1, totalQuestions, score));
            textViewQuestion.setText(questions[currentQuestionIndex]);

            // Ambil 4 opsi untuk pertanyaan saat ini
            // Setiap pertanyaan memiliki 4 opsi
            int optionStartIndex = currentQuestionIndex * 4;
            buttonOption1.setText(options[optionStartIndex]);
            buttonOption2.setText(options[optionStartIndex + 1]);
            buttonOption3.setText(options[optionStartIndex + 2]);
            buttonOption4.setText(options[optionStartIndex + 3]);

        } else {
            finishQuiz();
        }
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        int selectedOptionIndex = -1;

        if (v.getId() == R.id.buttonOption1) {
            selectedOptionIndex = 0;
        } else if (v.getId() == R.id.buttonOption2) {
            selectedOptionIndex = 1;
        } else if (v.getId() == R.id.buttonOption3) {
            selectedOptionIndex = 2;
        } else if (v.getId() == R.id.buttonOption4) {
            selectedOptionIndex = 3;
        }

        checkAnswer(selectedOptionIndex);
    }

    private void checkAnswer(int selectedOptionIndex) {
        if (selectedOptionIndex == answers[currentQuestionIndex]) {
            score++;
            Toast.makeText(this, "Jawaban Benar!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Jawaban Salah.", Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;
        displayQuestion(); // Pindah ke pertanyaan berikutnya atau selesaikan kuis
    }

    private void finishQuiz() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("USER_SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", totalQuestions);
        startActivity(intent);
        finish(); // Tutup QuizActivity agar tidak bisa kembali dengan tombol back
    }
}