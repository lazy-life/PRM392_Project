package com.example.delitesprm392project.RecyclerView.ProductHome;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.Cart.CartItem;
import com.example.delitesprm392project.DetailProduct;
import com.example.delitesprm392project.R;
import com.example.delitesprm392project.RecyclerView.Category.CategoryViewHolder;
import com.example.delitesprm392project.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private List<Product> productList;
    private  Context mContext;
    // linh: fix cung list de hien thi cart
    public static List<CartItem> cartItemList = new ArrayList<>();
    // linh: fix cung list de hien thi cart
    public ProductAdapter(List<Product> productList, Context mContext){
        this.productList = productList;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View productView  = inflater.inflate(R.layout.home_product_item,parent,false);
        ProductViewHolder productViewHolder = new ProductViewHolder(productView);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getImage()).into(holder.productImage);
        holder.productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(mContext, DetailProduct.class);
                intent.putExtra("object_product",product);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
