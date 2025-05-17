package com.main.loarlieur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewFinalScore;
    private Button buttonPlayAgain;
    private Button buttonBackToStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewFinalScore = findViewById(R.id.textViewFinalScore);
        buttonPlayAgain = findViewById(R.id.buttonPlayAgain);
        buttonBackToStart = findViewById(R.id.buttonBackToStart);

        int score = getIntent().getIntExtra("USER_SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);

        textViewFinalScore.setText(String.format(getString(R.string.your_score), score, totalQuestions));

        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
                startActivity(intent);
                finish(); // Tutup ResultActivity
            }
        });

        buttonBackToStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Hapus stack activity sebelumnya
                startActivity(intent);
                finish(); // Tutup ResultActivity
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Jika pengguna menekan tombol back, arahkan ke MainActivity
        super.onBackPressed();
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}