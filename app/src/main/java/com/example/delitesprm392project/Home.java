package com.example.delitesprm392project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.delitesprm392project.user.UserManagement;
import com.example.delitesprm392project.user.UserProfile;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.RecyclerView.Category.CategoryAdapter;
import com.example.delitesprm392project.RecyclerView.Product.ProductAdapter;
import com.example.delitesprm392project.model.Category;
import com.example.delitesprm392project.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private List<Category> categoryList;
    private List<Product> newestProduct,randomProduct,allProduct;
    RecyclerView  categoryRecyclerView,productRecyclerView;
    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnProfile = findViewById(R.id.btnProfile);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserManagement.class);
                view.getContext().startActivity(intent);
            }
        });
        categoryRecyclerView = findViewById(R.id.HomeCategoryRecycleView);
        categoryList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            categoryList.add(new Category(i,"category"));
        }
        categoryAdapter = new CategoryAdapter(categoryList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);

        Log.d("home",String.valueOf(categoryAdapter.getItemCount()));
    }
}