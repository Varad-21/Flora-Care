package com.example.skincure;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.skincure.CustomNotificationAdapter;
import com.example.skincure.R;
import com.example.skincure.YourNotificationModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserNotificationFragment extends Fragment {

    private ListView notificationListView;
    private CustomNotificationAdapter customAdapter;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_notification, container, false);

        notificationListView = view.findViewById(R.id.notificationListView);

        // Initialize the Firebase Realtime Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("notifications");

        // Create a custom adapter and set it to the ListView
        customAdapter = new CustomNotificationAdapter(requireContext(), new ArrayList<>());
        notificationListView.setAdapter(customAdapter);

        // Retrieve notifications from the Firebase Realtime Database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<YourNotificationModel> notifications = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    YourNotificationModel notification = snapshot.getValue(YourNotificationModel.class);
                    notifications.add(notification);
                }
                customAdapter = new CustomNotificationAdapter(requireContext(), notifications);
                notificationListView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors or failures here
            }
        });

        return view;
    }

}
