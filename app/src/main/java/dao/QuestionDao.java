package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import model.Question;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question question);

    @Query("SELECT * FROM question_table WHERE category_ID = :categoryId")
    List<Question> getQuestionsByCategory(int categoryId);

}
