package com.example.japanese_app_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanese_app_android.R;
import com.example.japanese_app_android.model.RadicalEntity;

import java.util.List;

public class RadicalAdapter extends RecyclerView.Adapter<RadicalAdapter.RadicalHolder> {

    Context context;

    List<RadicalEntity> radicalList;

    public RadicalAdapter(Context context, List<RadicalEntity> radicalList) {
        this.context = context;
        this.radicalList = radicalList;
    }

    @NonNull
    @Override
    public RadicalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.radical_layout,
                parent, false);

        return new RadicalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RadicalHolder holder, int position) {
        if (radicalList != null && !radicalList.isEmpty()) {
            RadicalEntity category = radicalList.get(position);
            holder.tv_stt.setText(category.getNumberOrder().toString());
            holder.tv_radical.setText(category.getRadical());
            holder.tv_name.setText(category.getName());
            holder.tv_meaning.setText(category.getMeaning());
        }
    }

    @Override
    public int getItemCount() {
        return radicalList.size();
    }

    public class RadicalHolder extends RecyclerView.ViewHolder {

        TextView tv_stt, tv_radical, tv_name, tv_meaning;

        public RadicalHolder(@NonNull View itemView) {
            super(itemView);

            tv_stt = itemView.findViewById(R.id.tv_radical_stt);
            tv_radical = itemView.findViewById(R.id.tv_radical_radical);
            tv_name = itemView.findViewById(R.id.tv_radical_name);
            tv_meaning = itemView.findViewById(R.id.tv_radical_meaning);

        }
    }
}
