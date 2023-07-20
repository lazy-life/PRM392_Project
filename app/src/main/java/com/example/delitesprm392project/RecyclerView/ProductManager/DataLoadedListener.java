package com.example.delitesprm392project.RecyclerView.ProductManager;

import com.example.delitesprm392project.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface DataLoadedListener {
    void onDataLoaded(List<Product> products);
}
