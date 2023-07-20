package com.example.delitesprm392project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.RecyclerView.Category.CategoryAdapter;
import com.example.delitesprm392project.RecyclerView.Product.ProductAdapter;
import com.example.delitesprm392project.RecyclerView.ProductManager.ProductManagerRecycleView;
import com.example.delitesprm392project.model.Category;
import com.example.delitesprm392project.model.Product;
import com.example.delitesprm392project.user.UserManagement;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    RecyclerView categoryRecyclerView, productRecyclerView;
    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;
    private List<Category> categoryList;
    private List<Product> newestProduct, randomProduct, allProduct;

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
            categoryList.add(new Category(i, "category"));
        }
        categoryAdapter = new CategoryAdapter(categoryList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);

        Log.d("home", String.valueOf(categoryAdapter.getItemCount()));
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