package model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "score_table")
public class Score {
    @PrimaryKey(autoGenerate = true)
    public int score_ID;
    public int user_ID;
    public int game_ID;
    public int score;
    public int category_ID;
    public int category_high_score;

    public Score(int user_ID, int game_ID, int score, int category_ID, int category_high_score) {
        this.user_ID = user_ID;
        this.game_ID = game_ID;
        this.score = score;
        this.category_ID = category_ID;
        this.category_high_score = category_high_score;
    }
}
