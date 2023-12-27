package com.example.skincure;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AdminAboutus extends AppCompatActivity {

    private EditText editAboutUsContent;
    private Button btnAdd, btnUpdate, btnDelete;
    private DatabaseReference aboutUsRef;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_aboutus);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        editAboutUsContent = findViewById(R.id.edit_about_us_content);
        btnAdd = findViewById(R.id.btn_add);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        firebaseAuth = FirebaseAuth.getInstance();
        aboutUsRef = FirebaseDatabase.getInstance().getReference("about_us");

        // Load existing content (if any) into the EditText
        aboutUsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String content = dataSnapshot.getValue(String.class);
                if (content != null) {
                    editAboutUsContent.setText(content);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors here
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAboutUsContent();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAboutUsContent();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAboutUsContent();
            }
        });
    }

    private void addAboutUsContent() {
        String content = editAboutUsContent.getText().toString();
        aboutUsRef.setValue(content);
        Toast.makeText(this, "About Us content added.", Toast.LENGTH_SHORT).show();
    }

    private void updateAboutUsContent() {
        String content = editAboutUsContent.getText().toString();
        aboutUsRef.setValue(content);
        Toast.makeText(this, "About Us content updated.", Toast.LENGTH_SHORT).show();
    }

    private void deleteAboutUsContent() {
        aboutUsRef.removeValue();
        editAboutUsContent.setText("");
        Toast.makeText(this, "About Us content deleted.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
        startActivity(intent);
        finish();
        return false;
    }
}
