package com.example.project2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project2.models.Makanan;
import com.example.project2.models.Pendidikan;

import java.util.List;

@Dao
public interface MakananDao {
	
	// Query untuk menampilkan semua user
	@Query("SELECT * FROM makanan")
	List<Makanan> getAll();
	
	@Insert
	void insertAll(Makanan... makanan);
	
	@Delete
	void delete(Makanan makanan);
	
	@Update
	void update(Makanan makanan);
}
