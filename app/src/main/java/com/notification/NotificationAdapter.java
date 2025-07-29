package com.notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    List<NotificationModel> list;

    public NotificationAdapter(List<NotificationModel> list) {
        this.list = list;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView appName, title, text, time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.appName);
            title = itemView.findViewById(R.id.title);
            text = itemView.findViewById(R.id.text);
            time = itemView.findViewById(R.id.time);
        }
    }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.MyViewHolder holder, int position) {
        NotificationModel model = list.get(position);
        holder.appName.setText(model.appName);
        holder.title.setText(model.title);
        holder.text.setText(model.text);
        holder.time.setText(model.time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
