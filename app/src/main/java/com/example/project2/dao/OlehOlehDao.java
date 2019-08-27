package com.example.project2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project2.models.OlehOleh;
import com.example.project2.models.Penginapan;

import java.util.List;

@Dao
public interface OlehOlehDao {
	
	// Query untuk menampilkan semua user
	@Query("SELECT * FROM oleholeh")
	List<OlehOleh> getAll();
	
	@Insert
	void insertAll(OlehOleh... oleholeh);
	
	@Delete
	void delete(OlehOleh oleholeh);
	
	@Update
	void update(OlehOleh oleholeh);
}
