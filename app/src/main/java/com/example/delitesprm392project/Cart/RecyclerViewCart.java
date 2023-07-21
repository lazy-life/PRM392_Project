package com.example.delitesprm392project.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.delitesprm392project.R;
import com.example.delitesprm392project.RecyclerView.ProductHome.ProductAdapter;
import com.example.delitesprm392project.model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewCart extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView totalPrice;
    Button btnCheckout;
    double sum = 0;
    ProductAdapter proAdapter = new ProductAdapter();
    ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
    // linh: fix cung list de hien thi cart
    public static List<CartItem> cartItemList2 = new ArrayList<>();
    Product product1 = new Product(1, "Product 1", 10.0, 1, true, "app/src/main/res/drawable/apple2.jpg", "Product 1 description");
    Product product2 = new Product(2, "Product 2", 20.0, 2, false, "app/src/main/res/drawable/apple2.jpg", "Product 2 description");
    Product product3 = new Product(3, "Product 3", 30.0, 1, true, "app/src/main/res/drawable/apple2.jpg", "Product 3 description");
    Product product4 = new Product(4, "Product 4", 40.0, 2, false, "app/src/main/res/drawable/apple2.jpg", "Product 4 description");
    Product product5 = new Product(5, "Product 5", 50.0, 1, true, "app/src/main/res/drawable/apple2.jpg", "Product 5 description");

    CartItem cartItem1 = new CartItem(product1, 1);
    CartItem cartItem2 = new CartItem(product2, 1);
    CartItem cartItem3 = new CartItem(product3, 1);
    CartItem cartItem4 = new CartItem(product4, 1);
    CartItem cartItem5 = new CartItem(product5, 1);

    // linh: fix cung list de hien thi cart
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_cart);

        recyclerView = findViewById(R.id.viewRecycleCartList);
        totalPrice = findViewById(R.id.tvTotal);
        btnCheckout = findViewById(R.id.btnCheckout);

        cartItemList2.add(cartItem1);
        cartItemList2.add(cartItem2);
        cartItemList2.add(cartItem3);
        cartItemList2.add(cartItem4);
        cartItemList2.add(cartItem5);
//        if(proAdapter.cartItemList != null){
//            for(CartItem cartItem:proAdapter.cartItemList){
//                cartItems.add(cartItem);
//            }
//            cartAdapter = new CartAdapter(cartItems, RecyclerViewCart.this);
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//            recyclerView.setAdapter(cartAdapter);
//            recyclerView.setLayoutManager(linearLayoutManager);
//        }
        if (cartItemList2 != null) {
            for (CartItem cartItem : cartItemList2) {
                cartItems.add(cartItem);
                sum += cartItem.getTotalPrice();
            }
            cartAdapter = new CartAdapter(cartItems, RecyclerViewCart.this, totalPrice);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setAdapter(cartAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);
            totalPrice.setText(Double.toString(sum));
        }

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerViewCart.this, RecyclerViewOrder.class);
                intent.putExtra("total", totalPrice.getText());
                Bundle bundle = new Bundle();
                bundle.putSerializable("cartList", (Serializable) cartItems);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}