package com.example.project2.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;


public class PenginapanItemViewHolder extends RecyclerView.ViewHolder
{
    private TextView tv_penginapan_name;
    private ImageView img_penginapan;

    public PenginapanItemViewHolder(@NonNull View itemView)
    {
        super(itemView);

        this.tv_penginapan_name = itemView.findViewById(R.id.tv_penginapan_name_item);
        this.img_penginapan = itemView.findViewById(R.id.img_penginapan_item);
    }
    
    public TextView getTv_penginapan_name() {
        return tv_penginapan_name;
    }
    
    public ImageView getImg_penginapan() {
        return img_penginapan;
    }
}
