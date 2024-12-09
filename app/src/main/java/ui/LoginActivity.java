package ui;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.triviaapp3.R;

import model.User;
import repository.TriviaRepository;
import ui.SignUpActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameInput, passwordInput;
    private Button loginButton;
    private TextView signUpText;

    private TriviaRepository triviaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);  // Set the layout

        // Find views by ID
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);
        signUpText = findViewById(R.id.sign_up_text);

        // Initialize the repository (this assumes you already have a reference to your database)
        triviaRepository = new TriviaRepository(getApplication());

        // Insert default admin user if it doesn't exist
//        triviaRepository.insertDefaultAdminUserIfNotExists();



        // Set up button click listeners, etc.
        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            // Handle the login logic here (e.g., check credentials, etc.)
        });


        // Link to Sign Up Activity
        signUpText.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            // Check if username and password are correct
            if (triviaRepository.validateUser(username, password)) {
                // If valid, navigate to UserMainMenuActivity
                Intent intent = new Intent(LoginActivity.this, UserMainMenuActivity.class);
                startActivity(intent);
                finish();  // Optional: Close LoginActivity so user can't go back to it
            } else {
                // Show error message if credentials are incorrect
                Toast.makeText(LoginActivity.this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });

    }



}
