package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import model.Category;

@Dao
public interface CategoryDao {
    @Insert
    void insert(Category category);

    @Query("SELECT * FROM category_table")
    List<Category> getAllCategories();
}
