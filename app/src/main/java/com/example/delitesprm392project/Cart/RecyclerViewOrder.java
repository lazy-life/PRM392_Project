package com.example.delitesprm392project.Cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.delitesprm392project.DetailProduct;
import com.example.delitesprm392project.Home;
import com.example.delitesprm392project.Login;
import com.example.delitesprm392project.R;
import com.example.delitesprm392project.Signup;
import com.example.delitesprm392project.model.Order;
import com.example.delitesprm392project.model.Product;
import com.example.delitesprm392project.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class RecyclerViewOrder extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView total;
    ImageView btnBack;
    ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
    CartOrderAdapter cartOrderAdapter;
    Button btnCheckout;
    EditText etName, etPhone, etAddress;
    Order order;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<CartItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView = findViewById(R.id.viewRecycleCartOrder);
        total = findViewById(R.id.tvTotal);
        btnBack = findViewById(R.id.btnBack);
        btnCheckout = findViewById(R.id.btnCheckout);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);


        // transfer total
        Intent intent = getIntent();
        String total1 = intent.getSerializableExtra("total").toString();
        total.setText(total1);

        // transfer cartList
        Bundle bundle = getIntent().getExtras();
        list = new ArrayList<>();
        if (bundle != null) {
            list = (List<CartItem>) bundle.getSerializable("cartList");
        }

        if (list != null) {
            for (CartItem cartItem : list) {
                cartItems.add(cartItem);
            }
            cartOrderAdapter = new CartOrderAdapter(cartItems, RecyclerViewOrder.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setAdapter(cartOrderAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);
        }

        // button back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerViewOrder.this, RecyclerViewCart.class);
                startActivity(intent);
            }
        });

        // button checkout
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order = new Order();
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String address = etAddress.getText().toString();

                order.setUserId(1);
                order.setOrderId(1);
                order.setShipName(name);
                order.setShipPhone(phone);
                order.setShipAddress(address);
                order.setOrderDate(Calendar.getInstance().getTime());
                for (CartItem c : list) {
                    order.setProductId(c.getProduct().getId());
                    order.setQuantity(c.getQuantity());
                    order.setTotalPrice(c.getTotalPrice());
                    Product pro = new Product();
                    pro = c.getProduct();
                    pro.setQuantity(pro.getQuantity()-c.getQuantity());
                    UpdateData(pro);
                    AddData(order);
                }

                list.clear();

            }
        });


    }

    public void UpdateData(Product product){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Products").child(String.valueOf(product.getId()));
        databaseReference.setValue(product);

    }

    public void AddData(Order product) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Orders")
                .child(String.valueOf(product.getOrderId() - 1));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //cập nhật dữ liệu trên firebase theo product
                databaseReference.setValue(product);
                //ve lai trang san pham
                onDataLoaded();
                Log.d("TAG", "Data loaded successfully.");
                // Call a method to update your UI with the new data here
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG", "Failed to load data. Error message: " + error.getMessage());
                // Handle the error here
            }
        });
    }

    private void onDataLoaded() {
        Toast.makeText(RecyclerViewOrder.this, "Order successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RecyclerViewOrder.this, Home.class);
        this.startActivity(intent);
    }

}
