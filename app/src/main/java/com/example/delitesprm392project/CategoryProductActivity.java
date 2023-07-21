package com.example.delitesprm392project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.delitesprm392project.RecyclerView.Category.CategoryAdapter;
import com.example.delitesprm392project.RecyclerView.ProductHome.ProductAdapter;
import com.example.delitesprm392project.model.Category;
import com.example.delitesprm392project.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryProductActivity extends AppCompatActivity {
    RecyclerView categoryProductRecyclerView,categoryRecyclerView;
    ProductAdapter productAdapter;
    CategoryAdapter categoryAdapter;
    List<Product> productList;
    DatabaseReference productRef,cateRef;
    EditText search;
    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);
        Intent intent =getIntent();
        int id = intent.getIntExtra("cateid",0);
        String s = intent.getStringExtra("search");
        search = findViewById(R.id.category_search);
        search.setText(s);
        btnSearch = findViewById(R.id.btn_category_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s  = search.getText().toString();
                categoryProductRecyclerView = findViewById(R.id.category_product_Recycle_View);
                categoryRecyclerView = findViewById(R.id.category_CategoryRecycleView);
                Log.d("sada",String.valueOf(LoadDataCategory()));
                LoadDataProductCate(id,s);
            }
        });
        categoryProductRecyclerView = findViewById(R.id.category_product_Recycle_View);
        categoryRecyclerView = findViewById(R.id.category_CategoryRecycleView);
        Log.d("sada",String.valueOf(LoadDataCategory()));
        LoadDataProductCate(id,null);



    }
    public void LoadDataProductCate(int cateid,String search){
        productList = new ArrayList<>();
        productRef =  FirebaseDatabase.getInstance().getReference("Products");
        productRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(search==null){
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Product product = dataSnapshot.getValue(Product.class);
                        if(product.getCategoryId()==cateid){
                            productList.add(product);
                        }

                    }
                }else if(cateid==0){
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Product product = dataSnapshot.getValue(Product.class);
                        if(product.getName().toLowerCase().contains(search.toLowerCase())){
                            productList.add(product);
                        }
                    }
                }
                else{
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Product product = dataSnapshot.getValue(Product.class);
                        if(product.getCategoryId()==cateid&&product.getName().toLowerCase().contains(search.toLowerCase())){
                            productList.add(product);
                        }
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
    private boolean onCategoryLoad(List<Category> cate){
        if(!cate.isEmpty()){
            categoryAdapter = new CategoryAdapter(cate,CategoryProductActivity.this,CategoryProductActivity.class);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CategoryProductActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            categoryRecyclerView.setAdapter(categoryAdapter);
            categoryRecyclerView.setLayoutManager(linearLayoutManager);
            return true;
        }
        return false;
    }

}