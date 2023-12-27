package com.example.skincure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AdminFaceGender extends AppCompatActivity {

    CardView male , female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_face_gender);
        male = findViewById(R.id.MaleCard);
        female = findViewById(R.id.FemaleCard);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminFaceGender.this, "Add face information for male", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),AdminFaceMale.class);
                startActivity(intent);
                finish();
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminFaceGender.this, "Add face information for female", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),AdminFaceFemale.class);
                startActivity(intent);
                finish();
            }
        });
    }
}