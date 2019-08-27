package com.example.project2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.adapters.RecyclerMakananListAdapter;
import com.example.project2.adapters.RecyclerPenginapanListAdapter;
import com.example.project2.dao.PenginapanDao;
import com.example.project2.dao.WisataDao;
import com.example.project2.data.AppDbProvider;
import com.example.project2.models.Makanan;
import com.example.project2.models.Penginapan;

import java.util.ArrayList;

public class PenginapanActivity extends AppCompatActivity {
	// Components
	private RecyclerView rv_penginapan_list;
	
	// Data
	private RecyclerPenginapanListAdapter recyclerPenginapanListAdapter;
	private ArrayList<Penginapan> penginapanList = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_penginapan);
		setTitle("Penginapan");
		
		this.initData();
		this.initComponents();
	}
	
	// Menambahkan data
	private void initData()
	{
		PenginapanDao penginapanDao = AppDbProvider.getAsynchronousInstance(getApplicationContext()).penginapanDao();
		
		this.penginapanList.addAll(penginapanDao.getAll());
		
		this.recyclerPenginapanListAdapter = new RecyclerPenginapanListAdapter(this);
		this.recyclerPenginapanListAdapter.setPenginapanList(penginapanList);
	}
	
	// menginisialisasi variavel, data ke dalam recycler view
	private void initComponents()
	{
		this.rv_penginapan_list = findViewById(R.id.recycler_penginapan);
		this.rv_penginapan_list.setLayoutManager(new GridLayoutManager(this, 2));
		this.rv_penginapan_list.setAdapter(this.recyclerPenginapanListAdapter);
	}
}
