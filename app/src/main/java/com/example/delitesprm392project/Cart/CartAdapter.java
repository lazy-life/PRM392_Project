package com.example.delitesprm392project.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.R;
import com.example.delitesprm392project.RecyclerView.Product.ProductAdapter;
import com.example.delitesprm392project.model.Product;
import com.squareup.picasso.Picasso;
import com.yonder.basketlayout.BasketLayoutViewListener;
import com.yonder.basketlayout.State;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<CartItem> cartList;
    private Context mContext;

    public RecyclerViewCart r;

    // test cart
    public CartAdapter() {

    }
    // test cart

    public CartAdapter(List<CartItem> cartList, Context mContext) {
        this.cartList = cartList;
        this.mContext = mContext;
    }

    ProductAdapter productAdapter = new ProductAdapter();

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cartView = inflater.inflate(R.layout.activity_cart, parent, false);
        CartViewHolder viewHolder = new CartViewHolder(cartView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        //        CartItem cart = productAdapter.cartItemList.get(position);
        CartItem cart = cartList.get(position);
        if (cart != null) {
            Picasso.get().load(cart.getProduct().getImagePath()).into(holder.itemImg);
            holder.itemName.setText(cart.getProduct().getProductname());
            holder.itemPrice.setText(String.valueOf(cart.getProduct().getProductprice()));
        }

        holder.basketLayoutView.setMaxQuantity(10);
        // set-up basketLayoutView
        holder.basketLayoutView.setBasketLayoutListener(new BasketLayoutViewListener() {
            @Override
            public void onClickDecreaseQuantity(int i) {
                 i = cart.getQuantity();
                holder.basketLayoutView.setBasketQuantity(i-1, State.None);
            }

            @Override
            public void onClickIncreaseQuantity(int i) {
                i = cart.getQuantity();
                holder.basketLayoutView.setBasketQuantity(i+1, State.None);
            }

            @Override
            public void onClickTrash() {
            // delete cart

            }

            @Override
            public void onExceedMaxQuantity(int i) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
}
