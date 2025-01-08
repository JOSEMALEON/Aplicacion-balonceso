package com.example.contadorbaloncesto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int scoreLocal = 0;
    private int scoreVisitor = 0;

    private TextView tvScoreLocal;
    private TextView tvScoreVisitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular vistas
        tvScoreLocal = findViewById(R.id.tv_score_local);
        tvScoreVisitor = findViewById(R.id.tv_score_visitor);

        Button btnAdd1Local = findViewById(R.id.btn_add1_local);
        Button btnAdd2Local = findViewById(R.id.btn_add2_local);
        Button btnSubtract1Local = findViewById(R.id.btn_subtract1_local);

        Button btnAdd1Visitor = findViewById(R.id.btn_add1_visitor);
        Button btnAdd2Visitor = findViewById(R.id.btn_add2_visitor);
        Button btnSubtract1Visitor = findViewById(R.id.btn_subtract1_visitor);

        Button btnReset = findViewById(R.id.btn_reset);
        Button btnShowResults = findViewById(R.id.btn_show_results);

        // Configurar listeners
        btnAdd1Local.setOnClickListener(v -> updateScore(true, 1));
        btnAdd2Local.setOnClickListener(v -> updateScore(true, 2));
        btnSubtract1Local.setOnClickListener(v -> updateScore(true, -1));

        btnAdd1Visitor.setOnClickListener(v -> updateScore(false, 1));
        btnAdd2Visitor.setOnClickListener(v -> updateScore(false, 2));
        btnSubtract1Visitor.setOnClickListener(v -> updateScore(false, -1));

        btnReset.setOnClickListener(v -> resetScores());
        btnShowResults.setOnClickListener(v -> navigateToScoreActivity());
    }

    private void updateScore(boolean isLocal, int points) {
        if (isLocal) {
            scoreLocal = Math.max(scoreLocal + points, 0); // Evitar bajar de 0
            tvScoreLocal.setText(String.valueOf(scoreLocal));
        } else {
            scoreVisitor = Math.max(scoreVisitor + points, 0); // Evitar bajar de 0
            tvScoreVisitor.setText(String.valueOf(scoreVisitor));
        }
    }

    private void resetScores() {
        scoreLocal = 0;
        scoreVisitor = 0;
        tvScoreLocal.setText(String.valueOf(scoreLocal));
        tvScoreVisitor.setText(String.valueOf(scoreVisitor));
    }

    private void navigateToScoreActivity() {
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("SCORE_LOCAL", scoreLocal);
        intent.putExtra("SCORE_VISITOR", scoreVisitor);
        startActivity(intent);
    }
}
