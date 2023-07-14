package com.example.delitesprm392project.RecyclerView.Category;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.R;
import com.example.delitesprm392project.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private List<Category> categorylist;


    public CategoryAdapter(List<Category> categorylist) {
        this.categorylist = categorylist;

    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.category_button, parent, false);
        Log.d("adapter1","sa");
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(categoryView);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categorylist.get(position);
        holder.button.setText(category.getCategoryName());
        Log.d("adapter2","sa");
    }

    @Override
    public int getItemCount() {
        return categorylist.size();
    }
}
