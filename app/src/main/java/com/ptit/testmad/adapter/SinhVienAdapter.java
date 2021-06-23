package com.ptit.testmad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import com.ptit.testmad.R;
import com.ptit.testmad.model.SinhVien;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.MyViewHolder> {
    private Context context;
    private List<SinhVien> list;
    AdapterCallBack callBack;
    public SinhVienAdapter(List<SinhVien> list, Context context, AdapterCallBack callBack) {
        this.context = context;
        this.list = list;
        this.callBack=callBack;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        SinhVien sinhVien = list.get(position);
        initView(sinhVien, holder);
        holder.itemView.setOnClickListener(view ->{
            callBack.setOnClickItem(view, sinhVien);
        });

    }

    private void initView(SinhVien sv, MyViewHolder holder) {
        holder.tvTen.setText(sv.getTen());
        holder.tvNam.setText(""+sv.getNam());
        holder.tvQueQuan.setText(sv.getQuequan());
        holder.tvNamHoc.setText("Năm học: "+sv.getNamhoc());
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTen, tvNam, tvQueQuan, tvNamHoc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tv_ten);
            tvNam = itemView.findViewById(R.id.tv_namsinh);
            tvQueQuan = itemView.findViewById(R.id.tv_quequann);
            tvNamHoc = itemView.findViewById(R.id.tv_namhoc);
        }
    }

    public interface AdapterCallBack{
        void setOnClickItem(View view, SinhVien sinhVien);
    }


}
