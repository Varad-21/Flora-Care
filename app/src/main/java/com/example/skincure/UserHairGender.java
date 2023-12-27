package com.example.skincure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserHairGender extends AppCompatActivity {

    CardView fcard,Mcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hair_gender);

        fcard = findViewById(R.id.FemaleCard);
        Mcard = findViewById(R.id.MaleCard);


        Mcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserHairMale.class);
                startActivity(intent);
                finish();
            }
        });

        fcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserHairFemale.class);
                startActivity(intent);
                finish();
            }
        });
    }
}