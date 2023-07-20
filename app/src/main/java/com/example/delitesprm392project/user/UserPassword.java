package com.example.delitesprm392project.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.delitesprm392project.R;
import com.example.delitesprm392project.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserPassword extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    User account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_password);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        TextView name = findViewById(R.id.pwName);
        TextView email = findViewById(R.id.pwEmail);


        EditText oldPW = findViewById(R.id.oldPassword);
        EditText newPW = findViewById(R.id.newPassword);
        EditText renewPW = findViewById(R.id.renewPassword);

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
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        findViewById(R.id.pwBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserManagement.class);
                view.getContext().startActivity(intent);
            }
        });


        Button btn = findViewById(R.id.pwBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPWs = oldPW.getText().toString();
                String newPWs = newPW.getText().toString();
                String renewPWs = renewPW.getText().toString();
                if (oldPWs.isEmpty() || newPWs.isEmpty() || renewPWs.isEmpty()) {
                    Toast.makeText(UserPassword.this, "Please input all the field", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!oldPWs.equals(account.getPassword())) {
                    Toast.makeText(UserPassword.this, "Wrong old password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!newPWs.equals(renewPWs)) {
                    Toast.makeText(UserPassword.this, "Reinput new password", Toast.LENGTH_SHORT).show();
                    return;
                }
                account.setPassword((newPWs));
                databaseReference.child("Users").child(user.getUid()).setValue(account, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error == null) {
                            Toast.makeText(UserPassword.this, "Updated password successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(UserPassword.this, "Failed to update", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}