package com.example.medicinereminder.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicinereminder.R;
import com.example.medicinereminder.RDBs.ReportRDB.ReportEntity;

import java.util.Date;
import java.util.List;

public class reportAdapter extends RecyclerView.Adapter<reportAdapter.ViewHolder>{
    List<ReportEntity> data;
    reportOnitemClicklistner listner;

    public reportAdapter(List<ReportEntity> data) {
        this.data = data;
    }
    public interface reportOnitemClicklistner{
        void onItemclick(int position);
    }

    //method to implement itemclick listner
    public void setOnitemClick(reportOnitemClicklistner onitemClick){
        listner=onitemClick;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_report,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.reportremindername.setText(data.get(position).getReportname());
        Date date=new Date(data.get(position).getReportnotifytime());
        holder.reportremindertmie.setText(date.toString());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView reportremindername,reportremindertmie;
        ImageButton deleteReport;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reportremindername=itemView.findViewById(R.id.reportdetails);
            reportremindertmie=itemView.findViewById(R.id.reportremindertime);
            deleteReport=itemView.findViewById(R.id.delete_report);
            deleteReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listner.onItemclick(getAdapterPosition());
                }
            });

        }
    }
}
