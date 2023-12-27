package com.example.skincure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Register all the ImageButtons with their appropriate IDs
        ImageButton backB = findViewById(R.id.backB);
        ImageButton logOutB = findViewById(R.id.logOutB);
        ImageButton profileB = findViewById(R.id.profileB);



        // Register all the Buttons with their appropriate IDs


        // Register all the CardViews with their appropriate IDs
        CardView FaceTreatmentAdmin = findViewById(R.id.FaceTreatmentAdmin);
        CardView HairTreatmentAdmin = findViewById(R.id.HairTreatmentAdmin);
        CardView DietPlanAdmin = findViewById(R.id.DietPlanAdmin);
        CardView NotifierAdmin = findViewById(R.id.NotifierAdmin);
        CardView FeedbackAdmin = findViewById(R.id.FeedbackAdmin);
        CardView AboutusAdmin = findViewById(R.id.AboutusAdmin);

        // Handle each of the ImageButtons with the OnClickListener
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "User Reports", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminReports.class);
                startActivity(intent);
                finish();
            }
        });
        logOutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Logout Button", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        profileB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Hello User", Toast.LENGTH_SHORT).show();
            }
        });

//         Handle each of the Buttons with the OnClickListener

//        todoB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(AdminDashboard.this, "TODO LIST", Toast.LENGTH_SHORT).show();
//            }
//        });
//        editProfileB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(AdminDashboard.this, "Editing Profile", Toast.LENGTH_SHORT).show();
//            }
//        });

        // Handle each of the CardViews with the OnClickListener
        FaceTreatmentAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Add Various Plant Details for User", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminFaceMale.class);
                startActivity(intent);
                finish();
            }
        });
        HairTreatmentAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Add Suggestions for Plants;", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminHairMale.class);
                startActivity(intent);
                finish();
            }
        });
        DietPlanAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Add Fertilizer", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminNotifications.class);
                startActivity(intent);
                finish();
            }
        });
        NotifierAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Add Reminder to User", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminNotifications.class);
                startActivity(intent);
                finish();
                 }
        });
        FeedbackAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "View User Plant Details", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),AdminFeedback.class);
                startActivity(intent);
                finish();
            }
        });
        AboutusAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminDashboard.this, "Admin Add about us", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),AdminAboutus.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
