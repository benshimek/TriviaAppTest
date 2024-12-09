package repository;

import android.app.Application;

import java.util.List;

import dao.AnswerDao;
import dao.CategoryDao;
import dao.GameDao;
import dao.QuestionDao;
import dao.ScoreDao;
import dao.UserDao;
import database.TriviaDatabase;
import model.Answer;
import model.Category;
import model.Game;
import model.Question;
import model.Score;
import model.User;

public class TriviaRepository {

    private UserDao userDao;
    private CategoryDao categoryDao;
    private GameDao gameDao;
    private QuestionDao questionDao;
    private AnswerDao answerDao;
    private ScoreDao scoreDao;
    

    public TriviaRepository(Application application) {
        TriviaDatabase database = TriviaDatabase.getDatabase(application);
        userDao = database.userDao();
        categoryDao = database.categoryDao();
        gameDao = database.gameDao();
        questionDao = database.questionDao();
        answerDao = database.answerDao();
        scoreDao = database.scoreDao();
    }

    public void insertUser(User user) {
        new Thread(() -> userDao.insert(user)).start();
    }

    public User loginUser(String username, String password) {
        return userDao.login(username, password);
    }

    public List<Category> getCategories() {
        return categoryDao.getAllCategories();
    }

    public void insertCategory(Category category) {
        new Thread(() -> categoryDao.insert(category)).start();
    }

    public void insertGame(Game game) {
        new Thread(() -> gameDao.insert(game)).start();
    }

    public void insertQuestion(Question question) {
        new Thread(() -> questionDao.insert(question)).start();
    }

    public void insertAnswer(Answer answer) {
        new Thread(() -> answerDao.insert(answer)).start();
    }

    public void insertScore(Score score) {
        new Thread(() -> scoreDao.insert(score)).start();
    }

    public Score getHighScore(int userId, int categoryId) {
        return scoreDao.getHighScore(userId, categoryId);
    }

    public List<Question> getQuestionsByCategory(int categoryId) {
        return questionDao.getQuestionsByCategory(categoryId);
    }

    public List<Answer> getAnswersForQuestion(int questionId) {
        return answerDao.getAnswersForQuestion(questionId);
    }

    public Answer getAnswerById(int answerId) {
        return answerDao.getAnswerById(answerId);
    }

    // TriviaRepository.java or UserDao.java

    public void insertDefaultAdminUserIfNotExists() {
        // Check if there are any users in the database
        User user = userDao.getUserByUsername("Admin2");
        if (user == null) {
            // Insert the default Admin user if it doesn't exist
            User defaultAdmin = new User("Admin2", "Admin2", true);
            userDao.insert(defaultAdmin); // Insert into the database
        }
    }

        public boolean validateUser(String username, String password) {
            // Logic to validate user credentials (e.g., compare with stored user data)
            // This is just a basic example. Replace with your actual logic to check the username and password.

            User user = getUserByUsername(username);
            return user != null && user.getPassword().equals(password);
        }

        public User getUserByUsername(String username) {
            // Retrieve user from database or other data source
            // Example: return database.findUserByUsername(username);
            return null; // Placeholder, replace with actual logic
        }




}
