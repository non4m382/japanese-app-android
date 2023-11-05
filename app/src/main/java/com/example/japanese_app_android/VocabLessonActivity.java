package com.example.japanese_app_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanese_app_android.adapter.TuvungLessonAdapter;
import com.example.japanese_app_android.model.LessonEntity;

import java.util.ArrayList;
import java.util.List;

public class VocabLessonActivity extends AppCompatActivity {
    private List<LessonEntity> generateKanjiLessons() {
        List<LessonEntity> lessons = new ArrayList<>();
        lessons.add(new LessonEntity(1, "Bài 1", "Nội dung bài 1"));
        lessons.add(new LessonEntity(2, "Bài 2", "Nội dung bài 2"));
        lessons.add(new LessonEntity(3, "Bài 3", "Nội dung bài 3"));
        lessons.add(new LessonEntity(4, "Bài 4", "Nội dung bài 4"));
        lessons.add(new LessonEntity(5, "Bài 5", "Nội dung bài 5"));

        return lessons;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuvung_lesson_activity); // Gắn layout XML vào Activity

        RecyclerView recyclerView = findViewById(R.id.recyclerView); // Khởi tạo RecyclerView
        List<LessonEntity> lessons = generateKanjiLessons(); // Lấy danh sách bài học

        // Khởi tạo Adapter và thiết lập cho RecyclerView
        TuvungLessonAdapter adapter = new TuvungLessonAdapter(this, lessons);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // Số cột trong lưới (ở đây là 2)

        // Xử lý sự kiện khi người dùng nhấp vào một mục danh sách (nếu cần)
    }

}
