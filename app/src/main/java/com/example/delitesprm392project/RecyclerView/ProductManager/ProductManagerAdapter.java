package com.example.delitesprm392project.RecyclerView.ProductManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.R;
import com.example.delitesprm392project.model.Product;

import java.util.List;

public class ProductManagerAdapter extends RecyclerView.Adapter<ProductManagerHolder>{
    public List<Product> mProducts;
    private Context mContext;

    public ProductManagerAdapter(List<Product> mProducts, Context mContext) {
        this.mProducts = mProducts;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ProductManagerHolder onCreateViewHolder(@NonNull ViewGroup parent, @SuppressLint("RecyclerView")  int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View productView = inflater.inflate(R.layout.product_item_manager,parent,false);
        ProductManagerHolder viewHolder = new ProductManagerHolder(productView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductManagerHolder holder, int position) {
        Product product = mProducts.get(position);
        holder.productName.setText(product.getProductname());
        holder.category.setText(String.valueOf(product.getCategoryid()));
        holder.price.setText(String.valueOf(product.getProductprice()));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
