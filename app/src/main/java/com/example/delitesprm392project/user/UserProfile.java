package com.example.delitesprm392project.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.delitesprm392project.R;
import com.example.delitesprm392project.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class UserProfile extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    User account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        TextView name = findViewById(R.id.tvProfileName);
        TextView email = findViewById(R.id.tvProfileEmail);
        EditText etName = findViewById(R.id.editextUserName);

        EditText etEmail = findViewById(R.id.editextUserEmail);
        EditText etPhone = findViewById(R.id.editextUserPhone);
        EditText etAddress = findViewById(R.id.editextUserAddress);

        if (user == null) {
            Toast.makeText(this, "hehe", Toast.LENGTH_SHORT).show();
        } else {
            databaseReference.child("Users").orderByChild("email").equalTo(user.getEmail()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        account = dataSnapshot.getValue(User.class);
                        name.setText(account.getName());
                        email.setText(account.getEmail());
                        etName.setText(account.getName());
                        etEmail.setText(account.getEmail());
                        etPhone.setText(account.getPhone());
                        etAddress.setText(account.getAddress());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        Button btn = findViewById(R.id.userProfileUpdateBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();
                String add = etAddress.getText().toString();
                User newUser = new User(name,email,phone,account.getPassword(),account.getRole(),add);
                databaseReference.child("Users").child(user.getUid()).setValue(newUser, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error == null) {
                            Toast.makeText(UserProfile.this, "Updated profile successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(UserProfile.this, "Failed to update", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}