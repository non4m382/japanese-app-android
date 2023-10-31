package com.example.japanese_app_android.model.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    @NonNull
    @Override
    public CategoryAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class CategoryHolder extends RecyclerView.ViewHolder {


        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
