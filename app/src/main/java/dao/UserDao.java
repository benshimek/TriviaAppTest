package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import model.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password LIMIT 1")
    User login(String username, String password);

    @Query("SELECT * FROM user_table WHERE username = :username LIMIT 1")
    User getUserByUsername(String username);
}

