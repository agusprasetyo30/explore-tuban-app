package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.dao.UserDao;
import com.example.project2.data.AppDbProvider;
import com.example.project2.models.User;

public class LoginActivity extends AppCompatActivity {
	private EditText etUsername, etPassword;
	
	// Data yang akan disimpan/ditampilkan berupa property object dari Entity class User
	private User currentUser;
	
	// mengambil nama
	private String name;
	
	public static final String NAME_KEY = "name_key";
	public static boolean LOGIN_STATUS = false;
	public static String DATA_NAMA = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setTitle("Login Area");
		
		etUsername = findViewById(R.id.et_username);
		etPassword = findViewById(R.id.et_password);
	}
	
	public void clickLogin(View view) {
		//  jika username & password kosong
		if (TextUtils.isEmpty(etUsername.getText().toString().trim()) &&
			TextUtils.isEmpty(etPassword.getText().toString().trim())) {
			Toast.makeText(LoginActivity.this, "Username/Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
			etUsername.requestFocus();
		} else if (TextUtils.isEmpty(etUsername.getText().toString().trim())) {
			Toast.makeText(LoginActivity.this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
			etUsername.requestFocus();
		} else if (TextUtils.isEmpty(etPassword.getText().toString().trim())) {
			Toast.makeText(LoginActivity.this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
			etPassword.requestFocus();
		} else {
			boolean login_admin = false;
			
			if (etUsername.getText().equals("admin") && etPassword.getText().equals("admin")) {
				login_admin = true;
			}
			
			boolean validasi = this.validateCrundential();
			
			try {
				if (validasi && login_admin) {
					Intent i = new Intent(LoginActivity.this, AdminActivity.class);
					i.putExtra(NAME_KEY, name);
					startActivity(i);
					DATA_NAMA = name;
					
					this.clearEditText();
					finish();
				} else {
					Toast.makeText(this, "Invalid username and/or password!", Toast.LENGTH_LONG).show();
					this.clearEditText();
				}
			} catch (Exception e) {
				Log.e("TES", e.getMessage());
			}
		}
	}
	
	// Untuk validasi, jika username & password yg dimasukan sesuai dengan DUMMY yang disesuaikan maka akan bernilai TRUE
	private boolean validateCrundential() {
		boolean status = false;
		String currentUsername = this.etUsername.getText().toString();
		String currentPassword = this.etPassword.getText().toString();
		
		try {
		
			UserDao userDao = AppDbProvider.getAsynchronousInstance(getApplicationContext()).userDao();
			this.currentUser = userDao.findByUsernameAndPassword(currentUsername, currentPassword);
			
			if (this.currentUser != null) {
				status = true;
				name = currentUser.getName();
				LOGIN_STATUS = true;
			} else if (currentUsername.equals("login") && currentPassword.equals("admin")) {
				status = true;
				name = "Super Admin";
				LOGIN_STATUS = true;
			}
		
		} catch (Exception e) {
			Log.e("TES", e.getMessage());
		}
		
		return status;
	}
	
	private void clearEditText()
	{
		etUsername.setText("");
		etPassword.setText("");
	}
}
