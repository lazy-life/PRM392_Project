package com.example.delitesprm392project.RecyclerView.Category;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.CategoryProductActivity;
import com.example.delitesprm392project.R;
import com.example.delitesprm392project.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private  List<Category> categorylist;
    private Context mContext;
    private  Class mClass;

    public CategoryAdapter(List<Category> categorylist,Context mContext,Class mClass) {
        this.categorylist = categorylist;
        this.mContext = mContext;
        this.mClass = mClass;

    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.category_button, parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(categoryView);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categorylist.get(position);
        holder.button.setText(category.getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(mContext, mClass);
                intent.putExtra("cateid",category.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categorylist.size();
    }
}
