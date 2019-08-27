package com.example.project2.data;



import androidx.room.RoomDatabase;

// interface yang akan digunakan untuk menangani event-event
// pada saat operasi database sedang dan telah selesai dilakukan.

public interface DatabaseTaskEventListener
{
	Object runDatabaseOperation(RoomDatabase database, Object... params);
	void onDatabaseOperationFinished(Object... results);
}
