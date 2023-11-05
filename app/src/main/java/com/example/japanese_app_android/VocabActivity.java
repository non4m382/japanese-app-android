package com.example.japanese_app_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanese_app_android.adapter.KanjiAdapter;
import com.example.japanese_app_android.adapter.VocabAdapter;
import com.example.japanese_app_android.model.KanjiEntity;
import com.example.japanese_app_android.model.VocabularyEntity;

import java.util.ArrayList;
import java.util.List;

public class VocabActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    VocabAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocab_list_activity);

        recyclerView = findViewById(R.id.rv_vocab);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRecycleView();
    }


    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VocabAdapter(this, getList());
        recyclerView.setAdapter(adapter);
    }

    private List<VocabularyEntity> getList() {
        List<VocabularyEntity> radicalList = new ArrayList<>();
        radicalList.add(new VocabularyEntity(1, 1,"私","tôi","わたし","watashi","シ"));
        radicalList.add(new VocabularyEntity(2, 1,"年","Năm","とし","toshi","年をとる"));
        return radicalList;
    }

    public void openHomepage(View view) {
        Intent intent = new Intent(VocabActivity.this, VocabLessonActivity.class);
        startActivity(intent);
    }
}