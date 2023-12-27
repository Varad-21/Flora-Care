package com.example.skincure;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError; // Import DatabaseError
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserFeedbackFragment extends Fragment {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText feedbackEditText;
    private Button submitFeedbackButton;

    private DatabaseReference feedbackRef;
    private RecyclerView recyclerView;
    private FeedbackAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_feedback, container, false);

        feedbackRef = FirebaseDatabase.getInstance().getReference("feedback");
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FeedbackAdapter();

        feedbackRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Feedback> feedbackList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String feedback = snapshot.child("feedback").getValue(String.class);

                    Feedback feedbackEntry = new Feedback(name, email, feedback);
                    feedbackList.add(feedbackEntry);
                }
                adapter.setFeedbackList(feedbackList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors (e.g., log the error or show an error message)
                // For example:
                Log.e("UserFeedbackFragment", "Database Error: " + databaseError.getMessage());
            }
        });

        nameEditText = view.findViewById(R.id.nameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        feedbackEditText = view.findViewById(R.id.feedbackEditText);
        submitFeedbackButton = view.findViewById(R.id.submitFeedbackButton);

        submitFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String feedback = feedbackEditText.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !feedback.isEmpty()) {
                    // Save the feedback to the database
                    DatabaseReference newFeedbackRef = feedbackRef.push();
                    newFeedbackRef.child("name").setValue(name);
                    newFeedbackRef.child("email").setValue(email);
                    newFeedbackRef.child("feedback").setValue(feedback);

                    nameEditText.setText("");
                    emailEditText.setText("");
                    feedbackEditText.setText("");
                }
            }
        });

        return view;
    }
}
