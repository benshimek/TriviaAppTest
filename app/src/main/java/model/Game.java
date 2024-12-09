package model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "game_table")
public class Game {
    @PrimaryKey(autoGenerate = true)
    public int game_ID;
    public int user_ID;
    public int score;
    //public Date date;
    public int category_ID;

    public Game(int user_ID, int score, int category_ID) {
        this.user_ID = user_ID;
        this.score = score;
        //this.date = date;
        this.category_ID = category_ID;
    }
}
