package com.example.japanese_app_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.japanese_app_android.R;
import com.example.japanese_app_android.adapter.AlphabetAdapter;
import com.example.japanese_app_android.model.AlphabetEntity;
import com.example.japanese_app_android.model.CategoryEntity;

import java.util.ArrayList;

public class AlphabetActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<AlphabetEntity> alphabetList;

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alphabet);
        recyclerView=findViewById(R.id.rc_alphabet);

        spinner = findViewById(R.id.alphabet_spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = (String) parent.getSelectedItem();
                Log.d("ok", select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing to do
            }
        });

        alphabetList=new ArrayList<>();

        alphabetList.add(new AlphabetEntity(1, "あ", "ア", "a", "sound", "writing ", "", 1));
        alphabetList.add(new AlphabetEntity(2, "い", "イ", "i", "sound", "writing ", "", 1));
        alphabetList.add(new AlphabetEntity(3, "う", "ウ", "u", "sound", "writing ", "", 1));
        alphabetList.add(new AlphabetEntity(4, "え", "エ", "e", "sound", "writing ", "", 1));
        alphabetList.add(new AlphabetEntity(5, "お", "オ", "o", "sound", "writing ", "", 1));
        alphabetList.add(new AlphabetEntity(6, "か", "カ", "ka", "sound", "writing ", "", 1));

        AlphabetAdapter adapter = new AlphabetAdapter(alphabetList, this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 5);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}