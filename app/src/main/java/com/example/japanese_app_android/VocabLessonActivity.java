package com.example.japanese_app_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanese_app_android.adapter.VocabLessonAdapter;
import com.example.japanese_app_android.model.LessonEntity;
import com.example.japanese_app_android.model.response.GeneralResponse;
import com.example.japanese_app_android.retrofit.LessonApi;
import com.example.japanese_app_android.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VocabLessonActivity extends AppCompatActivity {

    RetrofitService retrofitService;

    LessonApi lessonApi;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuvung_lesson_activity); // Gắn layout XML vào Activity
        retrofitService = new RetrofitService(getApplicationContext());
        lessonApi = retrofitService.getRetrofit().create(LessonApi.class);

        recyclerView = findViewById(R.id.recyclerView); // Khởi tạo RecyclerView

        // TODO chuyền bookId khi bấm chọn sách từ main homepage
        Integer bookId = 1;
        getRadicalByCategory(bookId);

    }

    private void getRadicalByCategory(Integer id) {
        List<LessonEntity> lessonEntities = new ArrayList<>();
        lessonApi.getLessonByBookId(id).enqueue(new Callback<GeneralResponse<List<LessonEntity>>>() {
            @Override
            public void onResponse(Call<GeneralResponse<List<LessonEntity>>> call, Response<GeneralResponse<List<LessonEntity>>> response) {
                if (response.isSuccessful()) {
                    GeneralResponse<List<LessonEntity>> generalResponse = response.body();
                    lessonEntities.addAll(generalResponse.getData());

                    VocabLessonAdapter adapter = new VocabLessonAdapter(getApplicationContext(), lessonEntities);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3)); // Số cột trong lưới (ở đây là 2)
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse<List<LessonEntity>>> call, Throwable t) {
                // Nothing to do yet
            }
        });
    }

    public void backToTerm(View view) {
        Intent intent = new Intent(VocabLessonActivity.this, TermActivity.class);
        startActivity(intent);
    }

    public void openVocabList(View view) {

        // Truyền thông tin bài học vào Activity mới
        Intent intent = new Intent(VocabLessonActivity.this, VocabActivity.class);
        intent.putExtra("lessonId", 1);
        startActivity(intent);
    }

}
