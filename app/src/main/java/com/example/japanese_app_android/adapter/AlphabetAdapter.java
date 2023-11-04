package com.example.japanese_app_android.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanese_app_android.R;
import com.example.japanese_app_android.model.AlphabetEntity;

import java.util.ArrayList;

public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.AlphabetHolder> {

    private Context context;
    private ArrayList<AlphabetEntity> alphabetList;

    public AlphabetAdapter(ArrayList<AlphabetEntity> recycleAlphabetArrayList, Context context) {
        this.alphabetList = recycleAlphabetArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AlphabetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alphabet_layout, parent, false);
        return new AlphabetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlphabetHolder holder, int position) {
        AlphabetEntity alphabetEntity = alphabetList.get(position);
        holder.tv_alphabet.setText(alphabetEntity.getHiragana());

    }

    @Override
    public int getItemCount() {
        return alphabetList.size();
    }

    public class AlphabetHolder extends RecyclerView.ViewHolder {

        private TextView tv_alphabet;

        public AlphabetHolder(@NonNull View itemView) {
            super(itemView);
            tv_alphabet = itemView.findViewById(R.id.tv_alphabet);
        }
    }

}
