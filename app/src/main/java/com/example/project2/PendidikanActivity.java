package com.example.project2;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.adapters.RecyclerPendidikanListAdapter;
import com.example.project2.adapters.RecyclerWisataListAdapter;
import com.example.project2.dao.PendidikanDao;
import com.example.project2.dao.WisataDao;
import com.example.project2.data.AppDbProvider;
import com.example.project2.models.Pendidikan;
import com.example.project2.models.Wisata;

import java.util.ArrayList;

public class PendidikanActivity extends AppCompatActivity {
	// Components
	private RecyclerView rv_pendidikan_list;
	
	// Data
	private RecyclerPendidikanListAdapter recyclerPendidikanListAdapter;
	private ArrayList<Pendidikan> pendidikanList = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pendidikan);
		setTitle("Pendidikan");
		
		this.initData();
		this.initComponents();
	}
	
	// Menambahkan data
	private void initData()
	{
		PendidikanDao pendidikanDao = AppDbProvider.getAsynchronousInstance(getApplicationContext()).pendidikanDao();
		
		this.pendidikanList.addAll(pendidikanDao.getAll());
		
		this.recyclerPendidikanListAdapter = new RecyclerPendidikanListAdapter(this);
		this.recyclerPendidikanListAdapter.setPendidikanList(pendidikanList);
	}
	
	// menginisialisasi variavel, data ke dalam recycler view
	private void initComponents()
	{
		this.rv_pendidikan_list = findViewById(R.id.recycler_pendidikan);
		this.rv_pendidikan_list.setLayoutManager(new GridLayoutManager(this, 2));
		this.rv_pendidikan_list.setAdapter(this.recyclerPendidikanListAdapter);
	}
	
	
}
