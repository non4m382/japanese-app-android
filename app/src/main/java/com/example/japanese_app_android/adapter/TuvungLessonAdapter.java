package com.example.japanese_app_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.japanese_app_android.R;
import com.example.japanese_app_android.model.KanjiLesson;

import java.util.List;

public class TuvungLessonAdapter extends RecyclerView.Adapter<TuvungLessonAdapter.ViewHolder> {
    private List<KanjiLesson> lessonList;
    private Context context;

    public TuvungLessonAdapter(Context context, List<KanjiLesson> lessonList) {
        this.context = context;
        this.lessonList = lessonList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for the lesson
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tuvung_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (lessonList != null && position < lessonList.size()) {
            KanjiLesson lesson = lessonList.get(position);
            if (lesson != null) {
                // Set the lesson name and content
                holder.tvName.setText(lesson.getName());
                holder.tvContent.setText(lesson.getContent());
            }
        }
    }

    @Override
    public int getItemCount() {
        // Return the total number of lessons
        return lessonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvContent;

        public ViewHolder(View view) {
            super(view);
            // Initialize the TextViews
            tvName = view.findViewById(R.id.idNameKanJi);
            tvContent = view.findViewById(R.id.idContentKanJi);
        }
    }
}
