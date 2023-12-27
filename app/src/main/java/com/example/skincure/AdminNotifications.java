package com.example.skincure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class AdminNotifications extends AppCompatActivity {

    private EditText userEmail;
    private EditText notificationText;
    private Button sendNotificationButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notifications);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        userEmail = findViewById(R.id.userEmail);
        notificationText = findViewById(R.id.notificationText);
        sendNotificationButton = findViewById(R.id.sendNotificationButton);

        // Initialize the Firebase Realtime Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("notifications");

        sendNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmailAddress = userEmail.getText().toString();
                String message = notificationText.getText().toString();

                // Create a new notification object and store it in the database
                String key = databaseReference.push().getKey();
                databaseReference.child(key).setValue(new UserNotification(userEmailAddress, message));

                Toast.makeText(AdminNotifications.this, "Notification send to user", Toast.LENGTH_SHORT).show();
            }
        })
    ;}

    @Override
    public boolean onSupportNavigateUp() {
        // This method is called when the back button in the toolbar is pressed.
        onBackPressed();
        Intent intent = new Intent(getApplicationContext(),AdminDashboard.class);
        startActivity(intent);
        finish();
        return false;
    }
}
