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

import com.example.japanese_app_android.adapter.KanjiAdapter;
import com.example.japanese_app_android.adapter.RadicalAdapter;
import com.example.japanese_app_android.model.CategoryEntity;
import com.example.japanese_app_android.model.KanjiEntity;
import com.example.japanese_app_android.model.RadicalEntity;

import java.util.ArrayList;
import java.util.List;

public class KanjiActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    KanjiAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kanji_list_activity);

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
        radicalList.add(new KanjiEntity(1,"Tư","私","cá nhân","わたし","シ"));
        radicalList.add(new KanjiEntity(2,"Tuế","歳","tuổi","とし","セイ"));
        return radicalList;
    }

    public void openHomepage(View view) {
        Intent intent = new Intent(KanjiActivity.this, MainActivity.class);
        startActivity(intent);
    }
}