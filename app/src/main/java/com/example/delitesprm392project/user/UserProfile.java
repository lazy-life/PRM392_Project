package com.example.delitesprm392project.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.delitesprm392project.R;

import org.w3c.dom.Text;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        TextView name = findViewById(R.id.tvProfileName);
        TextView email = findViewById(R.id.tvProfileEmail);
        name.setText("Pham duc duy");
        email.setText("duy@gmail.com");

        EditText etName = findViewById(R.id.editextUserName);
        etName.setText("Pham duc duy");
        EditText etEmail = findViewById(R.id.editextUserEmail);
        etEmail.setText("duy@gmail.com");
        EditText etPhone = findViewById(R.id.editextUserPhone);
        etPhone.setText("0123456798");
        EditText etAddress= findViewById(R.id.editextUserAddress);
        etAddress.setText("ha noi");

        Button btn = findViewById(R.id.userProfileUpdateBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();
                String add = etAddress.getText().toString();
                Toast.makeText(UserProfile.this, name + ' ' + email, Toast.LENGTH_SHORT).show();
            }
        });
    }
}