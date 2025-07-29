package com.notification;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NotificationModel.class}, version = 1)
public abstract class NotificationDatabase extends RoomDatabase {
    private static NotificationDatabase instance;

    public abstract NotificationDao notificationDao();

    public static synchronized NotificationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            NotificationDatabase.class, "notification_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

