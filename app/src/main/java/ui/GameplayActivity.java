package ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.triviaapp3.R;

import java.util.List;

import model.Answer;
import model.Question;
import model.Score;
import repository.TriviaRepository;

public class GameplayActivity extends AppCompatActivity {

    private TriviaRepository repository;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private TextView questionTextView, scoreTextView;
    private RadioGroup answersGroup;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        // Initialize repository and UI elements
        repository = new TriviaRepository(getApplication());
        questionTextView = findViewById(R.id.question_text);
        scoreTextView = findViewById(R.id.score_text);
        answersGroup = findViewById(R.id.answers_group);
        submitButton = findViewById(R.id.submit_button);

        // Get the category ID (passed from Main Menu)
        int categoryId = getIntent().getIntExtra("CATEGORY_ID", -1);

        // Fetch questions for the selected category
        fetchQuestions(categoryId);

        // Setup button click listener
        submitButton.setOnClickListener(v -> {
            checkAnswer();
            loadNextQuestion();
        });
    }

    private void fetchQuestions(int categoryId) {
        // Get the questions for the selected category from the repository
        questions = repository.getQuestionsByCategory(categoryId);

        if (questions != null && !questions.isEmpty()) {
            displayQuestion(questions.get(currentQuestionIndex));
        }
    }

    private void displayQuestion(Question question) {
        // Display the question text
        questionTextView.setText(question.question_text);

        // Clear previous answers
        answersGroup.removeAllViews();

        // Get the possible answers and display them
        List<Answer> answers = repository.getAnswersForQuestion(question.question_ID);
        for (Answer answer : answers) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answer.answer_text);
            radioButton.setId(answer.answer_ID);
            answersGroup.addView(radioButton);
        }
    }

    private void checkAnswer() {
        // Check if the selected answer is correct
        int selectedAnswerId = answersGroup.getCheckedRadioButtonId();
        Answer selectedAnswer = repository.getAnswerById(selectedAnswerId);

        // If the selected answer matches the correct answer, increment the score
        if (selectedAnswer != null && selectedAnswer.answer_ID == questions.get(currentQuestionIndex).correct_answer_ID) {
            score++;
        }
    }

    private void loadNextQuestion() {
        currentQuestionIndex++;

        if (currentQuestionIndex < questions.size()) {
            // Display the next question
            displayQuestion(questions.get(currentQuestionIndex));
        } else {
            // Game Over, show score and navigate to the Score Screen
            navigateToScoreScreen();
        }
    }

    private void navigateToScoreScreen() {
        // Save the score to the database
        int userId = getSharedPreferences("prefs", MODE_PRIVATE).getInt("user_id", -1);
        int categoryId = getIntent().getIntExtra("CATEGORY_ID", -1);
        Score scoreRecord = new Score(userId, 0, score, categoryId, 0);
        repository.insertScore(scoreRecord);

        // Navigate to Score Screen
        Intent intent = new Intent(GameplayActivity.this, ScoreActivity.class);
        intent.putExtra("SCORE", score);
        startActivity(intent);
        finish();
    }
}
