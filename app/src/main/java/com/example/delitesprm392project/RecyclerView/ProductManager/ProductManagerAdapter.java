package com.example.delitesprm392project.RecyclerView.ProductManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
    public ProductManagerHolder onCreateViewHolder(@NonNull ViewGroup parent,  int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View productView = inflater.inflate(R.layout.product_item_manager,parent,false);
        ProductManagerHolder viewHolder = new ProductManagerHolder(productView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductManagerHolder holder,@SuppressLint("RecyclerView")  int position) {
        Product product = mProducts.get(position);
        holder.productName.setText(product.getName());
        holder.category.setText(String.valueOf(product.getCategoryId()));
        holder.price.setText(String.valueOf(product.getPrice()));

        holder.update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = mProducts.get(position);


                // Tạo intent để chuyển sang activity ProductDetail
                Intent intent = new Intent(v.getContext(), ProductUpdateActivity.class);

                // Gắn đối tượng Product vào intent
                intent.putExtra("product", product);

                // Chuyển sang activity ProductDetail
                v.getContext().startActivity(intent);
            }
        });

        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDeteleActivity.class);

                // Gắn đối tượng Product vào intent
                intent.putExtra("product", product);

                // Chuyển sang activity ProductDetail
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
