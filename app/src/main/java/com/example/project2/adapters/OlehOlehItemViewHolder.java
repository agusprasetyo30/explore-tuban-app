package com.example.project2.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;


public class OlehOlehItemViewHolder extends RecyclerView.ViewHolder
{
    private TextView tv_oleh_oleh_name;
    private ImageView img_oleh_oleh;

    public OlehOlehItemViewHolder(@NonNull View itemView)
    {
        super(itemView);

        this.tv_oleh_oleh_name = itemView.findViewById(R.id.tv_oleh_oleh_name_item);
        this.img_oleh_oleh = itemView.findViewById(R.id.img_oleh_oleh_item);
    }
    
    public TextView getTv_oleh_oleh_name() {
        return tv_oleh_oleh_name;
    }
    
    public ImageView getImg_oleh_oleh() {
        return img_oleh_oleh;
    }
}
