package com.example.delitesprm392project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.delitesprm392project.RecyclerView.ProductHome.ProductAdapter;
import com.example.delitesprm392project.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerView searchProductRecyclerView;
    ProductAdapter productAdapter;
    List<Product> productList;
    DatabaseReference productRef;
    EditText search;
    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent =getIntent();
        String s = intent.getStringExtra("search");
        search = findViewById(R.id.search_search);
        btnSearch = findViewById(R.id.btn_search_search);
        search.setText(s);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = search.getText().toString();

            }
        });
        searchProductRecyclerView = findViewById(R.id.search_product_Recycle_View);
        Log.d("aaa",String.valueOf(productList));
        LoadDataProductSearch(s);


    }
    public void LoadDataProductSearch(String search){
        productList = new ArrayList<>();
        productRef =  FirebaseDatabase.getInstance().getReference("Products");
        productRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    if(product.getName().toLowerCase().contains(search.toLowerCase())){
                        productList.add(product);
                    }

                }
                onProductLoad();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void onProductLoad(){
        if(!productList.isEmpty()){
            Log.d("aaa",String.valueOf(productList));
            productAdapter = new ProductAdapter(productList,SearchActivity.this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchActivity.this,3);
            searchProductRecyclerView.setAdapter(productAdapter);
            searchProductRecyclerView.setLayoutManager(gridLayoutManager);
        }
    }

}