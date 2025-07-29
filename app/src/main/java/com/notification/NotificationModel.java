package com.notification;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notifications")
public class NotificationModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String appName;
    public String title;
    public String text;
    public String time;

    public NotificationModel(String appName, String title, String text, String time) {
        this.appName = appName;
        this.title = title;
        this.text = text;
        this.time = time;
    }
}
