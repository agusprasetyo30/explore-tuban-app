package com.example.project2.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project2.models.User;
import com.example.project2.models.Wisata;

import java.util.List;

@Dao
public interface WisataDao {
	
	// Query untuk menampilkan semua user
	@Query("SELECT * FROM wisata")
	List<Wisata> getAll();
	
	@Insert
	void insertAll(Wisata... wisata);
	
	@Delete
	void delete(Wisata wisata);
	
	@Update
	void update(Wisata wisata);
}
