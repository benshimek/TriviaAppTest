package model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class Category {
    @PrimaryKey(autoGenerate = true)
    public int category_ID;
    public String title;
    public String description;

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
