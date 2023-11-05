package com.example.japanese_app_android;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.japanese_app_android.R;
import com.example.japanese_app_android.model.AlphabetEntity;

import java.io.IOException;

public class AlphabetDetailActivity extends AppCompatActivity {

    ImageButton btnback, btnsound;

    MediaPlayer soundAlphabet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alphabet_detail);
        btnback = findViewById(R.id.btn_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Thoát AlphabetDetailActivity
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            AlphabetEntity alphabetEntity = (AlphabetEntity) bundle.get("alphabet_detail");
            ImageView imgAlphabet = findViewById(R.id.img_alphabet_detail);
            Glide.with(this)
                    .load(alphabetEntity.getWritingHiragana())
                    .into(imgAlphabet);

        } else {
            // Xử lý trường hợp khi không có dữ liệu được truyền từ Intent
        }

}
}