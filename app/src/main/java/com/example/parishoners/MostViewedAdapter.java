package com.example.parishoners;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MostViewedAdapter extends RecyclerView.Adapter<MostViewedAdapter.MostViewedViewHolder> {
    ArrayList<MostViewedHelperClass> mostViewedFeatures;

    public MostViewedAdapter(ArrayList<MostViewedHelperClass> mostViewedFeatures) {
        this.mostViewedFeatures = mostViewedFeatures;
    }

    @NonNull
    @Override
    public MostViewedAdapter.MostViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design, parent, false);
        MostViewedViewHolder mostViewedViewHolder = new MostViewedViewHolder(view);
        return mostViewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedAdapter.MostViewedViewHolder holder, int position) {
        MostViewedHelperClass helperClass = mostViewedFeatures.get(position);
        holder.imageView.setImageResource(helperClass.getImage());
        holder.textView.setText(helperClass.getTitle());

    }

    @Override
    public int getItemCount() {
        return mostViewedFeatures.size();
    }

    public static class MostViewedViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MostViewedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mv_image);
            textView = itemView.findViewById(R.id.mv_title);
        }
    }
}
