package com.example.japanese_app_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.japanese_app_android.util.SharedPref;

public class MainActivity extends AppCompatActivity {

    private TextView tvNguPhapDetail1, tvNguPhapDetail2, tvNguPhapDetail3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        SharedPref.init(getApplicationContext());

        if (!SharedPref.read(SharedPref.LOGGED, false)) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        //Lấy dữ liệu của Ngữ Pháp detail để đẩy sang NguPhapMoiNgayDetail.class
        //Các bạn truyền dữ liệu vào thì tự sửa nhé, mình đang để dữ liệu ảo <3
        tvNguPhapDetail1 = findViewById(R.id.tvNguPhap1);
        tvNguPhapDetail1.setOnClickListener(v -> {
            String NguPhapDetail1 = tvNguPhapDetail1.getText().toString();
            Intent intent1 = new Intent(MainActivity.this, NguPhapMoiNgayDetail.class);
            intent1.putExtra("detail1", NguPhapDetail1);
            startActivity(intent1);
        });

        tvNguPhapDetail2 = findViewById(R.id.tvNguPhap2);
        tvNguPhapDetail2.setOnClickListener(v -> {
            String NguPhapDetail2 = tvNguPhapDetail2.getText().toString();
            Intent intent2 = new Intent(MainActivity.this, NguPhapMoiNgayDetail.class);
            intent2.putExtra("detail2", NguPhapDetail2);
            startActivity(intent2);
        });

        tvNguPhapDetail3 = findViewById(R.id.tvNguPhap3);
        tvNguPhapDetail3.setOnClickListener(v -> {
            String NguPhapDetail3 = tvNguPhapDetail3.getText().toString();
            Intent intent3 = new Intent(MainActivity.this, NguPhapMoiNgayDetail.class);
            intent3.putExtra("detail3", NguPhapDetail3);
            startActivity(intent3);
        });

    }

    public void openRadicalActivity(View view) {
        Intent intent = new Intent(MainActivity.this, RadicalActivity.class);
        Log.d("main_activity", "radical clicked");
        startActivity(intent);
    }

    public void openProfileUpdateAcitivity(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileUpdateAcitivity.class);
        Log.d("main_activity", "profile update clicked");
        startActivity(intent);
    }
}