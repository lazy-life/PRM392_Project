package com.example.delitesprm392project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.delitesprm392project.model.Product;

public class DetailProduct extends AppCompatActivity {

    ImageView imageView, imgHome;
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
    }
}