package com.example.japanese_app_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanese_app_android.R;
import com.example.japanese_app_android.model.KanjiEntity;
import com.example.japanese_app_android.model.VocabularyEntity;

import java.util.List;

public class VocabAdapter extends RecyclerView.Adapter<VocabAdapter.VocabHolder> {

    Context context;

    List<VocabularyEntity> list;

    public VocabAdapter(Context context, List<VocabularyEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VocabHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vocab_list_layout,
                parent, false);

        return new VocabHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabHolder holder, int position) {
        if (list != null && !list.isEmpty()) {
            VocabularyEntity kanji = list.get(position);
            if(position % 2 == 0){
                holder.itemView.setBackgroundResource(R.drawable.bg_row_even);
            }
            holder.tv_vocab_name.setText(kanji.getName());
            holder.tv_vocab_kanji.setText(kanji.getKanji());
            holder.tv_vocab_meaning.setText(kanji.getMeaning());
            holder.tv_vocab_sound.setText(kanji.getSound());
            holder.tv_vocab_example.setText(kanji.getExample());

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VocabHolder extends RecyclerView.ViewHolder {

        TextView tv_vocab_name, tv_vocab_kanji, tv_vocab_meaning, tv_vocab_sound, tv_vocab_example;

        public VocabHolder(@NonNull View itemView) {
            super(itemView);

            tv_vocab_name = itemView.findViewById(R.id.tv_vocab_name);
            tv_vocab_kanji = itemView.findViewById(R.id.tv_vocab_kanji);
            tv_vocab_meaning = itemView.findViewById(R.id.tv_vocab_meaning);
            tv_vocab_sound = itemView.findViewById(R.id.tv_vocab_sound);
            tv_vocab_example = itemView.findViewById(R.id.tv_vocab_example);
        }
    }
}
