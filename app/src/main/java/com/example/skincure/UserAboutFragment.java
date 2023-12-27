package com.example.skincure;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserAboutFragment extends Fragment {

    private TextView textAboutUsContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_about, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        textAboutUsContent = view.findViewById(R.id.text_about_us_content);

        // Initialize Firebase Realtime Database reference
        DatabaseReference aboutUsRef = FirebaseDatabase.getInstance().getReference("about_us");

        // Retrieve and display "About Us" content from Firebase
        aboutUsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String content = dataSnapshot.getValue(String.class);
                if (content != null) {
                    textAboutUsContent.setText(content);
                } else {
                    textAboutUsContent.setText("About Us content not available.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
                textAboutUsContent.setText("Failed to load About Us content.");
            }
        });

        return view;
    }
}
