package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project2.dao.MakananDao;
import com.example.project2.dao.OlehOlehDao;
import com.example.project2.dao.PendidikanDao;
import com.example.project2.dao.PenginapanDao;
import com.example.project2.dao.UserDao;
import com.example.project2.dao.WisataDao;
import com.example.project2.data.DTSAppDatabase;
import com.example.project2.data.DatabaseTask;
import com.example.project2.data.DatabaseTaskEventListener;
import com.example.project2.models.Makanan;
import com.example.project2.models.OlehOleh;
import com.example.project2.models.Pendidikan;
import com.example.project2.models.Penginapan;
import com.example.project2.models.User;
import com.example.project2.models.Wisata;

public class AddDataActivity extends AppCompatActivity implements DatabaseTaskEventListener {
	// Loading indicator untuk ditampilkan saat menyimpan data
	ProgressDialog loadingIndicator;
	
	EditText nama;
	EditText linkFoto;
	Spinner jenisData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_data);
		setTitle("Tambah Data");
		
		nama = findViewById(R.id.et_data_name);
		linkFoto = findViewById(R.id.et_link_photo);
		jenisData = findViewById(R.id.jenis_data);
	}
	
	public void tambahData_click(View view) {
		if (TextUtils.isEmpty(nama.getText().toString().trim())) {
			Toast.makeText(AddDataActivity.this, "Nama harus diisi!", Toast.LENGTH_LONG).show();
			nama.requestFocus();
		} else if (TextUtils.isEmpty(linkFoto.getText().toString().trim())) {
			Toast.makeText(AddDataActivity.this, "Link harus diisi!", Toast.LENGTH_LONG).show();
			linkFoto.requestFocus();
		} else {
			this.showLoadingIndicator();
			
			DatabaseTask databaseTask = new DatabaseTask(this, this);
			databaseTask.execute();
		}
	}
	
	@Override
	public Object runDatabaseOperation(RoomDatabase database, Object... params) {
		// Membuat variabel db yang menginisialisasi abstract DTSAppDatabase
		DTSAppDatabase db = (DTSAppDatabase) database;
		
		// Membuat variabel userdao yang menginisialisasi interface UserDao yang mengambil data dari DTSAppDatabase
		
		if (jenisData.getSelectedItemId() == 0) {
			Wisata wisata = this.makeWisata();
			WisataDao wisataDao = db.wisataDao();
			
			// insert data
			wisataDao.insertAll(wisata);
		} else if (jenisData.getSelectedItemId() == 1) {
			Pendidikan pendidikan = this.makePendidikan();
			PendidikanDao pendidikanDao = db.pendidikanDao();
			
			// insert data
			pendidikanDao.insertAll(pendidikan);
		} else if (jenisData.getSelectedItemId() == 2) {
			Makanan makanan = this.makeMakanan();
			MakananDao makananDao = db.makananDao();
			
			// insert data
			makananDao.insertAll(makanan);
		} else if (jenisData.getSelectedItemId() == 3) {
			Penginapan penginapan = this.makePenginapan();
			PenginapanDao penginapanDao = db.penginapanDao();
			
			// insert data
			penginapanDao.insertAll(penginapan);
		} else if (jenisData.getSelectedItemId() == 4) {
			OlehOleh olehOleh = this.makeOlehOleh();
			OlehOlehDao olehOlehDao = db.olehOlehDao();
			
			// insert data
			olehOlehDao.insertAll(olehOleh);
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
				Toast.makeText(getApplicationContext(), "Tambah data sukses", Toast.LENGTH_SHORT).show();
				clearData();
				finish();
			}
		}, 5000);
	}
	
	private Wisata makeWisata()
	{
		Wisata w = new Wisata();
		
		w.nama = nama.getText().toString();
		w.foto = linkFoto.getText().toString();
		
		return w;
	}
	
	private Pendidikan makePendidikan()
	{
		Pendidikan p = new Pendidikan();
		
		p.nama = nama.getText().toString();
		p.foto = linkFoto.getText().toString();
		
		return p;
	}
	
	private Makanan makeMakanan()
	{
		Makanan m = new Makanan();
		
		m.nama = nama.getText().toString();
		m.foto = linkFoto.getText().toString();
		
		return m;
	}
	
	private Penginapan makePenginapan()
	{
		Penginapan p = new Penginapan();
		
		p.nama = nama.getText().toString();
		p.foto = linkFoto.getText().toString();
		
		return p;
	}
	
	private OlehOleh makeOlehOleh()
	{
		OlehOleh o = new OlehOleh();
		
		o.nama = nama.getText().toString();
		o.foto = linkFoto.getText().toString();
		
		return o;
	}
	
	// Menampilkan loading indicator
	private void showLoadingIndicator()
	{
		loadingIndicator = new ProgressDialog(this);
		loadingIndicator.setMessage("Proses penyimpanan data . . .");
		loadingIndicator.setIndeterminate(true);
		loadingIndicator.setCancelable(true);
		loadingIndicator.show();
	}
	
	// menghapus inputan
	private void clearData()
	{
		nama.setText("");
		linkFoto.setText("");
		jenisData.setSelection(0);
	}
}
