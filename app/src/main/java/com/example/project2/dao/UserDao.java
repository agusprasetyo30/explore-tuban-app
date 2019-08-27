package com.example.project2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project2.models.User;

import java.util.List;

@Dao
public interface UserDao {
	
	// Query untuk menampilkan semua user
	@Query("SELECT * FROM user")
	List<User> getAll();
	
	// Menampilkan user berdasarkan username
	@Query("SELECT * FROM user WHERE username IN (:usernameList)")
	List<User> loadAllByIds(String[] usernameList);
	
	// Menampilkan 1 user
	@Query("SELECT * FROM user LIMIT 1")
	User selectOne();
	
	// Menampilkan data yang username dan passwordnya sama
	@Query("SELECT * FROM user WHERE username = :username AND password = :password LIMIT 1")
	User findByUsernameAndPassword(String username, String password);
	
	@Insert
	void insertAll(User... users);
	
	@Delete
	void delete(User user);
	
	@Update
	void update(User user);
}
