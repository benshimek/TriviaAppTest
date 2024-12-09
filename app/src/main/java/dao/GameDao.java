package dao;

import androidx.room.Dao;
import androidx.room.Insert;

import model.Game;

@Dao
public interface GameDao {
    @Insert
    void insert(Game game);
}
