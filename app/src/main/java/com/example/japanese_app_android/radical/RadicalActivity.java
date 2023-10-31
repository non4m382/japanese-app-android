package com.example.japanese_app_android.radical;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanese_app_android.R;
import com.example.japanese_app_android.model.CategoryEntity;
import com.example.japanese_app_android.model.Chapter;
import com.example.japanese_app_android.model.adapter.CategorySpinnerAdapter;
import com.example.japanese_app_android.model.adapter.ChapterAdapter;
import com.example.japanese_app_android.model.response.GeneralResponse;
import com.example.japanese_app_android.retrofit.RadicalApi;
import com.example.japanese_app_android.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RadicalActivity extends AppCompatActivity {

    private Spinner categorySpinner;

    private CategorySpinnerAdapter categoryAdapter;

    Toolbar myToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radical);


        myToolBar = findViewById(R.id.app_bar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        myToolBar.inflateMenu(R.menu.menu_radical);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

//        setRecyclerView();
    }

    void setRecyclerView() {
        Chapter c1 = new Chapter(R.drawable.android_image_1, "Chapter 1", "Android Architecture");
        Chapter c2 = new Chapter(R.drawable.android_image_2, "Chapter 2", "Android Layout");
        Chapter c3 = new Chapter(R.drawable.android_image_3, "Chapter 3", "Android Activity");
        Chapter c4 = new Chapter(R.drawable.android_image_4, "Chapter 4", "Android Intent");
        Chapter c5 = new Chapter(R.drawable.android_image_5, "Chapter 5", "Android Style");
        Chapter c6 = new Chapter(R.drawable.android_image_6, "Chapter 6", "Android Recycler View");

        List<Chapter> chapters = new ArrayList<>();
        chapters.add(c1);
        chapters.add(c2);
        chapters.add(c3);
        chapters.add(c4);
        chapters.add(c5);
        chapters.add(c6);
        chapters.add(c6);
        chapters.add(c6);
        chapters.add(c6);
        chapters.add(c6);
        chapters.add(c6);
        chapters.add(c6);
        chapters.add(c6);

        RecyclerView rec = findViewById(R.id.rec_chapters);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(new ChapterAdapter(chapters));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_radical, menu);
        setSpinner(menu);
        return true;
    }

    void setSpinner(Menu menu) {
        MenuItem spinner_item = menu.findItem(R.id.spinner);
        List<CategoryEntity> categoryEntities = getAllCategories();
        categoryAdapter = new CategorySpinnerAdapter(RadicalActivity.this,
                R.layout.layout_drop_title, categoryEntities);
        categorySpinner = (AppCompatSpinner) spinner_item.getActionView();
        categorySpinner.setAdapter(categoryAdapter);
        categoryAdapter.setDropDownViewResource(R.layout.layout_drop_title);
        myToolBar.addView(categorySpinner);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RadicalActivity.this, "Something changed", LENGTH_SHORT).show();
                Log.i("ok", "checked");
                CategoryEntity item = categoryAdapter.getItem(position);
                if (item != null) {
                    Log.i("spinner", "got it");
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                            position + "",
                            Toast.LENGTH_LONG).show());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void setSpinner2(Menu menu) {

    }

    private List<CategoryEntity> getAllCategories() {
        List<CategoryEntity> mdList = new ArrayList<>();
        RetrofitService retrofitService = new RetrofitService();
        RadicalApi radicalApi = retrofitService.getRetrofit().create(RadicalApi.class);
        radicalApi.getAllCategories().enqueue(new Callback<GeneralResponse<List<CategoryEntity>>>() {
            @Override
            public void onResponse(Call<GeneralResponse<List<CategoryEntity>>> call,
                                   Response<GeneralResponse<List<CategoryEntity>>> response) {
                GeneralResponse<List<CategoryEntity>> generalResponse = response.body();
                mdList.addAll(generalResponse.getData());
            }

            @Override
            public void onFailure(Call<GeneralResponse<List<CategoryEntity>>> call, Throwable t) {
                Toast.makeText(RadicalActivity.this, "Failed to load categories",
                        LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        return mdList;
    }

}
