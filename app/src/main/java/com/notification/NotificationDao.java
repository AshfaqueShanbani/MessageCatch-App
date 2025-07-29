package com.notification;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotificationDao {

    @Insert
    void insert(NotificationModel notification);

    @Query("SELECT * FROM notifications ORDER BY id DESC")
    List<NotificationModel> getAll();
}
