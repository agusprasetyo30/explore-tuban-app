package com.example.project2.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;


public class PendidikanItemViewHolder extends RecyclerView.ViewHolder
{
    private TextView tv_pendidikan_name;
    private ImageView img_pendidikan;

    public PendidikanItemViewHolder(@NonNull View itemView)
    {
        super(itemView);

        this.tv_pendidikan_name = itemView.findViewById(R.id.tv_wisata_pendidikan_item);
        this.img_pendidikan = itemView.findViewById(R.id.img_pendidikan_item);
    }
    
    public TextView getTv_pendidikan_name() {
        return tv_pendidikan_name;
    }
    
    public ImageView getImg_pendidikan() {
        return img_pendidikan;
    }
}
