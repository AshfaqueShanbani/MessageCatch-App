package com.notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationStorage {
    private static final List<NotificationModel> notificationList = new ArrayList<>();

    public static void addNotification(NotificationModel model) {
        notificationList.add(0, model); // Add to top
    }

    public static List<NotificationModel> getNotifications() {
        return notificationList;
    }
}
