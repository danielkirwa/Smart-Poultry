package com.example.smartpoultry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChickAdapter extends RecyclerView.Adapter<ChickAdapter.ChickViewHolder> {
    Context context;
    private ArrayList flock_name,flock_number,flock_bread,date_added,flock_agg;

    public ChickAdapter(Context context, ArrayList flock_name, ArrayList flock_number, ArrayList flock_bread, ArrayList date_added, ArrayList flock_agg) {
        this.context = context;
        this.flock_name = flock_name;
        this.flock_number = flock_number;
        this.flock_bread = flock_bread;
        this.date_added = date_added;
        this.flock_agg = flock_agg;
    }

    @NonNull
    @Override
    public ChickViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.chick_view,parent,false);
        return new ChickViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChickViewHolder holder, int position) {
         holder.flock_name.setText(String.valueOf(flock_name.get(position)));
         holder.flock_number.setText(String.valueOf(flock_number.get(position)));
        holder.flock_bread.setText(String.valueOf(flock_bread.get(position)));
        holder.date_added.setText(String.valueOf(date_added.get(position)));
        holder.flock_agg.setText(String.valueOf(flock_agg.get(position)));
    }

    @Override
    public int getItemCount() {
        return flock_name.size();
    }

    public class ChickViewHolder extends RecyclerView.ViewHolder {
        TextView flock_name,flock_number,flock_bread,date_added,flock_agg;
        public ChickViewHolder(@NonNull View itemView) {
            super(itemView);
            flock_name = itemView.findViewById(R.id.rv_name_chick);
            flock_number = itemView.findViewById(R.id.rv_number_chick);
            flock_bread = itemView.findViewById(R.id.rv_bread_chick);
            date_added = itemView.findViewById(R.id.rv_date_chick);
            flock_agg = itemView.findViewById(R.id.rv_age_chick);
        }
    }
}
