package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import model.Answer;

@Dao
public interface AnswerDao {
    @Insert
    void insert(Answer answer);

    @Query("SELECT * FROM answer_table WHERE question_ID = :questionId")
    List<Answer> getAnswersForQuestion(int questionId);

    @Query("SELECT * FROM answer_table WHERE answer_ID = :answerId LIMIT 1")
    Answer getAnswerById(int answerId);

}
