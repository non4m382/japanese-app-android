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
import com.example.japanese_app_android.model.response.GeneralResponse;
import com.example.japanese_app_android.retrofit.RadicalApi;
import com.example.japanese_app_android.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RadicalActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    RadicalAdapter adapter;

    private Spinner spinner;

    RetrofitService retrofitService;

    RadicalApi radicalApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radical_activity);

        recyclerView = findViewById(R.id.rv_radical);
        spinner = findViewById(R.id.category_spinner);
        retrofitService = new RetrofitService(getApplicationContext());
        radicalApi = retrofitService.getRetrofit().create(RadicalApi.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCategoryList();
        setRecycleView();
    }

    private void setSpinner(List<CategoryEntity> categoryEntities) {
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

        Toast.makeText(getApplicationContext(), userData, Toast.LENGTH_SHORT).show();
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

    private void getCategoryList() {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        radicalApi.getAllCategories().enqueue(new Callback<GeneralResponse<List<CategoryEntity>>>() {
            @Override
            public void onResponse(Call<GeneralResponse<List<CategoryEntity>>> call, Response<GeneralResponse<List<CategoryEntity>>> response) {
                if (response.isSuccessful()) {
                    GeneralResponse<List<CategoryEntity>> generalResponse = response.body();
                    categoryEntities.addAll(generalResponse.getData());
                    setSpinner(categoryEntities);
                } else {
                    Log.d("get radical category", "failed");
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse<List<CategoryEntity>>> call, Throwable t) {
                Log.d("login", "failed");
            }
        });

    }


}