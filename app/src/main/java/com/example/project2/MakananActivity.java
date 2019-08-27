package com.example.project2;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.adapters.RecyclerMakananListAdapter;
import com.example.project2.adapters.RecyclerWisataListAdapter;
import com.example.project2.dao.MakananDao;
import com.example.project2.dao.WisataDao;
import com.example.project2.data.AppDbProvider;
import com.example.project2.models.Makanan;
import com.example.project2.models.Wisata;

import java.util.ArrayList;

public class MakananActivity extends AppCompatActivity {
	// Components
	private RecyclerView rv_makanan_list;
	
	// Data
	private RecyclerMakananListAdapter recyclerMakananListAdapter;
	private ArrayList<Makanan> makananList = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_makanan);
		setTitle("Makanan Khas Tuban");
		
		this.initData();
		this.initComponents();
	}
	
	// Menambahkan data
	private void initData()
	{
		MakananDao makananDao = AppDbProvider.getAsynchronousInstance(getApplicationContext()).makananDao();
		
		this.makananList.addAll(makananDao.getAll());
		
		this.recyclerMakananListAdapter = new RecyclerMakananListAdapter(this);
		this.recyclerMakananListAdapter.setMakananList(makananList);
	}
	
	// menginisialisasi variavel, data ke dalam recycler view
	private void initComponents()
	{
		this.rv_makanan_list = findViewById(R.id.recycler_makanan);
		this.rv_makanan_list.setLayoutManager(new GridLayoutManager(this, 2));
		this.rv_makanan_list.setAdapter(this.recyclerMakananListAdapter);
	}
}
