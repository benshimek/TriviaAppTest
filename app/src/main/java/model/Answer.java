package model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "answer_table")
public class Answer {
    @PrimaryKey(autoGenerate = true)
    public int answer_ID;
    public int question_ID;
    public String answer_text;

    public Answer(int question_ID, String answer_text) {
        this.question_ID = question_ID;
        this.answer_text = answer_text;
    }
}
