package com.example.project2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project2.models.Makanan;
import com.example.project2.models.Penginapan;

import java.util.List;

@Dao
public interface PenginapanDao {
	
	// Query untuk menampilkan semua user
	@Query("SELECT * FROM penginapan")
	List<Penginapan> getAll();
	
	@Insert
	void insertAll(Penginapan... penginapan);
	
	@Delete
	void delete(Penginapan penginapan);
	
	@Update
	void update(Penginapan penginapan);
}
