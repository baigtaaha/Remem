package com.example.memorypowertest.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "results")
public class ResultEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String date;
    public int score;
    public String level;

    public ResultEntity(String date, int score, String level) {
        this.date = date;
        this.score = score;
        this.level = level;
    }
}
