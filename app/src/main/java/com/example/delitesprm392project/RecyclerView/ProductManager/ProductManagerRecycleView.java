package com.example.delitesprm392project.RecyclerView.ProductManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.delitesprm392project.R;
import com.example.delitesprm392project.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductManagerRecycleView extends AppCompatActivity implements DataLoadedListener {

    RecyclerView recyclerView;
    List<Product> productRecycles;
    ProductManagerAdapter productAdapter;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    Button addProduct;
    EditText searchTxt;
    Button searchBtn;

    public void AddList() {
        productRecycles = new ArrayList<>();
        productRecycles.add(new Product(1, "pro 1", 100, 1, true, "draw 1", "ngu"));
        productRecycles.add(new Product(2, "pro 2", 200, 1, true, "draw 2", "ngu"));
        productRecycles.add(new Product(3, "pro 3", 300, 1, true, "draw 3", "ngu"));
        productRecycles.add(new Product(4, "pro 4", 400, 1, true, "draw 4", "ngu"));
    }

    public void LoadData(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Products");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productRecycles = new ArrayList<>();
                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    Product model = data.getValue(Product.class);
                    productRecycles.add(model);
                }
                onDataLoaded(productRecycles);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manager_recycle_view);

        recyclerView = findViewById(R.id.viewRecycleProductManagerList);
        addProduct = findViewById(R.id.viewRecycleProductManagerAdd);
        searchTxt = findViewById(R.id.viewRecycleProductManagerSearch);
        searchBtn = findViewById(R.id.viewRecycleProductManagerSearchBtn);

        LoadData();

//        productAdapter = new ProductManagerAdapter(productRecycles, ProductManagerRecycleView.this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setAdapter(productAdapter);
//        recyclerView.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void onDataLoaded(List<Product> products) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        productAdapter = new ProductManagerAdapter(products, ProductManagerRecycleView.this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}