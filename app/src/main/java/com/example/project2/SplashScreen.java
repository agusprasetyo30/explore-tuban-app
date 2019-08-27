package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
	private ProgressBar progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		progress = findViewById(R.id.progress_bar);
		
		progress.setIndeterminate(true);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				progress.setIndeterminate(false);
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				startActivity(i);
				finish();
			}
		}, 5000);
	}
}
