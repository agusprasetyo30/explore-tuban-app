package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2.models.Makanan;
import com.example.project2.models.OlehOleh;
import com.example.project2.models.Pendidikan;
import com.example.project2.models.Penginapan;

import static com.example.project2.LoginActivity.DATA_NAMA;
import static com.example.project2.LoginActivity.LOGIN_STATUS;
import static com.example.project2.LoginActivity.NAME_KEY;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
	TextView tvNameLogin;
	Button btn_lihatData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		tvNameLogin = findViewById(R.id.tv_name_login);
		btn_lihatData = findViewById(R.id.lihat_data_button);
		
		btn_lihatData.setOnClickListener(this);
		
		Intent i = getIntent();
		String nama = i.getStringExtra(NAME_KEY);
		tvNameLogin.setText(DATA_NAMA);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_admin, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == R.id.menu_add_user) {
			Intent intent = new Intent(this, SignUpActivity.class);
			startActivity(intent);
			return true;
		} else if (item.getItemId() == R.id.menu_logout) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			Toast.makeText(this, "Logout Sukses", Toast.LENGTH_LONG).show();
			LOGIN_STATUS = false;
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void tambahData_click(View view) {
		Intent intent = new Intent(this, AddDataActivity.class);
		startActivity(intent);
	}
	
	
	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.lihat_data_button) {
			// Menampilkan alert
			final String[] action = {"Tempat Wisata", "Pendidikan", "Makanan Khas", "Penginapan", "Oleh-oleh"};
			
			AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
			alert.setItems(action, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int i) {
					switch (i) {
						case 0:
							Intent intent1 = new Intent(AdminActivity.this, WisataActivity.class);
							startActivity(intent1);
							break;
						
						case 1:
							Intent intent2 = new Intent(AdminActivity.this, PendidikanActivity.class);
							startActivity(intent2);
							break;
							
						case 2:
							Intent intent3 = new Intent(AdminActivity.this, MakananActivity.class);
							startActivity(intent3);
							break;
							
						case 3:
							Intent intent4 = new Intent(AdminActivity.this, PenginapanActivity.class);
							startActivity(intent4);
							break;
							
						case 4:
							Intent intent5 = new Intent(AdminActivity.this, OlehOlehActivity.class);
							startActivity(intent5);
							break;
					}
				}
			});
			alert.create();
			alert.show();
		}
	}
}
