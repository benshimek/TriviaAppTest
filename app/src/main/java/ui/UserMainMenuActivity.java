package ui;

// THIS CLASS IS FOR STANDARD USERS ONLY //

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.triviaapp3.R;

import java.util.List;

import model.Category;
import repository.TriviaRepository;

public class UserMainMenuActivity extends AppCompatActivity {

    private TriviaRepository repository;
    private ListView categoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        categoryListView = findViewById(R.id.category_list);

        repository = new TriviaRepository(getApplication());

        List<Category> categories = repository.getCategories();
        // Set up ListView with the categories
    }
}