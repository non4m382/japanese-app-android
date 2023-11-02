package com.example.japanese_app_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanese_app_android.adapter.RadicalAdapter;
import com.example.japanese_app_android.model.CategoryEntity;
import com.example.japanese_app_android.model.RadicalEntity;

import java.util.ArrayList;
import java.util.List;

public class RadicalActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    RadicalAdapter adapter;

    private Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radical_activity);

        recyclerView = findViewById(R.id.rv_radical);
        spinner = findViewById(R.id.category_spinner);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSpinner();
        setRecycleView();
    }

    private void setSpinner() {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(new CategoryEntity(1, "Bộ thủ 1 nét", 1));
        categoryEntities.add(new CategoryEntity(2, "Bộ thủ 2 nét", 1));
        categoryEntities.add(new CategoryEntity(3, "Bộ thủ 3 nét", 1));

        ArrayAdapter<CategoryEntity> categoryEntityArrayAdapter =
                new ArrayAdapter<>(this,
                        com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                        categoryEntities);
        spinner.setAdapter(categoryEntityArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CategoryEntity category = (CategoryEntity) parent.getSelectedItem();
                displayUserData(category);
                Log.d("ok", "checked");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing to do
            }
        });
    }

    private void displayUserData(CategoryEntity category) {
        String name = category.getName();
        int id = category.getId();


        String userData = "Name: " + name + "\nId: " + id;

        Toast.makeText(this, userData, Toast.LENGTH_LONG).show();
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RadicalAdapter(this, getList());
        recyclerView.setAdapter(adapter);
    }

    private List<RadicalEntity> getList() {
        List<RadicalEntity> radicalList = new ArrayList<>();
        radicalList.add(new RadicalEntity(1L, 1, "一", "Nhất", "Số 1", 1));
        radicalList.add(new RadicalEntity(2L, 2, "〡", "Cổn", "nét sổ", 1));
        radicalList.add(new RadicalEntity(1L, 1, "一", "Nhất", "Số 1", 1));
        radicalList.add(new RadicalEntity(2L, 2, "〡", "Cổn", "nét sổ", 1));
        radicalList.add(new RadicalEntity(1L, 1, "一", "Nhất", "Số 1", 1));
        radicalList.add(new RadicalEntity(2L, 2, "〡", "Cổn", "nét sổ", 1));
        radicalList.add(new RadicalEntity(1L, 1, "一", "Nhất", "Số 1", 1));
        radicalList.add(new RadicalEntity(2L, 2, "〡", "Cổn", "nét sổ", 1));
        radicalList.add(new RadicalEntity(1L, 1, "一", "Nhất", "Số 1", 1));
        radicalList.add(new RadicalEntity(2L, 2, "〡", "Cổn", "nét sổ", 1));
        radicalList.add(new RadicalEntity(1L, 1, "一", "Nhất", "Số 1", 1));
        radicalList.add(new RadicalEntity(2L, 2, "〡", "Cổn", "nét sổ", 1));
        radicalList.add(new RadicalEntity(3L, 3, "丶", "Chủ", "điểm,chấm", 1));
        return radicalList;
    }

    public void openHomepage(View view) {
        Intent intent = new Intent(RadicalActivity.this, MainActivity.class);
        startActivity(intent);
    }
}