package com.example.smartpoultry;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChickenAdapter extends RecyclerView.Adapter<ChickenAdapter.ChickenViewHolder> {
    private Context context;
    private ArrayList flock_name,flock_number,flock_bread,date_acq,flock_note;

    public ChickenAdapter(Context context, ArrayList flock_name, ArrayList flock_number, ArrayList flock_bread, ArrayList date_acq, ArrayList flock_note) {
        this.context = context;
        this.flock_name = flock_name;
        this.flock_number = flock_number;
        this.flock_bread = flock_bread;
        this.date_acq = date_acq;
        this.flock_note = flock_note;
    }

    @NonNull
    @Override
    public ChickenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.chicken_view,parent,false);
        return new ChickenViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChickenViewHolder holder, int position) {
        holder.flock_name.setText(String.valueOf(flock_name.get(position)));
        holder.flock_number.setText(String.valueOf(flock_number.get(position)));
        holder.flock_bread.setText(String.valueOf(flock_bread.get(position)));
        holder.date_acq.setText(String.valueOf(date_acq.get(position)));
        holder.flock_note.setText(String.valueOf(flock_note.get(position)));


    }

    @Override
    public int getItemCount() {
        return flock_name.size();
    }

    public class ChickenViewHolder extends RecyclerView.ViewHolder {
        TextView flock_name,flock_number,flock_bread,date_acq,flock_note;
        public ChickenViewHolder(@NonNull View itemView) {
            super(itemView);
            flock_name = itemView.findViewById(R.id.rv_flock_name);
            flock_bread = itemView.findViewById(R.id.rv_flock_bread);
            flock_number = itemView.findViewById(R.id.rv_flock_number);
            date_acq = itemView.findViewById(R.id.rv_flock_date);
            flock_note = itemView.findViewById(R.id.rv_flock_note);
        }
    }
}
