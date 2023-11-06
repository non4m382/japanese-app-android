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

    AlphabetEntity alphabetEntity;

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

        //Lấy dữ liệu từ Intent để lấy image của chữ cái
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            alphabetEntity = (AlphabetEntity) bundle.get("alphabet_detail");
            //Hiển thị hình ảnh
            ImageView imgAlphabet = findViewById(R.id.img_alphabet_detail);
            Glide.with(this)
                    .load(alphabetEntity.getWritingHiragana())
                    .into(imgAlphabet);

        } else {
            // Xử lý trường hợp khi không có dữ liệu được truyền từ Intent
        }

        //Ánh xạ cho button sound
        btnsound = findViewById(R.id.sound_button);
        btnsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound();
            }
        });

    }

    private void playSound() {
        if (alphabetEntity != null) {
            String soundUrl = alphabetEntity.getSound();
            soundAlphabet = new MediaPlayer();
            soundAlphabet.setAudioStreamType(AudioManager.STREAM_MUSIC);

            try {
                soundAlphabet.setDataSource(soundUrl);
                soundAlphabet.prepare();
                soundAlphabet.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}