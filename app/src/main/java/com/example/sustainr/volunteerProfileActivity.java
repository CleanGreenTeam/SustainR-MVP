package com.example.sustainr;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class volunteerProfileActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    TextView name;
    TextView location;
    TextView points;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_profile);

        String id =mAuth.getCurrentUser().getUid().toString();
        name = findViewById(R.id.name);
        location = findViewById(R.id.location);
        points = findViewByID(R.id.points);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    Volunteer usersnap = user.getValue(Volunteer.class);
                    if (usersnap.getId().equals(mAuth.getCurrentUser().getUid().toString())) {
                        name.setText(usersnap.getName());
                        location.setText(usersnap.getLocation());
                        points.setText(usersnap.getPoints());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
