package com.example.project2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project2.models.Pendidikan;
import com.example.project2.models.Wisata;

import java.util.List;

@Dao
public interface PendidikanDao {
	
	// Query untuk menampilkan semua user
	@Query("SELECT * FROM pendidikan")
	List<Pendidikan> getAll();
	
	@Insert
	void insertAll(Pendidikan... pendidikan);
	
	@Delete
	void delete(Pendidikan pendidikan);
	
	@Update
	void update(Pendidikan pendidikan);
}
