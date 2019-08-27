package com.example.project2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.adapters.RecyclerOlehOlehListAdapter;
import com.example.project2.adapters.RecyclerPenginapanListAdapter;
import com.example.project2.dao.OlehOlehDao;
import com.example.project2.dao.WisataDao;
import com.example.project2.data.AppDbProvider;
import com.example.project2.models.OlehOleh;

import java.util.ArrayList;

public class OlehOlehActivity extends AppCompatActivity {
	// Components
	private RecyclerView rv_oleh_oleh_list;
	
	// Data
	private RecyclerOlehOlehListAdapter recyclerOlehOlehListAdapter;
	private ArrayList<OlehOleh> olehOlehList = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oleh_oleh);
		setTitle("Oleh-Oleh Khas Tuban");
		
		this.initData();
		this.initComponents();
	}
	
	// Menambahkan data
	private void initData()
	{
		OlehOlehDao olehOlehDao = AppDbProvider.getAsynchronousInstance(getApplicationContext()).olehOlehDao();
		
		this.olehOlehList.addAll(olehOlehDao.getAll());
		
		this.recyclerOlehOlehListAdapter = new RecyclerOlehOlehListAdapter(this);
		this.recyclerOlehOlehListAdapter.setOlehOlehList(olehOlehList);
	}
	
	// menginisialisasi variavel, data ke dalam recycler view
	private void initComponents()
	{
		this.rv_oleh_oleh_list = findViewById(R.id.recycler_oleh_oleh);
		this.rv_oleh_oleh_list.setLayoutManager(new GridLayoutManager(this, 2));
		this.rv_oleh_oleh_list.setAdapter(this.recyclerOlehOlehListAdapter);
	}
}
