package com.example.japanese_app_android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanese_app_android.adapter.AlphabetAdapter;
import com.example.japanese_app_android.model.AlphabetEntity;
import com.example.japanese_app_android.model.response.GeneralResponse;
import com.example.japanese_app_android.retrofit.AlphabetApi;
import com.example.japanese_app_android.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlphabetActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Spinner spinner;

    ImageButton btnback;

    RetrofitService retrofitService;

    AlphabetApi alphabetApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alphabet);
        retrofitService = new RetrofitService(getApplicationContext());
        alphabetApi = retrofitService.getRetrofit().create(AlphabetApi.class);

        btnback = findViewById(R.id.btn_back);

        btnback.setOnClickListener(view -> {
            //Thoát AlphabetDetailActivity
            finish();
        });

        recyclerView = findViewById(R.id.rc_alphabet);

        spinner = findViewById(R.id.alphabet_spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = (String) parent.getSelectedItem();
                Log.d("ok", select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing to do
            }
        });

        getAllAlphabet();
    }

    private void getAllAlphabet() {
        ArrayList<AlphabetEntity> alphabetEntities = new ArrayList<>();
        alphabetApi.getAllLesson().enqueue(new Callback<GeneralResponse<List<AlphabetEntity>>>() {
            @Override
            public void onResponse(Call<GeneralResponse<List<AlphabetEntity>>> call, Response<GeneralResponse<List<AlphabetEntity>>> response) {
                if (response.isSuccessful()) {
                    GeneralResponse<List<AlphabetEntity>> generalResponse = response.body();
                    alphabetEntities.addAll(generalResponse.getData());

                    AlphabetAdapter adapter = new AlphabetAdapter(alphabetEntities, getApplicationContext());

                    GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 5);

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse<List<AlphabetEntity>>> call, Throwable t) {
                // Nothing to do yet
            }
        });
    }
}