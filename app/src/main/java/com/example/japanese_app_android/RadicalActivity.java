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

    List<CategoryEntity> categoryEntities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radical_activity);

        recyclerView = findViewById(R.id.rv_radical);
        spinner = findViewById(R.id.category_spinner);
        retrofitService = new RetrofitService(getApplicationContext());
        radicalApi = retrofitService.getRetrofit().create(RadicalApi.class);

        getCategoryList();
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
                Log.d("ok", category.getId().toString());
                getRadicalByCategory(category.getId());
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

    private void setRecycleView(List<RadicalEntity> radicalEntities) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RadicalAdapter(this, radicalEntities);
        recyclerView.setAdapter(adapter);
    }

    public void openHomepage(View view) {
        Intent intent = new Intent(RadicalActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void getCategoryList() {
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

    private void getRadicalByCategory(Integer id) {
        List<RadicalEntity> radicalEntities = new ArrayList<>();
        radicalApi.getRadicalByCategory(id).enqueue(new Callback<GeneralResponse<List<RadicalEntity>>>() {
            @Override
            public void onResponse(Call<GeneralResponse<List<RadicalEntity>>> call, Response<GeneralResponse<List<RadicalEntity>>> response) {
                if (response.isSuccessful()) {
                    GeneralResponse<List<RadicalEntity>> generalResponse = response.body();
                    radicalEntities.addAll(generalResponse.getData());
                    setRecycleView(radicalEntities);
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse<List<RadicalEntity>>> call, Throwable t) {
                // Nothing to do yet
            }
        });
    }


}