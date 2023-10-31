package com.example.japanese_app_android.model.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.japanese_app_android.model.CategoryEntity;

import java.util.List;

public class CategorySpinnerAdapter extends ArrayAdapter<CategoryEntity> {

    private List<CategoryEntity> categoryEntityList;

    public CategorySpinnerAdapter(@NonNull Context context, int resource, List<CategoryEntity> categoryEntityList) {
        super(context, resource);
        this.categoryEntityList = categoryEntityList;
    }

    @Override
    public int getCount() {
        return categoryEntityList.size();
    }

    @Nullable
    @Override
    public CategoryEntity getItem(int position) {
        return categoryEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.WHITE);
        label.setText(categoryEntityList.get(position).getName());

        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.WHITE);
        label.setText(categoryEntityList.get(position).getName());
        return label;
    }

    @NonNull
    @Override
    public Context getContext() {
        return super.getContext();
    }
}
