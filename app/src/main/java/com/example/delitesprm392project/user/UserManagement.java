package com.example.delitesprm392project.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.delitesprm392project.Home;
import com.example.delitesprm392project.Login;
import com.example.delitesprm392project.R;
import com.example.delitesprm392project.RecyclerView.ProductManager.ProductManagerRecycleView;
import com.example.delitesprm392project.model.User;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserManagement extends AppCompatActivity {


    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_management);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        ImageView img = findViewById(R.id.userImg);
        img.setBackgroundResource(R.drawable.avatar);
        TextView name = findViewById(R.id.userName);
        TextView email = findViewById(R.id.userEmail);


        if (user == null) {
            Toast.makeText(this, "hehe", Toast.LENGTH_SHORT).show();
        } else {

            databaseReference.child("Users").orderByChild("email").equalTo(user.getEmail()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        User account = dataSnapshot.getValue(User.class);
                        name.setText(account.getName());
                        email.setText(account.getEmail());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        findViewById(R.id.manageBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Home.class);
                view.getContext().startActivity(intent);
            }
        });

        LinearLayout profileBtn = findViewById(R.id.profile_btn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserProfile.class);
                view.getContext().startActivity(intent);

            }
        });

        LinearLayout pwBtn = findViewById(R.id.password_btn);
        pwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserPassword.class);
                view.getContext().startActivity(intent);

            }
        });


        LinearLayout logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(getApplicationContext()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(view.getContext(), Login.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menuProductList){
            Intent intent = new Intent(this, Home.class);

            // Chuyển sang activity add product
            this.startActivity(intent);
            return true;
        }
        if (item.getItemId()==R.id.menuProductManager){
            Intent intent = new Intent(this, ProductManagerRecycleView.class);

            // Chuyển sang activity add product
            this.startActivity(intent);
            return true;
        }
        if (item.getItemId()==R.id.menuUserManager){
            Intent intent = new Intent(this, UserManagement.class);

            // Chuyển sang activity add product
            this.startActivity(intent);
            return true;
        }
        if (item.getItemId()==R.id.menuLogout){
            Intent intent = new Intent(this, Home.class);

            // Chuyển sang activity add product
            this.startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}