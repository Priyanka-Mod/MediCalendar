package com.example.medicinereminder.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicinereminder.R;
import com.example.medicinereminder.RDBs.personalRDB.entity;

import java.util.Date;
import java.util.List;

public class adapter_rv extends RecyclerView.Adapter<adapter_rv.MyviewHolder> {

    List<entity> data;
    Context c;
    OnitemClicklistner listner;

    public adapter_rv(List<entity> data, Context c) {
        this.data = data;
        this.c = c;
    }

    //interface for deleting item from view if it is checked
    public interface OnitemClicklistner{
        void onItemclick(int position);
    }

    //method to implement itemclick listner
    public void setOnitemClick(OnitemClicklistner onitemClick){
        listner=onitemClick;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_reminder,parent,false);
        return new MyviewHolder(itemview,listner);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_rv.MyviewHolder holder, int position) {
        entity model=data.get(position);
        holder.details.setText(model.getReminderdetails());
        Date date=new Date(model.getAlaramtime());
        holder.remindertime.setText(date.toString());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MyviewHolder extends RecyclerView.ViewHolder{
        TextView details,remindertime;
        ImageButton remove;
        public MyviewHolder(@NonNull View itemView,OnitemClicklistner listner) {
            super(itemView);
            remindertime=itemView.findViewById(R.id.remindertime);
            details=itemView.findViewById(R.id.details);
            remove=itemView.findViewById(R.id.remove);

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listner.onItemclick(getAdapterPosition());
                }
            });
        }
    }
}
