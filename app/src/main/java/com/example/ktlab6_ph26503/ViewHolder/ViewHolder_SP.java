package com.example.ktlab6_ph26503.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ktlab6_ph26503.R;

public class ViewHolder_SP extends RecyclerView.ViewHolder {
    public TextView tv_sp_content;
    public TextView tv_sp_date;
    public TextView tv_sp_price;
    public TextView tv_details_sp;
    public ImageView imgUpdateSP;
    public ImageView imgDeleteSP;

    public ViewHolder_SP(@NonNull View itemView) {
        super(itemView);
        tv_sp_date = itemView.findViewById(R.id.tv_date_ISP);
        tv_sp_content = itemView.findViewById(R.id.tv_content_ISP);
        tv_sp_price = itemView.findViewById(R.id.tv_price_ISP);
        imgUpdateSP = itemView.findViewById(R.id.img_updateSP);
        imgDeleteSP = itemView.findViewById(R.id.img_deleteSP);
    }
}
