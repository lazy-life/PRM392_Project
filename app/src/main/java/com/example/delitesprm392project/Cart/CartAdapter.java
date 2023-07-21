package com.example.delitesprm392project.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView tvTotal;

    // test cart
    public CartAdapter() {

    }
    // test cart

    public CartAdapter(List<CartItem> cartList, Context mContext, TextView tvTotal) {
        this.cartList = cartList;
        this.mContext = mContext;
        this.tvTotal = tvTotal;
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
            Picasso.get().load(cart.getProduct().getImage()).into(holder.itemImg);
            holder.itemName.setText(cart.getProduct().getName());
            holder.itemPrice.setText(String.valueOf(cart.getProduct().getPrice()));
            holder.itemQuantity.setText(String.valueOf(cart.getQuantity()));
        }

        // button decrease
        holder.btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = cart.getQuantity();
                if (qty > 1) {
                    qty--;
                    cart.setQuantity(qty);
                    notifyDataSetChanged();
                    updateprice();
                }
                if (qty == 1) {
//                    holder.btnDec.setActivated(false);
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Lấy sản phẩm tương ứng với vị trí được click
                        CartItem c = cartList.get(position);
                        cartList.remove(c);
                        updateprice();
                        notifyDataSetChanged();
                    }
                }
            }
        });

        holder.btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = cart.getQuantity();
                qty++;
                cart.setQuantity(qty);
                notifyDataSetChanged();
                updateprice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public void updateprice() {
        double sum = 0;
        int i;
        for (i = 0; i < cartList.size(); i++)
            sum = sum + (cartList.get(i).getTotalPrice());

        tvTotal.setText(Double.toString(sum));
    }
}
