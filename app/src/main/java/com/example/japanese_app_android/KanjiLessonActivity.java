package com.example.japanese_app_android;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanese_app_android.adapter.KanjiLessonAdapter;
import com.example.japanese_app_android.model.KanjiLesson;

import java.util.ArrayList;
import java.util.List;
public class KanjiLessonActivity extends AppCompatActivity {
    private List<KanjiLesson> generateKanjiLessons() {
        List<KanjiLesson> lessons = new ArrayList<>();
        lessons.add(new KanjiLesson(1, "Bài 1", "Nội dung bài 1"));
        lessons.add(new KanjiLesson(2, "Bài 2", "Nội dung bài 2"));
        lessons.add(new KanjiLesson(3, "Bài 3", "Nội dung bài 3"));
        lessons.add(new KanjiLesson(4, "Bài 4", "Nội dung bài 4"));
        lessons.add(new KanjiLesson(5, "Bài 5", "Nội dung bài 5"));

        return lessons;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kanji_lesson_activity); // Gắn layout XML vào Activity

        RecyclerView recyclerView = findViewById(R.id.recyclerView); // Khởi tạo RecyclerView
        List<KanjiLesson> lessons = generateKanjiLessons(); // Lấy danh sách bài học

        // Khởi tạo Adapter và thiết lập cho RecyclerView
        KanjiLessonAdapter adapter = new KanjiLessonAdapter(this, lessons);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // Số cột trong lưới (ở đây là 2)

        // Xử lý sự kiện khi người dùng nhấp vào một mục danh sách (nếu cần)
    }

}
