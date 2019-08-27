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
import com.example.project2.models.OlehOleh;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.project2.LoginActivity.LOGIN_STATUS;


public class RecyclerOlehOlehListAdapter extends RecyclerView.Adapter<OlehOlehItemViewHolder>
{
    private Context context;
    private List<OlehOleh> olehOlehList;

    public RecyclerOlehOlehListAdapter(Context context)
    {
        this.context = context;
    }

    public List<OlehOleh> getOlehOlehList()
    {
        return olehOlehList;
    }

    public void setOlehOlehList(List<OlehOleh> contactList)
    {
        this.olehOlehList = contactList;

        // Biar nge-refresh
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OlehOlehItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Dapatkan inflater
        LayoutInflater inflater = LayoutInflater.from(this.context);

        // Render view pakai inflater
        View olehOlehItemView = inflater.inflate(R.layout.cardview_oleh_oleh_item, parent, false);

        // View-nya dilempar ke ViewHolder
        OlehOlehItemViewHolder vhOlehOlehItem = new OlehOlehItemViewHolder(olehOlehItemView);

        // Jadikan nilai balik method ini
        return vhOlehOlehItem;
    }

    // Ketika sedang me-render satu-persatu baris dari RecyclerView-nya...
    // Terjadi pe-renderan view holder satu persatu juga.
    @Override
    public void onBindViewHolder(@NonNull final OlehOlehItemViewHolder holder, int position)
    {
        // Dapatkan data di posisi saat ini...
        final OlehOleh currentOlehOleh = this.olehOlehList.get(position);

        // Pasang datanya di ViewHolder saat ini
        String name = currentOlehOleh.getNama();
    
        Picasso.get()
           .load(currentOlehOleh.getFoto())
           .placeholder(R.mipmap.ic_launcher)
           .fit()
           .centerCrop()
           .into(holder.getImg_oleh_oleh());

        holder.getTv_oleh_oleh_name().setText(name);
        
        if (LOGIN_STATUS) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(holder.itemView.getContext(), UpdateActivity.class);
                    i.putExtra("jenis", "oleholeh");
                    i.putExtra("id", currentOlehOleh.getId());
                    i.putExtra("nama", currentOlehOleh.getNama());
                    i.putExtra("link_foto", currentOlehOleh.getFoto());
                    holder.itemView.getContext().startActivity(i);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        if (this.olehOlehList == null) {
            return 0;
        } else {
            return this.olehOlehList.size();
        }
    }
}


