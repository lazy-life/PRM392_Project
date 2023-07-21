package com.example.delitesprm392project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.Cart.RecyclerViewCart;
import com.example.delitesprm392project.RecyclerView.Category.CategoryAdapter;
import com.example.delitesprm392project.RecyclerView.ProductHome.ProductAdapter;
import com.example.delitesprm392project.RecyclerView.ProductManager.ProductManagerRecycleView;
import com.example.delitesprm392project.model.Category;
import com.example.delitesprm392project.model.Product;
import com.example.delitesprm392project.user.UserManagement;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Home extends AppCompatActivity {

    RecyclerView categoryRecyclerView, newestproductRecyclerView,randomproductRecyclerView,allProductRecycleView;
    CategoryAdapter categoryAdapter;
    ProductAdapter allproductAdapter,newestproductAdapter,ranProductAdapter;
    private List<Category> categoryList;
    private List<Product> newestProduct, randomProduct, allProduct;
    DatabaseReference productRef,cateRef ;
    EditText search;
    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        search = findViewById(R.id.home_search);
        btnSearch = findViewById(R.id.btn_home_search);

        Button btnProfile = findViewById(R.id.btnProfile);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserManagement.class);
                view.getContext().startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = search.getText().toString();
                Intent intent =new Intent(Home.this, SearchActivity.class);
                intent.putExtra("search",s);
                startActivity(intent);
            }
        });

        categoryRecyclerView = findViewById(R.id.HomeCategoryRecycleView);
        newestproductRecyclerView = findViewById(R.id.home_newestproduct_RecycleView);
        randomproductRecyclerView = findViewById(R.id.home_RandomProduct_RecycleView);
        allProductRecycleView = findViewById(R.id.all_products_recyclerview);
        Log.d("sada",String.valueOf(LoadDataCategory()));
        Log.d("sada",String.valueOf(LoadDataProduct()));


    }
    private boolean LoadDataCategory(){
            List<Category> list = new ArrayList<>();
            cateRef =  FirebaseDatabase.getInstance().getReference("Categorys");
            cateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Category category = snapshot.getValue(Category.class);

                        list.add(category);

                    }
                    if(onCategoryLoad(list)){
                        Log.d("sddd",String.valueOf(list));

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            if(list.isEmpty()){
                return false;
            }else{
                return true;
            }

    }
    private boolean LoadDataProduct(){
        allProduct  = new ArrayList<>();
        randomProduct = new ArrayList<>();
        productRef =  FirebaseDatabase.getInstance().getReference("Products");
        productRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                   Product p = snapshot.getValue(Product.class);
                    allProduct.add(p);

                }
                newestProduct = allProduct.subList(allProduct.size() - 3, allProduct.size());
                Random random = new Random();
                for(int i = 0;i<4;i++){
                    int index = random.nextInt(allProduct.size());
                    Product p  = allProduct.get(index);
                    if(!randomProduct.contains(p)){
                        randomProduct.add(p);
                    }
                }
                if(onProductLoad()){
                    Log.d("all",String.valueOf(allProduct));
                    Log.d("new",String.valueOf(newestProduct));
                    Log.d("ran",String.valueOf(randomProduct));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if(allProduct.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    private boolean onCategoryLoad(List<Category> cate){
        if(!cate.isEmpty()){
            categoryAdapter = new CategoryAdapter(cate,Home.this, CategoryProductActivity.class);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            categoryRecyclerView.setAdapter(categoryAdapter);
            categoryRecyclerView.setLayoutManager(linearLayoutManager);
            return true;
        }
        return false;
    }
    private boolean onProductLoad(){
        if(!(allProduct.isEmpty()&&newestProduct.isEmpty()&&randomProduct.isEmpty())){
            newestproductAdapter = new ProductAdapter(newestProduct,Home.this);
            ranProductAdapter = new ProductAdapter(randomProduct,Home.this);
            allproductAdapter = new ProductAdapter(allProduct,Home.this);
            LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(Home.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            LinearLayoutManager linearLayoutManager1  = new LinearLayoutManager(Home.this);
            linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(Home.this,3);
            newestproductRecyclerView.setAdapter(newestproductAdapter);
            newestproductRecyclerView.setLayoutManager(linearLayoutManager);
            randomproductRecyclerView.setAdapter(ranProductAdapter);
            randomproductRecyclerView.setLayoutManager(linearLayoutManager1);
            allProductRecycleView.setAdapter(allproductAdapter);
            allProductRecycleView.setLayoutManager(gridLayoutManager);

            return true;
        }
        return false;
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
            Intent intent = new Intent(this, Login.class);
            AuthUI.getInstance().signOut(getApplicationContext()).addOnCompleteListener(new OnCompleteListener<Void>() {
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
                }
            });
            this.startActivity(intent);
            return true;
        }
        if (item.getItemId()==R.id.menuCart){
            Intent intent = new Intent(this, RecyclerViewCart.class);

            // Chuyển sang activity add product
            this.startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}