package com.example.delitesprm392project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.delitesprm392project.RecyclerView.ProductHome.ProductAdapter;
import com.example.delitesprm392project.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryProductActivity extends AppCompatActivity {
    RecyclerView categoryProductRecyclerView;
    ProductAdapter productAdapter;
    List<Product> productList;
    DatabaseReference productRef;
    EditText search;
    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);
        Intent intent =getIntent();
        int id = intent.getIntExtra("cateid",1);
        categoryProductRecyclerView = findViewById(R.id.category_product_Recycle_View);
        LoadDataProductCate(id);

    }
    public void LoadDataProductCate(int cateid){
        productList = new ArrayList<>();
        productRef =  FirebaseDatabase.getInstance().getReference("Products");
        productRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    if(product.getCategoryId()==cateid){
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
            productAdapter = new ProductAdapter(productList,CategoryProductActivity.this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(CategoryProductActivity.this,3);
            categoryProductRecyclerView.setAdapter(productAdapter);
            categoryProductRecyclerView.setLayoutManager(gridLayoutManager);
        }
    }

}