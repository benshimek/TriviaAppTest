package model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_table")
public class Question {
    @PrimaryKey(autoGenerate = true)
    public int question_ID;
    public int category_ID;
    public String question_text;
    public int correct_answer_ID;
    public int game_ID;

    public Question(int category_ID, String question_text, int correct_answer_ID, int game_ID) {
        this.category_ID = category_ID;
        this.question_text = question_text;
        this.correct_answer_ID = correct_answer_ID;
        this.game_ID = game_ID;
    }
}
