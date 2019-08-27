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
import com.example.project2.models.Pendidikan;
import com.example.project2.models.Wisata;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.project2.LoginActivity.LOGIN_STATUS;


public class RecyclerPendidikanListAdapter extends RecyclerView.Adapter<PendidikanItemViewHolder>
{
    private Context context;
    private List<Pendidikan> pendidikanList;

    public RecyclerPendidikanListAdapter(Context context)
    {
        this.context = context;
    }

    public List<Pendidikan> getContactList()
    {
        return pendidikanList;
    }

    public void setPendidikanList(List<Pendidikan> pendidikanList)
    {
        this.pendidikanList = pendidikanList;

        // Biar nge-refresh
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PendidikanItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Dapatkan inflater
        LayoutInflater inflater = LayoutInflater.from(this.context);

        // Render view pakai inflater
        View pendidikanItemView = inflater.inflate(R.layout.cardview_pendidikan_item, parent, false);

        // View-nya dilempar ke ViewHolder
        PendidikanItemViewHolder vhpendidikanItem = new PendidikanItemViewHolder(pendidikanItemView);

        // Jadikan nilai balik method ini
        return vhpendidikanItem;
    }

    // Ketika sedang me-render satu-persatu baris dari RecyclerView-nya...
    // Terjadi pe-renderan view holder satu persatu juga.
    @Override
    public void onBindViewHolder(@NonNull final PendidikanItemViewHolder holder, int position)
    {
        // Dapatkan data di posisi saat ini...
        final Pendidikan currentPendidikan = this.pendidikanList.get(position);

        // Pasang datanya di ViewHolder saat ini
        String name = currentPendidikan.getNama();
    
        Picasso.get()
           .load(currentPendidikan.getFoto())
           .placeholder(R.mipmap.ic_launcher)
           .fit()
           .centerCrop()
           .into(holder.getImg_pendidikan());

        holder.getTv_pendidikan_name().setText(name);
    
        if (LOGIN_STATUS) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(holder.itemView.getContext(), UpdateActivity.class);
                    i.putExtra("jenis", "pendidikan");
                    i.putExtra("id", currentPendidikan.getId());
                    i.putExtra("nama", currentPendidikan.getNama());
                    i.putExtra("link_foto", currentPendidikan.getFoto());
                    holder.itemView.getContext().startActivity(i);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        if (this.pendidikanList == null) {
            return 0;
        } else {
            return this.pendidikanList.size();
        }
    }
}


