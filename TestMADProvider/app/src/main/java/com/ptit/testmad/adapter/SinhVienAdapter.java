package com.ptit.testmad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ptit.testmad.R;
import com.ptit.testmad.model.B17DCCN528_GiangVien;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.MyViewHolder> {
    private Context context;
    private List<B17DCCN528_GiangVien> list;
    AdapterCallBack callBack;

    public SinhVienAdapter(List<B17DCCN528_GiangVien> list, Context context, AdapterCallBack callBack) {
        this.context = context;
        this.list = list;
        this.callBack=callBack;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TẠO ITEM view
        View view = LayoutInflater.from(context).inflate(R.layout.item_sv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        //load dữ liệu vào item
        B17DCCN528_GiangVien sinhVien = list.get(position);
        initView(sinhVien, holder);
        holder.itemView.setOnClickListener(view ->{
            callBack.setOnClickItem(view, sinhVien);
        });

    }

    private void initView(B17DCCN528_GiangVien sv, MyViewHolder holder) {
        holder.tvTen.setText(sv.getTen());
        holder.tvNam.setText(""+sv.getId());
        holder.tvQueQuan.setText(sv.getTrinhDo());
        holder.tvNamHoc.setText("KN: "+sv.getSoNamKN());
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
        void setOnClickItem(View view, B17DCCN528_GiangVien sinhVien);
    }


}
