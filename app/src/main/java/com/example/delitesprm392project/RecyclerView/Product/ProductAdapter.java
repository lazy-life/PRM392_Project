package com.example.delitesprm392project.RecyclerView.Product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.Cart.CartItem;
import com.example.delitesprm392project.DetailProduct;
import com.example.delitesprm392project.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private Context mContext;
    private List<Product> mProducts;

    // linh: hay dung bien nay de add to cart nhe :))
    public static List<CartItem> cartItemList = new ArrayList<>();
    // linh: hay dung bien nay de add to cart nhe :))

    public ProductAdapter(List<Product> mProducts, Context mContext) {
        this.mProducts = mProducts;
        this.mContext = mContext;
    }
    public ProductAdapter() {

    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product product = mProducts.get(position);
        // go to detail
        holder.productItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private void onClickGoToDetail(Product product){
        Log.d("detail", "success");
        Intent intent = new Intent(mContext, DetailProduct.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product",product);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }
}
