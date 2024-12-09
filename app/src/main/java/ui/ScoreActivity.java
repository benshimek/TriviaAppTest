package ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.triviaapp3.R;

public class ScoreActivity extends AppCompatActivity {

    private TextView scoreTextView;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTextView = findViewById(R.id.score_text);
        exitButton = findViewById(R.id.exit_button);

        // Retrieve the score from the intent
        int score = getIntent().getIntExtra("SCORE", 0);
        scoreTextView.setText(String.valueOf(score));

        exitButton.setOnClickListener(v -> {
            // Exit the game and return to Main Menu or Login Screen
            Intent intent = new Intent(ScoreActivity.this, LoginActivity.class); // or LoginActivity
            startActivity(intent);
            finish();
        });
    }
}

