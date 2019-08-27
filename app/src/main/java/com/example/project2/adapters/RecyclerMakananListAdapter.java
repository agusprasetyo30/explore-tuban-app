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
import com.example.project2.models.Makanan;
import com.example.project2.models.Wisata;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.project2.LoginActivity.LOGIN_STATUS;


public class RecyclerMakananListAdapter extends RecyclerView.Adapter<MakananItemViewHolder>
{
    private Context context;
    private List<Makanan> makananList;

    public RecyclerMakananListAdapter(Context context)
    {
        this.context = context;
    }

    public List<Makanan> getMakananList()
    {
        return makananList;
    }

    public void setMakananList(List<Makanan> contactList)
    {
        this.makananList = contactList;

        // Biar nge-refresh
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MakananItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Dapatkan inflater
        LayoutInflater inflater = LayoutInflater.from(this.context);

        // Render view pakai inflater
        View makananItemView = inflater.inflate(R.layout.cardview_makanan_item, parent, false);

        // View-nya dilempar ke ViewHolder
        MakananItemViewHolder vhMakananItem = new MakananItemViewHolder(makananItemView);

        // Jadikan nilai balik method ini
        return vhMakananItem;
    }

    // Ketika sedang me-render satu-persatu baris dari RecyclerView-nya...
    // Terjadi pe-renderan view holder satu persatu juga.
    @Override
    public void onBindViewHolder(@NonNull final MakananItemViewHolder holder, int position)
    {
        // Dapatkan data di posisi saat ini...
        final Makanan currentMakanan = this.makananList.get(position);

        // Pasang datanya di ViewHolder saat ini
        String name = currentMakanan.getNama();
    
        Picasso.get()
           .load(currentMakanan.getFoto())
           .placeholder(R.mipmap.ic_launcher)
           .fit()
           .centerCrop()
           .into(holder.getImg_makanan());

        holder.getTv_makanan_name().setText(name);
    
        if (LOGIN_STATUS) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(holder.itemView.getContext(), UpdateActivity.class);
                    i.putExtra("jenis", "makanan");
                    i.putExtra("id", currentMakanan.getId());
                    i.putExtra("nama", currentMakanan.getNama());
                    i.putExtra("link_foto", currentMakanan.getFoto());
                    holder.itemView.getContext().startActivity(i);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        if (this.makananList == null) {
            return 0;
        } else {
            return this.makananList.size();
        }
    }
}


