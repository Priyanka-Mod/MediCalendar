package com.example.medicinereminder.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicinereminder.R;
import com.example.medicinereminder.RDBs.medicineRDB.MedicineEntity;

import java.util.List;

public class PillAdapeter extends RecyclerView.Adapter<PillAdapeter.ViewHolder>{
    List<MedicineEntity> data;
    pillOnitemClicklistner listner;

    public PillAdapeter(List<MedicineEntity> data) {
        this.data = data;
    }
    public interface pillOnitemClicklistner{
        void onItemclick(int position);
    }
    public void setOnitemClick(pillOnitemClicklistner onitemClick){
        listner=onitemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_medicine,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MedicineEntity model=data.get(position);
        holder.pillname.setText(model.getPillname());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView pillname;
        ImageButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pillname=itemView.findViewById(R.id.detailsofpill);
            delete=itemView.findViewById(R.id.removepill);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listner.onItemclick(getAdapterPosition());

                }
            });


        }
    }
}
