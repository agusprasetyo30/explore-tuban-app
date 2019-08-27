package com.example.project2.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;


public class MakananItemViewHolder extends RecyclerView.ViewHolder
{
    private TextView tv_makanan_name;
    private ImageView img_makanan;

    public MakananItemViewHolder(@NonNull View itemView)
    {
        super(itemView);

        this.tv_makanan_name = itemView.findViewById(R.id.tv_makanan_name_item);
        this.img_makanan = itemView.findViewById(R.id.img_makanan_item);
    }
    
    public TextView getTv_makanan_name() {
        return tv_makanan_name;
    }
    
    public ImageView getImg_makanan() {
        return img_makanan;
    }
}
