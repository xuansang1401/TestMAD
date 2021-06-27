package com.ptit.testmad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ptit.testmad.R;
import com.ptit.testmad.model.B17DCCN528_ChuyenMon;

import java.util.List;

public class LopAdapter extends RecyclerView.Adapter<LopAdapter.MyViewHolder> {
    private Context context;
    private List<B17DCCN528_ChuyenMon> list;
    private AdapterLopCallBack callBack;
    public LopAdapter(List<B17DCCN528_ChuyenMon> list, Context context, AdapterLopCallBack callBack) {
        this.context = context;
        this.list = list;
        this.callBack=callBack;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lop, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        B17DCCN528_ChuyenMon lop = list.get(position);
        initView(lop, holder);

        holder.itemView.setOnClickListener(view->{
            callBack.setOnClickItem(view,lop);
        });
    }

    private void initView(B17DCCN528_ChuyenMon lop, MyViewHolder holder) {
        holder.tvTen.setText(lop.getTen());
        holder.tvMOta.setText(""+lop.getMoTa());

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTen, tvMOta;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tv_ten_lop);
            tvMOta = itemView.findViewById(R.id.tv_mota);

        }
    }

    public interface AdapterLopCallBack{
        void setOnClickItem(View view, B17DCCN528_ChuyenMon lop);
    }

}
