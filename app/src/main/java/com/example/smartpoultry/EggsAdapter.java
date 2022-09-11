package com.example.smartpoultry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EggsAdapter extends RecyclerView.Adapter<EggsAdapter.EggsViewHolder>{
    private Context context;
    private ArrayList flock_name,good_eggs,bad_eggs,date_eggs;

    public EggsAdapter(Context context, ArrayList flock_name, ArrayList good_eggs, ArrayList bad_eggs, ArrayList date_eggs) {
        this.context = context;
        this.flock_name = flock_name;
        this.good_eggs = good_eggs;
        this.bad_eggs = bad_eggs;
        this.date_eggs = date_eggs;
    }

    @NonNull
    @Override
    public EggsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.egg_view,parent,false);
        return new EggsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EggsViewHolder holder, int position) {
        holder.flock_name.setText(String.valueOf(flock_name.get(position)));
        holder.good_eggs.setText(String.valueOf(good_eggs.get(position)));
        holder.bad_eggs.setText(String.valueOf(bad_eggs.get(position)));
        holder.date_eggs.setText(String.valueOf(date_eggs.get(position)));

    }

    @Override
    public int getItemCount() {
        return flock_name.size();
    }

    public class EggsViewHolder extends RecyclerView.ViewHolder {
        TextView flock_name,good_eggs,bad_eggs,date_eggs;
        public EggsViewHolder(@NonNull View itemView) {
            super(itemView);
            flock_name = itemView.findViewById(R.id.rveggs_flock_name);
            good_eggs = itemView.findViewById(R.id.rv_good_eggs);
            bad_eggs = itemView.findViewById(R.id.rv_bad_eggs);
            date_eggs = itemView.findViewById(R.id.rv_date_egg);


        }
    }
}
