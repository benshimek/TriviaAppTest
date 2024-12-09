package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import model.Score;

@Dao
public interface ScoreDao {
    @Insert
    void insert(Score score);

    @Query("SELECT * FROM score_table WHERE user_ID = :userId AND category_ID = :categoryId ORDER BY score DESC LIMIT 1")
    Score getHighScore(int userId, int categoryId);
}
