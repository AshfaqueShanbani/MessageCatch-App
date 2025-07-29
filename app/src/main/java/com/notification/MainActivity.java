package com.notification;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.net.URLEncoder;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import xyz.hasnat.sweettoast.SweetToast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NotificationAdapter adapter;
    Handler handler = new Handler();
    Runnable refreshRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialButton btn=(MaterialButton) findViewById(R.id.callbtn);
        btn.setOnClickListener(v -> {
           showDeveloperHelplineDialog();
        });


        // Ask for notification access
        if (!Settings.Secure.getString(getContentResolver(),
                "enabled_notification_listeners").contains(getPackageName())) {
            startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS));
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadNotifications();

        refreshRunnable = () -> {
            loadNotifications();
            handler.postDelayed(refreshRunnable, 10000); // every 10s
        };
    }

    private void loadNotifications() {
        new Thread(() -> {
            List<NotificationModel> list = NotificationDatabase.getInstance(getApplicationContext())
                    .notificationDao().getAll();

            runOnUiThread(() -> {
                adapter = new NotificationAdapter(list);
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(refreshRunnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(refreshRunnable);
    }
    private void showDeveloperHelplineDialog() {
        String developerName = "Ashfaque Shanbani";
        String developerPhone = "+923173490004";
        String whatsappMessage = "Hi Ashfaque! I have new idea regarding the MassageCatch app.";

        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE);
        dialog.setTitleText("Need Help?");
        dialog.setContentText("Developer: " + developerName + "\n📞 " + developerPhone);
        dialog.setConfirmText("Call");
        dialog.setCancelText("WhatsApp");
        dialog.showCancelButton(true);

        dialog.setConfirmClickListener(sDialog -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + developerPhone));
            startActivity(intent);
            sDialog.dismissWithAnimation();
        });

        dialog.setCancelClickListener(sDialog -> {
            try {
                String url = "https://wa.me/" + developerPhone.replace("+", "") +
                        "?text=" + URLEncoder.encode(whatsappMessage, "UTF-8");
                Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
                whatsappIntent.setData(Uri.parse(url));
                startActivity(whatsappIntent);
            } catch (Exception e) {
                SweetToast.error(this, "WhatsApp not installed");
            }
            sDialog.dismissWithAnimation();
        });

        dialog.show();
    }

}

