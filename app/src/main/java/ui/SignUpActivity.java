package ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.triviaapp3.R;

import model.User;
import repository.TriviaRepository;

public class SignUpActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button signupButton;
    private TriviaRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameEditText = findViewById(R.id.signup_username_input);
        passwordEditText = findViewById(R.id.signup_password_input);
        signupButton = findViewById(R.id.signup_button);

        // Initialize repository to interact with the database
        repository = new TriviaRepository(getApplication());

        signupButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Check if username and password are not empty
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ensure the username is not already taken
            if (repository.getUserByUsername(username) != null) {
                Toast.makeText(SignUpActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
            } else {
                // Create a new user and insert it into the database
                User newUser = new User(username, password, false);  // 'false' might represent 'isAdmin' or a similar flag
                repository.insertUser(newUser);

                Toast.makeText(SignUpActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();

                // Optionally, navigate to the login activity or directly to the main menu
                finish();  // Close the sign-up activity and return to the login activity
            }
        });
    }
}


