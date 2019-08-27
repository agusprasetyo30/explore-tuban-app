package com.example.project2.adapters;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.UpdateActivity;


public class WisataItemViewHolder extends RecyclerView.ViewHolder
{
    private TextView tv_wisata_name;
    private ImageView img_wisata;
    
    public WisataItemViewHolder(@NonNull View itemView)
    {
        super(itemView);

        this.tv_wisata_name = itemView.findViewById(R.id.tv_wisata_name_item);
        this.img_wisata = itemView.findViewById(R.id.img_wisata_item);
    }
    
    public TextView getTv_wisata_name() {
        return tv_wisata_name;
    }
    
    public ImageView getImg_wisata() {
        return img_wisata;
    }
}
