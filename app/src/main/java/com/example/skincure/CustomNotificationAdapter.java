package com.example.skincure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skincure.YourNotificationModel;

import java.util.List;

public class CustomNotificationAdapter extends BaseAdapter {
    private List<YourNotificationModel> notifications;
    private Context context;

    public CustomNotificationAdapter(Context context, List<YourNotificationModel> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return notifications.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        YourNotificationModel notification = (YourNotificationModel) getItem(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(notification.getMessage());

        return convertView;
    }
}
