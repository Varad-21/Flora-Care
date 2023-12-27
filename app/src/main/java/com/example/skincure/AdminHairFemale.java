package com.example.skincure;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminHairFemale extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextName, editTextPrevention, editTextPrecautions;
    private Button buttonAdd, buttonUpdate, buttonDelete, buttonChooseImage;
    private Uri imageUri;  // Store the selected image URI
    private DatabaseReference databaseReference;
    private String postIdToUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hair_female);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Handle the back button press
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });

        editTextName = findViewById(R.id.editTextName);
        editTextPrevention = findViewById(R.id.editTextPrevention);
        editTextPrecautions = findViewById(R.id.editTextPrecautions);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonChooseImage = findViewById(R.id.buttonChooseImage);

        databaseReference = FirebaseDatabase.getInstance().getReference("posts2");

        buttonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPost();
            }
        });

        // ... (Other code)

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePost();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePost();
            }
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
        }
    }

    private void updatePost() {
        String name = editTextName.getText().toString().trim();
        String prevention = editTextPrevention.getText().toString().trim();
        String precautions = editTextPrecautions.getText().toString().trim();

        if (postIdToUpdate != null && !name.isEmpty() && !prevention.isEmpty() && !precautions.isEmpty()) {
            // Create a Post object with updated data
            Post updatedPost = new Post(postIdToUpdate, name, imageUri.toString(), prevention, precautions);

            // Update the post in the database using the post ID
            databaseReference.child(postIdToUpdate).setValue(updatedPost)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AdminHairFemale.this, "Post updated successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminHairFemale.this, "Failed to update post: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            // Reset the postIdToUpdate after update
            postIdToUpdate = null;
        } else {
            Toast.makeText(this, "Please select a post to update and fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void deletePost() {
        String name = editTextName.getText().toString().trim();

        if (!name.isEmpty()) {
            // Find the post by name in the database
            databaseReference.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Delete the post using its ID
                        String postId = snapshot.getKey();
                        databaseReference.child(postId).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(AdminHairFemale.this, "Post deleted successfully", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(AdminHairFemale.this, "Failed to delete post: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(AdminHairFemale.this, "Failed to delete post: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please enter the name of the post to delete", Toast.LENGTH_SHORT).show();
        }
    }

    private void addPost() {
        String name = editTextName.getText().toString().trim();
        String prevention = editTextPrevention.getText().toString().trim();
        String precautions = editTextPrecautions.getText().toString().trim();

        if (imageUri != null && !name.isEmpty() && !prevention.isEmpty() && !precautions.isEmpty()) {
            // Create a unique key for the post
            String postId = databaseReference.push().getKey();

            // Create a Post object
            Post post = new Post(postId, name, imageUri.toString(), prevention, precautions);

            // Save the post to the database
            databaseReference.child(postId).setValue(post)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AdminHairFemale.this, "Post added successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminHairFemale.this, "Failed to add post: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Please fill in all fields and choose an image", Toast.LENGTH_SHORT).show();
        }
    }
}
