package com.example.smartpoultry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.VaccineViewHolder> {
    private Context context;
    private ArrayList enrollname,enrollvaccine,enrolldate,enrollnote;

    public VaccineAdapter(Context context, ArrayList enrollname, ArrayList enrollvaccine, ArrayList enrolldate, ArrayList enrollnote) {
        this.context = context;
        this.enrollname = enrollname;
        this.enrollvaccine = enrollvaccine;
        this.enrolldate = enrolldate;
        this.enrollnote = enrollnote;
    }

    @NonNull
    @Override
    public VaccineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.vaccine_view,parent,false);
        return new VaccineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineViewHolder holder, int position) {
        holder.enrollname.setText(String.valueOf(enrollname.get(position)));
        holder.enrollvaccine.setText(String.valueOf(enrollvaccine.get(position)));
        holder.enrolldate.setText(String.valueOf(enrolldate.get(position)));
        holder.enrollnote.setText(String.valueOf(enrollnote.get(position)));



    }

    @Override
    public int getItemCount() {
        return enrollname.size();
    }

    public class VaccineViewHolder extends RecyclerView.ViewHolder {
        TextView enrollname,enrollvaccine,enrolldate,enrollnote;
        public VaccineViewHolder(@NonNull View itemView) {
            super(itemView);
            enrollname = itemView.findViewById(R.id.enroll_flock_name);
            enrollvaccine = itemView.findViewById(R.id.enroll_vaccine);
            enrolldate = itemView.findViewById(R.id.enroll_date);
            enrollnote = itemView.findViewById(R.id.enroll_note);

        }
    }
}
