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
import com.example.japanese_app_android.model.RadicalEntity;

import java.util.List;

public class KanjiAdapter extends RecyclerView.Adapter<KanjiAdapter.KanjiHolder> {

    Context context;

    List<KanjiEntity> kanjiEntities;

    public KanjiAdapter(Context context, List<KanjiEntity> kanjiEntities) {
        this.context = context;
        this.kanjiEntities = kanjiEntities;
    }

    @NonNull
    @Override
    public KanjiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kanji_list_layout,
                parent, false);

        return new KanjiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KanjiHolder holder, int position) {
        if (kanjiEntities != null && !kanjiEntities.isEmpty()) {
            KanjiEntity kanji = kanjiEntities.get(position);
            if(position % 2 == 0){
                holder.itemView.setBackgroundResource(R.drawable.bg_row_even);
            }
            holder.tv_kanji_reading.setText(kanji.getReading());
            holder.tv_kanji_writing.setText(kanji.getWriting());
            holder.tv_kanji_meaning.setText(kanji.getMeaning());
            holder.tv_kanji_on.setText(kanji.getOnyomi());
            holder.tv_kanji_kun.setText(kanji.getKunyomi());

        }
    }

    @Override
    public int getItemCount() {
        return kanjiEntities.size();
    }

    public class KanjiHolder extends RecyclerView.ViewHolder {

        TextView tv_kanji_reading, tv_kanji_writing, tv_kanji_meaning, tv_kanji_on, tv_kanji_kun;

        public KanjiHolder(@NonNull View itemView) {
            super(itemView);

            tv_kanji_reading = itemView.findViewById(R.id.tv_kanji_reading);
            tv_kanji_writing = itemView.findViewById(R.id.tv_kanji_writing);
            tv_kanji_meaning = itemView.findViewById(R.id.tv_kanji_meaning);
            tv_kanji_on = itemView.findViewById(R.id.tv_kanji_on);
            tv_kanji_kun = itemView.findViewById(R.id.tv_kanji_kun);
        }
    }
}
