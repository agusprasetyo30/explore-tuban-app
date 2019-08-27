package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project2.adapters.RecyclerWisataListAdapter;
import com.example.project2.dao.WisataDao;
import com.example.project2.data.AppDbProvider;
import com.example.project2.models.Wisata;

import java.util.ArrayList;
import java.util.List;

public class WisataActivity extends AppCompatActivity {
	// Components
	private RecyclerView rv_wisata_list;
	
	// Data
	private RecyclerWisataListAdapter recyclerWisataListAdapter;
	private ArrayList<Wisata> wisataList = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wisata);
		setTitle("Objek Wisata di Tuban");
		
		initData();
		initComponents();
	}
	
	// Menambahkan data
	private void initData()
	{
		WisataDao wisataDao = AppDbProvider.getAsynchronousInstance(getApplicationContext()).wisataDao();
		
		this.wisataList.addAll(wisataDao.getAll());
		
		this.recyclerWisataListAdapter = new RecyclerWisataListAdapter(this);
		
		swap(wisataDao.getAll());
		recyclerWisataListAdapter.setWisataList(wisataList);
	}
	
	// menginisialisasi variavel, data ke dalam recycler view
	private void initComponents()
	{
		this.rv_wisata_list = findViewById(R.id.recycler_wisata);
		this.rv_wisata_list.setLayoutManager(new GridLayoutManager(this, 2));
		this.rv_wisata_list.setAdapter(this.recyclerWisataListAdapter);
	}
	
	public void swap(List<Wisata> wisatas)
	{
		wisataList.clear();
		wisataList.addAll(wisatas);
		recyclerWisataListAdapter.notifyDataSetChanged();
	}

}
