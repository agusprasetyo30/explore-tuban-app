package com.example.project2.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.RoomDatabase;

import java.lang.ref.WeakReference;

public class DatabaseTask extends AsyncTask<Object, Void, Void> {
	
	// Room database butuh context
	private WeakReference<Context> databaseContext; // dimasukan WeakReference untuk mencegah kebocoran memory
	
	// Activity yang akan menjalankan operasi database di background secara asynchronous
	private DatabaseTaskEventListener eventListener;
	
	// Hasil dari operasi yang selesai dilakukan
	private Object operationResult;
	
	public DatabaseTask(Context databaseContext, DatabaseTaskEventListener eventListener) {
		this.databaseContext = new WeakReference<>(databaseContext);
		this.eventListener = eventListener;
	}
	
	// Operasi yang dijalankan di background secara Asynchronous
	@Override
	protected Void doInBackground(Object... objects) {
		
		// Mendapatkan instance database asynchronous dari AppDbProvider
		RoomDatabase database = AppDbProvider.getAsynchronousInstance(this.databaseContext.get());
		
		// Melemparkan instance database tadi ke event listener untuk diambil DAO-nya nanti
		this.operationResult = this.eventListener.runDatabaseOperation(database, objects);
		
		return null;
	}
	
	// merupakan tempat bagi kita untuk meletakkan kode yang ingin
	// dieksekusi ketika proses background selesai dikerjakan.
	@Override
	protected void onPostExecute(Void aVoid) {
		super.onPostExecute(aVoid);
		
		// Memberitahukan kepada listener bahwasannya operasi yang dilakukan di-background telah selesai
		this.eventListener.onDatabaseOperationFinished(this.operationResult);
	}
}
