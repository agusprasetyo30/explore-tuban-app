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
import com.example.project2.models.Penginapan;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.project2.LoginActivity.LOGIN_STATUS;


public class RecyclerPenginapanListAdapter extends RecyclerView.Adapter<PenginapanItemViewHolder>
{
    private Context context;
    private List<Penginapan> penginapanList;

    public RecyclerPenginapanListAdapter(Context context)
    {
        this.context = context;
    }

    public List<Penginapan> getPenginapanList()
    {
        return penginapanList;
    }

    public void setPenginapanList(List<Penginapan> contactList)
    {
        this.penginapanList = contactList;

        // Biar nge-refresh
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PenginapanItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Dapatkan inflater
        LayoutInflater inflater = LayoutInflater.from(this.context);

        // Render view pakai inflater
        View penginapanItemView = inflater.inflate(R.layout.cardview_penginapan_item, parent, false);

        // View-nya dilempar ke ViewHolder
        PenginapanItemViewHolder vhPenginapanItem = new PenginapanItemViewHolder(penginapanItemView);

        // Jadikan nilai balik method ini
        return vhPenginapanItem;
    }

    // Ketika sedang me-render satu-persatu baris dari RecyclerView-nya...
    // Terjadi pe-renderan view holder satu persatu juga.
    @Override
    public void onBindViewHolder(@NonNull final PenginapanItemViewHolder holder, int position)
    {
        // Dapatkan data di posisi saat ini...
        final Penginapan currentPenginapan = this.penginapanList.get(position);

        // Pasang datanya di ViewHolder saat ini
        String name = currentPenginapan.getNama();
    
        Picasso.get()
           .load(currentPenginapan.getFoto())
           .placeholder(R.mipmap.ic_launcher)
           .fit()
           .centerCrop()
           .into(holder.getImg_penginapan());

        holder.getTv_penginapan_name().setText(name);
    
        if (LOGIN_STATUS) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(holder.itemView.getContext(), UpdateActivity.class);
                    i.putExtra("jenis", "penginapan");
                    i.putExtra("id", currentPenginapan.getId());
                    i.putExtra("nama", currentPenginapan.getNama());
                    i.putExtra("link_foto", currentPenginapan.getFoto());
                    holder.itemView.getContext().startActivity(i);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        if (this.penginapanList == null) {
            return 0;
        } else {
            return this.penginapanList.size();
        }
    }
}


