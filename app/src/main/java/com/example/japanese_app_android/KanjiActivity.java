package com.example.japanese_app_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanese_app_android.adapter.KanjiAdapter;
import com.example.japanese_app_android.model.KanjiEntity;
import com.example.japanese_app_android.model.LessonEntity;

import java.util.ArrayList;
import java.util.List;

public class KanjiActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    KanjiAdapter adapter;
    String lessonId;
    LessonEntity kanjiLesson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kanji_list_activity);
        lessonId = getIntent().getStringExtra("lessonId");
        kanjiLesson = findKanjiLessonById(lessonId);
        TextView textViewTitle = findViewById(R.id.textViewTitle);
        textViewTitle.setText(kanjiLesson.getName() + ": " + kanjiLesson.getContent());
        recyclerView = findViewById(R.id.rv_kanji);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRecycleView();
    }


    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new KanjiAdapter(this, getList());
        recyclerView.setAdapter(adapter);
    }

    private List<KanjiEntity> getList() {
        List<KanjiEntity> radicalList = new ArrayList<>();
        radicalList.add(new KanjiEntity(1, 1, "Tư", "私", "cá nhân", "わたし", "シ"));
        radicalList.add(new KanjiEntity(2, 1, "Tuế", "歳", "tuổi", "とし", "セイ"));
        radicalList.add(new KanjiEntity(3, 1, "QUỐC", "国", "đất nước", "くに", "コク"));
        radicalList.add(new KanjiEntity(4, 1, "NHÂN", "人", "Người", "ひと", "ジン"));
        radicalList.add(new KanjiEntity(6, 1, "NHẬT", "日", "Mặt trời", "ひ", "ニチニチ"));
        radicalList.add(new KanjiEntity(7, 1, "NGUYỆT", "月", "Mặt trăng", "つき", "ゲツ"));
        radicalList.add(new KanjiEntity(8, 1, "TỨ", "四", "Bốn", "よん", "シ"));
        return radicalList;
    }

    public void openHomepage(View view) {
        Intent intent = new Intent(KanjiActivity.this, KanjiLessonActivity.class);
        startActivity(intent);
    }

    public LessonEntity findKanjiLessonById(String lessonId) {
        return new LessonEntity(1, "Bài 1", "Cơ bản");
    }
}