package com.example.delitesprm392project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.delitesprm392project.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {

    EditText editTextFullName, editTextPass, editTextEmail, editTextPhone;
    TextView editTextSignin;
    Button buttonRegister;
    // creating a variable for our
    // Firebase Database.
    FirebaseAuth firebaseAuth;


    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.email);
        editTextFullName = findViewById(R.id.fullname);
        editTextPass = findViewById(R.id.pass);
        editTextPhone = findViewById(R.id.phone);
        editTextSignin = findViewById(R.id.signin);
        buttonRegister = findViewById(R.id.btnlogin);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //firebaseDatabase = FirebaseDatabase.getInstance();

                // below line is used to get reference for our database.
                //databaseReference = firebaseDatabase.getReference("Users");

                // initializing our object
                // class variable.
                user = new User();

                String name = editTextFullName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String pass = editTextPass.getText().toString();
                String role = "1";


                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(Signup.this, "Fill all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phone)) {
                                Toast.makeText(Signup.this, "Phone Number Existed!", Toast.LENGTH_SHORT).show();
                            } else {
                                Signup(name, email, phone, pass, role, "ha noi");
                                //writeNewUser(name, email, phone, pass, role,"hanoi");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle database error
                        }
                    });
                }
            }
        });

        editTextSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void writeNewUser(String name, String email, String phone, String pass, String role, String addres) {
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(pass);
        user.setRole(role);
        user.setAddress(addres);
        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.child(phone).setValue(user);

                // after adding this data we are showing toast message.
                Toast.makeText(Signup.this, "You have register successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(Signup.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = new Intent(Signup.this, Login.class);
        startActivity(intent);
        finish();
    }

    public void Signup(String name, String email, String phone, String password, String role, String address) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String key = databaseReference.child("Users").push().getKey();
                            User account = new User(name, email, phone, password, role, address);
                            databaseReference.child("Users").child(key).setValue(account, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    if (error == null) {
                                        Toast.makeText(Signup.this, "Account oke", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Signup.this, "Account already exists. Try logging in", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(Signup.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            Log.d("Sugn", task.getException().toString());
                        }
                    }


                });
    }

}