package com.example.skincure;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiresApi(api = 30)
public class AdminReports extends AppCompatActivity {

    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_MANAGE_EXTERNAL_STORAGE = 2; // Optional

    private DatabaseReference usersReference;
    private ListView usersListView;
    private Button exportButton;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reports);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersReference = database.getReference("Users");

        usersListView = findViewById(R.id.usersListView);
         //exportButton = findViewById(R.id.exportButton);
        back = findViewById(R.id.idback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });



        // Fetch user data and display it
        fetchAndDisplayUserData();
    }



    private void fetchAndDisplayUserData() {
        usersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> usersList = new ArrayList<>();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    if (user != null) {
                        String userData = "Email: " + user.getEmail() +
                                "\nPassword: " + user.getPassword() +
                                "\n"
                                ;
                        usersList.add(userData);
                    }
                }

                // Create an adapter and set it to your ListView
                ArrayAdapter<String> adapter = new ArrayAdapter<>(AdminReports.this, android.R.layout.simple_list_item_1, usersList);
                usersListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(AdminReports.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generatePDFReport() {
        // Create a PDF document
        try {
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(Objects.requireNonNull(getExternalFilesDir(null)).getPath() + "/user_report.pdf"));
            Document document = new Document(pdfDocument);

            // Retrieve data from Firebase and add it to the PDF
            usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String uid = userSnapshot.getKey();
                        String name = userSnapshot.child("name").getValue(String.class);
                        String email = userSnapshot.child("email").getValue(String.class);
                        String password = userSnapshot.child("password").getValue(String.class);
                        int userType = userSnapshot.child("usertype").getValue(Integer.class);

                        // Add user data to the PDF
                        document.add(new Paragraph("User ID: " + uid));
                        document.add(new Paragraph("Email: " + email));
                        document.add(new Paragraph("Password: " + password));
                        document.add(new Paragraph("User Type: " + userType));
                        document.add(new Paragraph("\n"));
                    }

                    // Close the PDF document
                    document.close();

                    // Inform the user that the report has been generated
                    Toast.makeText(AdminReports.this, "PDF Report Generated", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error
                    Toast.makeText(AdminReports.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            // Handle exceptions, e.g., file system errors
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
