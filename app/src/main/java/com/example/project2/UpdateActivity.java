package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2.adapters.RecyclerWisataListAdapter;
import com.example.project2.dao.MakananDao;
import com.example.project2.dao.OlehOlehDao;
import com.example.project2.dao.PendidikanDao;
import com.example.project2.dao.PenginapanDao;
import com.example.project2.dao.WisataDao;
import com.example.project2.data.DTSAppDatabase;
import com.example.project2.data.DatabaseTask;
import com.example.project2.data.DatabaseTaskEventListener;
import com.example.project2.models.Makanan;
import com.example.project2.models.OlehOleh;
import com.example.project2.models.Pendidikan;
import com.example.project2.models.Penginapan;
import com.example.project2.models.Wisata;

public class UpdateActivity extends AppCompatActivity implements DatabaseTaskEventListener {
	// Loading indicator untuk ditampilkan saat menyimpan data
	ProgressDialog loadingIndicator;
	
	private TextView tv_jenis;
	private TextView tv_id;
	private EditText et_name;
	private EditText et_link_photo;
	private boolean update_onClick = false;
	private boolean delete_onClick = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		tv_jenis = findViewById(R.id.tv_jenis_data);
		tv_id = findViewById(R.id.tv_id);
		et_name = findViewById(R.id.et_update_name);
		et_link_photo = findViewById(R.id.et_update_link_photo);
		setTitle("Update & Delete Data");
		
		Intent i = getIntent();
		int id = i.getIntExtra("id", 0);
		String nama = i.getStringExtra("nama");
		String link_foto = i.getStringExtra("link_foto");
		String jenis = i.getStringExtra("jenis");
		
		
		tv_jenis.setText(jenis);
		tv_id.setText(String.valueOf(id));
		et_name.setText(nama);
		et_link_photo.setText(link_foto);
	}
	
	public void updateData_click(View view) {
		update_onClick = true;
		delete_onClick = false;
		this.showLoadingIndicator();
		
		DatabaseTask databaseTask = new DatabaseTask(this, this);
		databaseTask.execute();
	}
	
	public void deleteData_click(View view) {
		delete_onClick = true;
		update_onClick = false;
		this.showLoadingIndicator();
		DatabaseTask databaseTask = new DatabaseTask(this, this);
		databaseTask.execute();
	}
	
	private Wisata makeWisata()
	{
		Wisata w = new Wisata();
		
		w.setId(Integer.valueOf(tv_id.getText().toString()));
		w.nama = et_name.getText().toString();
		w.foto = et_link_photo.getText().toString();
		
		return w;
	}
	
	private Pendidikan makePendidikan()
	{
		Pendidikan p = new Pendidikan();
		
		p.setId(Integer.valueOf(tv_id.getText().toString()));
		p.nama = et_name.getText().toString();
		p.foto = et_link_photo.getText().toString();
		
		return p;
	}
	
	private Makanan makeMakanan()
	{
		Makanan m = new Makanan();
		
		m.setId(Integer.valueOf(tv_id.getText().toString()));
		m.nama = et_name.getText().toString();
		m.foto = et_link_photo.getText().toString();
		
		return m;
	}
	
	private Penginapan makePenginapan()
	{
		Penginapan p = new Penginapan();
		
		p.setId(Integer.valueOf(tv_id.getText().toString()));
		p.nama = et_name.getText().toString();
		p.foto = et_link_photo.getText().toString();
		
		return p;
	}
	
	private OlehOleh makeOlehOleh()
	{
		OlehOleh o = new OlehOleh();
		
		o.setId(Integer.valueOf(tv_id.getText().toString()));
		o.nama = et_name.getText().toString();
		o.foto = et_link_photo.getText().toString();
		
		return o;
	}
	
	@Override
	public Object runDatabaseOperation(RoomDatabase database, Object... params) {
		// Membuat variabel db yang menginisialisasi abstract DTSAppDatabase
		DTSAppDatabase db = (DTSAppDatabase) database;
		
		Wisata wisata = this.makeWisata();
		Pendidikan pendidikan = this.makePendidikan();
		Makanan makanan = this.makeMakanan();
		Penginapan penginapan = this.makePenginapan();
		OlehOleh olehOleh = this.makeOlehOleh();
		
		if (tv_jenis.getText().equals("wisata")) {
				if (update_onClick) {
					WisataDao wisataDao = db.wisataDao();
					wisataDao.update(wisata);
				}
				if (delete_onClick) {
					WisataDao wisataDao = db.wisataDao();
					wisataDao.delete(wisata);
				}
		} else if (tv_jenis.getText().equals("pendidikan")) {
				if (update_onClick) {
					PendidikanDao pendidikanDao = db.pendidikanDao();
					pendidikanDao.update(pendidikan);
				}
				if (delete_onClick) {
					PendidikanDao pendidikanDao = db.pendidikanDao();
					pendidikanDao.delete(pendidikan);
				}
		} else if (tv_jenis.getText().equals("makanan")) {
				if (update_onClick) {
					MakananDao makananDao = db.makananDao();
					makananDao.update(makanan);
				}
				if (delete_onClick) {
					MakananDao makananDao = db.makananDao();
					makananDao.delete(makanan);
				}
		} else if (tv_jenis.getText().equals("penginapan")) {
				if (update_onClick) {
					PenginapanDao penginapanDao = db.penginapanDao();
					penginapanDao.update(penginapan);
				}
				if (delete_onClick) {
					PenginapanDao penginapanDao = db.penginapanDao();
					penginapanDao.delete(penginapan);
				}
		} else if (tv_jenis.getText().equals("oleholeh")) {
				if (update_onClick) {
					OlehOlehDao olehOlehDao = db.olehOlehDao();
					olehOlehDao.update(olehOleh);
				}
				if (delete_onClick) {
					OlehOlehDao olehOlehDao = db.olehOlehDao();
					olehOlehDao.delete(olehOleh);
				}
		}
		
		return null;
	}
	
	@Override
	public void onDatabaseOperationFinished(Object... results) {
		// Delay eksekusi program agar nampak agak lama seolah-olah datanya sedang diunggah
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// Tutup loading indicator & tampilkan toast
				loadingIndicator.dismiss();
				Toast.makeText(getApplicationContext(), "Proses data sukses", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(UpdateActivity.this, AdminActivity.class);
				startActivity(i);
				clearData();
				finish();
			}
		}, 5000);
	}
	
	// Menampilkan loading indicator
	private void showLoadingIndicator()
	{
		loadingIndicator = new ProgressDialog(this);
		loadingIndicator.setMessage("Data sedang di proses . . .");
		loadingIndicator.setIndeterminate(true);
		loadingIndicator.setCancelable(true);
		loadingIndicator.show();
	}
	
	private void clearData()
	{
		et_name.setText("");
		et_link_photo.setText("");
		tv_jenis.setText("");
		tv_id.setText("");
	}
}
