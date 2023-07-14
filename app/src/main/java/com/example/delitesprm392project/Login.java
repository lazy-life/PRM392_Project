package com.example.delitesprm392project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText editTextPass, editTextPhone;
    TextView editTextSignup;
    Button buttonLogin;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextPass = findViewById(R.id.pass);
        editTextPhone = findViewById(R.id.email);
        buttonLogin = findViewById(R.id.btnlogin);
        editTextSignup = findViewById(R.id.signup);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //firebaseDatabase = FirebaseDatabase.getInstance();

                // below line is used to get reference for our database.
                //databaseReference = firebaseDatabase.getReference("Users");

                // initializing our object
                // class variable.
                final String txtPhone = editTextPhone.getText().toString();
                final String txtPass = editTextPass.getText().toString();

                if (txtPhone.isEmpty() || txtPass.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter your mobile and password", Toast.LENGTH_SHORT).show();
                } else {
                    SignIn(txtPhone,txtPass);
//                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            try {
//                                if (snapshot.hasChild(txtPhone)) {
//                                    final String getPassword = snapshot.child(txtPhone).child("password").getValue(String.class);
//                                    final String getRole = snapshot.child(txtPhone).child("role").getValue(String.class);
//                                    if (getPassword.equals(txtPass)) {
//                                        if (getRole.equals("1")) {
//                                            Intent intent = new Intent(Login.this, Home.class);
//                                            startActivity(intent);
//                                            finish();
//                                        } else {
//
//                                        }
//
//                                    } else {
//                                        Toast.makeText(Login.this, "Phone Number or Password invalid1!", Toast.LENGTH_SHORT).show();
//                                    }
//                                } else {
//                                    Toast.makeText(Login.this, "Phone Number or Password invalid2!", Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (Exception e) {
//                                Toast.makeText(Login.this, "Phone Number or Password invalid!", Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                            // Handle database error
//                        }
//                    });
                }
            }
        });

        //Click to move Sign up page
        editTextSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void SignIn(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}