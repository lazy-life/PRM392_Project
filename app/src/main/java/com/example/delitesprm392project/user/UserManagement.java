package com.example.delitesprm392project.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.delitesprm392project.R;

import org.w3c.dom.Text;

public class UserManagement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_management);

        ImageView img = findViewById(R.id.userImg);
        img.setBackgroundResource(R.drawable.avatar);
        TextView name = findViewById(R.id.userName);
        TextView email = findViewById(R.id.userEmail);
        name.setText("Pham duc duy");
        email.setText("duy@gmail.com");
        LinearLayout profileBtn = findViewById(R.id.profile_btn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserProfile.class);
                intent.putExtra("product", "product");
                view.getContext().startActivity(intent);

            }
        });

        LinearLayout pwBtn = findViewById(R.id.password_btn);
        pwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserPassword.class);
                intent.putExtra("product", "product");
                view.getContext().startActivity(intent);

            }
        });
    }


}