package com.example.japanese_app_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NguPhapMoiNgayDetail extends AppCompatActivity {

    private TextView tvDetails;
    FloatingActionButton btnBack;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngu_phap_moi_ngay_detail);
        tvDetails = findViewById(R.id.npDetail);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(NguPhapMoiNgayDetail.this, MainActivity.class);
                startActivity(back);
            }
        });

        Intent i1 = getIntent();
        String detail1 = i1.getStringExtra("detail1");
        tvDetails.setText(detail1);

        Intent i2 = getIntent();
        String detail2 = i2.getStringExtra("detail2");
        tvDetails.setText(detail2);

        Intent i3 = getIntent();
        String detail3 = i3.getStringExtra("detail3");
        tvDetails.setText(detail3);

    }
}