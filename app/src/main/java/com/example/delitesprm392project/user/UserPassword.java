package com.example.delitesprm392project.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.delitesprm392project.R;

public class UserPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_password);

        TextView name = findViewById(R.id.pwName);
        TextView email = findViewById(R.id.pwEmail);
        name.setText("Pham duc duy");
        email.setText("duy@gmail.com");

        EditText oldPW = findViewById(R.id.oldPassword);
        EditText newPW = findViewById(R.id.newPassword);
        EditText renewPW = findViewById(R.id.renewPassword);
        Button btn = findViewById(R.id.pwBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPWs = oldPW.getText().toString();
                String newPWs = newPW.getText().toString();
                String renewPWs = renewPW.getText().toString();

            }
        });
    }
}