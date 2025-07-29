package com.notification;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyNotificationListener extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String pkg = sbn.getPackageName();

        if (!pkg.equals("com.whatsapp") && !pkg.equals("com.whatsapp.w4b")) return;

        String title = sbn.getNotification().extras.getString("android.title", "");
        String text = sbn.getNotification().extras.getString("android.text", "");
        String time = new SimpleDateFormat("hh:mm a, dd MMM yyyy", Locale.getDefault()).format(new Date());

        NotificationModel model = new NotificationModel(pkg, title, text, time);

        new Thread(() -> {
            NotificationDatabase.getInstance(getApplicationContext())
                    .notificationDao().insert(model);
        }).start();
    }
}
