package com.example.project2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.UpdateActivity;
import com.example.project2.models.Wisata;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.project2.LoginActivity.LOGIN_STATUS;


public class RecyclerWisataListAdapter extends RecyclerView.Adapter<WisataItemViewHolder> {
    private Context context;
    private List<Wisata> wisataList;

    public RecyclerWisataListAdapter(Context context)
    {
        this.context = context;
    }

    public List<Wisata> getWisataList()
    {
        return wisataList;
    }

    public void setWisataList(List<Wisata> contactList)
    {
        this.wisataList = contactList;
        
        // Biar nge-refresh
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WisataItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Dapatkan inflater
        LayoutInflater inflater = LayoutInflater.from(this.context);

        // Render view pakai inflater
        View wisataItemView = inflater.inflate(R.layout.cardview_wisata_item, parent, false);

        // View-nya dilempar ke ViewHolder
        WisataItemViewHolder vhWisataItem = new WisataItemViewHolder(wisataItemView);

        // Jadikan nilai balik method ini
        return vhWisataItem;
    }

    // Ketika sedang me-render satu-persatu baris dari RecyclerView-nya...
    // Terjadi pe-renderan view holder satu persatu juga.
    @Override
    public void onBindViewHolder(@NonNull final WisataItemViewHolder holder, int position)
    {
        // Dapatkan data di posisi saat ini...
        final Wisata currentWisata = this.wisataList.get(position);
        
        // Pasang datanya di ViewHolder saat ini
        String name = currentWisata.getNama();
    
        Picasso.get()
           .load(currentWisata.getFoto())
           .placeholder(R.mipmap.ic_launcher)
           .fit()
           .centerCrop()
           .into(holder.getImg_wisata());

        holder.getTv_wisata_name().setText(name);
    
        if (LOGIN_STATUS) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(holder.itemView.getContext(), UpdateActivity.class);
                    i.putExtra("jenis", "wisata");
                    i.putExtra("id", currentWisata.getId());
                    i.putExtra("nama", currentWisata.getNama());
                    i.putExtra("link_foto", currentWisata.getFoto());
                    holder.itemView.getContext().startActivity(i);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        if (this.wisataList == null) {
            return 0;
        } else {
            return this.wisataList.size();
        }
    }
    

}


