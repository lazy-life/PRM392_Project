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
import android.widget.NumberPicker;

import com.example.delitesprm392project.R;
import com.example.delitesprm392project.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductAddActivity extends AppCompatActivity {

    EditText pName;
    EditText pDescription;
    EditText pPrice;
    NumberPicker pCategory;
    CheckBox pStocking;
    Button addBtn;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    public void AddData(Product product){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Products")
                .child(String.valueOf(product.getId()-1));

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
        setContentView(R.layout.activity_product_add);

        pName = findViewById(R.id.addProductName);
        pDescription = findViewById(R.id.addProductDescription);
        pPrice = findViewById(R.id.addProductPrice);
        pCategory = findViewById(R.id.addProductCategory);
        addBtn = findViewById(R.id.addProductBtn);
        pStocking = findViewById(R.id.addProductStocking);

        Intent intent = getIntent();
        int productId = intent.getIntExtra("productId", 100);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = new Product(productId, pName.getText().toString()
                        , Double.parseDouble(pPrice.getText().toString()), pCategory.getValue()
                        , pStocking.isChecked(), "", pDescription.getText().toString());

                AddData(product);
            }
        });

    }
}