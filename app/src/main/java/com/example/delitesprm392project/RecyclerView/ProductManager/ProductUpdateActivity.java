package com.example.delitesprm392project.RecyclerView.ProductManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.delitesprm392project.R;
import com.example.delitesprm392project.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductUpdateActivity extends AppCompatActivity {

    EditText pName;
    EditText pDescription;
    EditText pPrice;
    CheckBox pStocking;
    Button updateBtn;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    public void UpdateData(Product product){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Products").child(String.valueOf(product.getId()-1));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //cập nhật dữ liệu trên firebase theo product
                databaseReference.setValue(product);
                //ve lai trang san pham
                onDataLoaded();
                Log.d("TAG", "Data loaded successfully.");
                // Call a method to update your UI with the new data here
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG", "Failed to load data. Error message: " + error.getMessage());
                // Handle the error here
            }
        });
    }

    private void onDataLoaded() {
        Intent intent = new Intent(this, ProductManagerRecycleView.class);

        // Chuyển sang activity ProductDetail
        this.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_update);

        pName = findViewById(R.id.updateProductName);
        pDescription = findViewById(R.id.updateProductDescription);
        pPrice = findViewById(R.id.updateProductPrice);
        updateBtn = findViewById(R.id.updateProductBtn);
        pStocking = findViewById(R.id.updateProductStocking);

        // Nhận đối tượng Product từ intent
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");

        pName.setText(product.getName());
        pDescription.setText(product.getDescription());
        pPrice.setText(String.valueOf(product.getPrice()));
        pStocking.setChecked(product.isStocking());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product updateProduct = new Product();
                updateProduct.setName(pName.getText().toString());
                updateProduct.setDescription(pDescription.getText().toString());
                updateProduct.setPrice(Double.parseDouble(String.valueOf(pPrice.getText())) );
                updateProduct.setStocking(pStocking.isChecked());
                UpdateData(updateProduct);
            }
        });
    }
}