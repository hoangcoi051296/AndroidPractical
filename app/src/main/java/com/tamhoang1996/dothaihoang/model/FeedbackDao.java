package com.tamhoang1996.dothaihoang.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FeedbackDao {
    @Insert
    void insertFeedback(Feedback feedback);

    @Query("select * from feedback")
    List<Feedback> getAllUser();
}
