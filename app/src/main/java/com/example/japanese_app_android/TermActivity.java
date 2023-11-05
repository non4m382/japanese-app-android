package com.example.japanese_app_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TermActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term);
    }

    public void openHomepage(View view) {
        Intent intent = new Intent(TermActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void openKanjiLesson(View view) {
        Intent intent = new Intent(TermActivity.this, KanjiLessonActivity.class);
        startActivity(intent);
    }
    public void openVocabLesson(View view) {
        Intent intent = new Intent(TermActivity.this, VocabLessonActivity.class);
        startActivity(intent);
    }
}
