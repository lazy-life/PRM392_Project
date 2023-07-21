package com.example.delitesprm392project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.delitesprm392project.Cart.Cart;
import com.example.delitesprm392project.Cart.CartItem;
import com.example.delitesprm392project.model.Product;

public class DetailProduct extends AppCompatActivity {

    ImageView imageView, imgHome,imgAddcart;
    TextView name, price, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        imageView = findViewById(R.id.imgProduct);
        name = findViewById(R.id.tvProductName);
        price = findViewById(R.id.tvPrice);
        description = findViewById(R.id.tvDescription);
        imgHome = findViewById(R.id.imgHome);
        imgAddcart = findViewById(R.id.imgAddCart);
        // detail
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Product product = (Product) bundle.get("object_product");
        //image
        Drawable drawable = getResources().getDrawable(R.drawable.nahida);
        imageView.setImageDrawable(drawable);
        // name price des
        name.setText(product.getName());
        price.setText(Double.toString(product.getPrice()));
        description.setText(product.getDescription());

        // return home
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
        imgAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = Cart.getInstance();
                if(cart.getCartList().isEmpty()){
                    CartItem c = new CartItem(product,1);
                    cart.addCartItem(c);
                }else{
                    for (CartItem c: cart.getCartList()){
                        if(c.getProduct().getId()==product.getId()){
                            c.setQuantity(c.getQuantity()+1);
                        }else{
                            cart.addCartItem(new CartItem(product,1));
                        }
                    }
                }
            }
        });
    }
}