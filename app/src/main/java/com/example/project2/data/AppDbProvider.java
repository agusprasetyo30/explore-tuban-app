package com.example.project2.data;

import android.content.Context;

import androidx.room.Room;

// Class ini hanyalah class pembantu saja yang nantinya dapat
// memudahkan kita untuk mendapatkan instance dari class DTSAppDatabase
public class AppDbProvider
{
	// membuat variabel untuk menyimpan instance database asyncchronous
	private static DTSAppDatabase asynchronousInstance;
	
	//method untuk asynchronous
	public static DTSAppDatabase getAsynchronousInstance(Context context)
	{
		if (AppDbProvider.asynchronousInstance == null)
		{
			// menggunakan synchronous
			AppDbProvider.asynchronousInstance = Room.databaseBuilder(context, DTSAppDatabase.class, "dtsapp.db")
				.allowMainThreadQueries().build();
//
		}
		return AppDbProvider.asynchronousInstance;
	}
	
}
