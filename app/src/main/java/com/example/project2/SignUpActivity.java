package com.example.project2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import com.example.project2.dao.UserDao;
import com.example.project2.data.DTSAppDatabase;
import com.example.project2.data.DatabaseTask;
import com.example.project2.data.DatabaseTaskEventListener;
import com.example.project2.models.User;

public class SignUpActivity extends AppCompatActivity implements DatabaseTaskEventListener {
	
	// Loading indicator untuk ditampilkan saat menyimpan data
	ProgressDialog loadingIndicator;
	
	EditText etName, etUsername, etPassword, etConfirmPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		setTitle("Registrasi");
		
		this.initData();
	}
	
	private void initData()
	{
		etName = findViewById(R.id.et_reg_name);
		etUsername = findViewById(R.id.et_reg_username);
		etPassword = findViewById(R.id.et_reg_password);
		etConfirmPassword = findViewById(R.id.et_reg_confirm_password);
	}
	
	public void clickRegister(View view) {
		//  jika username & password kosong
		if (TextUtils.isEmpty(etUsername.getText().toString().trim()) &&
			TextUtils.isEmpty(etName.getText().toString().trim()) &&
		   TextUtils.isEmpty(etPassword.getText().toString().trim()) &&
			TextUtils.isEmpty(etConfirmPassword.getText().toString().trim()))
		{
			Toast.makeText(SignUpActivity.this, "Data register tidak boleh kosong", Toast.LENGTH_LONG).show();
			etName.requestFocus();
			
		} else if (TextUtils.isEmpty(etName.getText().toString().trim())) {
			Toast.makeText(SignUpActivity.this, "Nama harus diisi!", Toast.LENGTH_LONG).show();
			etName.requestFocus();
			
		} else if (TextUtils.isEmpty(etUsername.getText().toString().trim())) {
			Toast.makeText(SignUpActivity.this, "Username harus diisi!", Toast.LENGTH_LONG).show();
			etUsername.requestFocus();
			
		} else if (TextUtils.isEmpty(etPassword.getText().toString().trim())) {
			Toast.makeText(SignUpActivity.this, "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
			etPassword.requestFocus();
			
		}  else if (TextUtils.isEmpty(etConfirmPassword.getText().toString().trim())) {
			Toast.makeText(SignUpActivity.this, "Konfirmasi password tidak boleh kosong", Toast.LENGTH_LONG).show();
			etPassword.requestFocus();
			
		} else if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
			Toast.makeText(SignUpActivity.this, "Password yang anda masukan tidak sesuai", Toast.LENGTH_LONG).show();
			etPassword.setText("");
			etConfirmPassword.setText("");
			etPassword.requestFocus();
		} else {
			// Tampilkan loading indicator
			this.showLoadingIndicator();
			
			DatabaseTask databaseTask = new DatabaseTask(this, this);
			// Menjalankan Database Task
			databaseTask.execute();
			
			Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
			startActivity(i);
			finish();
			clearEditText();
		}
	}
	
	// Membuat entity class user baru berdasarkan isian user
	private User makeUser()
	{
		User u = new User();
		
		u.name = etName.getText().toString();
		u.username = etUsername.getText().toString();
		u.password = etPassword.getText().toString();
		
		return u;
	}
	
	// Menampilkan loading indicator
	private void showLoadingIndicator()
	{
		loadingIndicator = new ProgressDialog(this);
		loadingIndicator.setMessage("Uploading user data to server . . .");
		loadingIndicator.setIndeterminate(true);
		loadingIndicator.setCancelable(true);
		loadingIndicator.show();
	}
	
	@Override
	public Object runDatabaseOperation(RoomDatabase database, Object... params) {
		// Membuat object user yang di return pada method makeUser()
		User user = this.makeUser();
		
		// Membuat variabel db yang menginisialisasi abstract DTSAppDatabase
		DTSAppDatabase db = (DTSAppDatabase) database;
		
		// Membuat variabel userdao yang menginisialisasi interface UserDao yang mengambil data dari DTSAppDatabase
		UserDao userDao = db.userDao();
		
		// Menginsert data melalui DAO sesuai dengan perintahnya, pada script dibawah untuk mengInsert semua data
		// sesuai object yang dibuat
		userDao.insertAll(user);
		
		Log.d("TES", "Penambahan berhasil");
		Log.d("TES", "nama : " + user.getName() + " | username : " + user.getUsername());
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
				Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
			}
		}, 5000);
	}
	
	private void clearEditText()
	{
		etName.setText("");
		etUsername.setText("");
		etPassword.setText("");
		etConfirmPassword.setText("");
	}
}
