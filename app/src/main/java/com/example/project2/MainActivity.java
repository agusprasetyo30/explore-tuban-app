package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {
	
	CarouselView carouselView;
	
	int[] sampleImage = {R.drawable.gambar4, R.drawable.gambar1, R.drawable.gambar2, R.drawable.gambar3};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		carouselView = findViewById(R.id.carouselView);
		carouselView.setPageCount(sampleImage.length);
		
		carouselView.setImageListener(imageListener);
	}
	
	ImageListener imageListener = new ImageListener() {
		@Override
		public void setImageForPosition(int position, ImageView imageView) {
			imageView.setImageResource(sampleImage[position]);
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_login, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == R.id.menu_login) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void clickWisataButton(View view) {
		Intent intent = new Intent(this, WisataActivity.class);
		startActivity(intent);
	}
	
	public void clickPendidikanButton(View view) {
		Intent intent = new Intent(this, PendidikanActivity.class);
		startActivity(intent);
	}
	
	public void clickMakananButton(View view) {
		Intent intent = new Intent(this, MakananActivity.class);
		startActivity(intent);
	}
	
	public void clickPenginapanButton(View view) {
		Intent intent = new Intent(this, PenginapanActivity.class);
		startActivity(intent);
	}
	
	public void clickOlehOlehButton(View view) {
		Intent intent = new Intent(this, OlehOlehActivity.class);
		startActivity(intent);
	}
}
