package com.example.japanese_app_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanese_app_android.AlphabetDetailActivity;
import com.example.japanese_app_android.R;
import com.example.japanese_app_android.model.AlphabetEntity;

import java.util.ArrayList;

public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.AlphabetHolder> {

    private Context context;
    private ArrayList<AlphabetEntity> alphabetList;

    private Spinner spinner;


    public AlphabetAdapter(ArrayList<AlphabetEntity> recycleAlphabetArrayList, Context context, Spinner spinner) {
        this.alphabetList = recycleAlphabetArrayList;
        this.context = context;
        this.spinner = spinner;
    }

    @NonNull
    @Override
    public AlphabetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alphabet_layout, parent, false);
        return new AlphabetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlphabetHolder holder, int position) {
        final AlphabetEntity alphabetEntity = alphabetList.get(position);

        //Kiểm tra giá trị được chọn từ Spinner
        String selectedAlphabet = spinner.getSelectedItem().toString();

        String displayText;
        if (selectedAlphabet.equals("Hiragana")) {
            displayText = alphabetEntity.getHiragana();
        } else if (selectedAlphabet.equals("Katakana")) {
            displayText = alphabetEntity.getKatakana();
        } else {
            displayText = alphabetEntity.getHiragana(); //giá trị mặc định nếu không có lựa chọn nào được chọn
        }

        holder.tv_alphabet.setText(displayText);
        holder.tv_alphabet.setText(alphabetEntity.getHiragana());
        holder.layoutItem.setOnClickListener(view -> onClickDetail(alphabetEntity));

    }

    private void onClickDetail(AlphabetEntity alphabetEntity) {
        Intent intent = new Intent(context, AlphabetDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("alphabet_detail", alphabetEntity);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return alphabetList.size();
    }

    public class AlphabetHolder extends RecyclerView.ViewHolder {

        private TextView tv_alphabet;

        private LinearLayout layoutItem;

        public AlphabetHolder(@NonNull View itemView) {
            super(itemView);
            tv_alphabet = itemView.findViewById(R.id.tv_alphabet);
            layoutItem = itemView.findViewById(R.id.layout_alphabet_item);
        }
    }

}
