package database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import dao.AnswerDao;
import dao.CategoryDao;
import dao.GameDao;
import dao.QuestionDao;
import dao.ScoreDao;
import dao.UserDao;
import model.Answer;
import model.Category;
import model.Game;
import model.Question;
import model.Score;
import model.User;

@Database(entities = {User.class, Category.class, Game.class, Question.class, Answer.class, Score.class}, version = 1, exportSchema = false)
public abstract class TriviaDatabase extends RoomDatabase {

    private static volatile TriviaDatabase INSTANCE;
    public abstract UserDao userDao();
    public abstract CategoryDao categoryDao();
    public abstract GameDao gameDao();
    public abstract QuestionDao questionDao();
    public abstract AnswerDao answerDao();
    public abstract ScoreDao scoreDao();

    // Singleton pattern to ensure only one instance of the database
    /*public static synchronized TriviaDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TriviaDatabase.class, "trivia_database")
                    .fallbackToDestructiveMigration() // Allows destructive migration if needed
                    .build();
        }
        return INSTANCE;
    }*/

    public static TriviaDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (TriviaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TriviaDatabase.class, "trivia_database")
                            .fallbackToDestructiveMigration()  // Optional: handles migrations
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
