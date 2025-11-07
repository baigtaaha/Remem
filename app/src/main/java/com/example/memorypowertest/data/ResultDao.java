package com.example.memorypowertest.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ResultDao {
    @Insert
    void insertResult(ResultEntity result);

    @Query("SELECT * FROM results ORDER BY id DESC")
    List<ResultEntity> getAllResults();
}
