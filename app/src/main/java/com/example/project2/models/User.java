package com.example.project2.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
	
	@PrimaryKey(autoGenerate = true )
	@NonNull
	private int id;
	
	@ColumnInfo(name = "username")
	public String username;
	
	@ColumnInfo(name = "password")
	public String password;
	
	@ColumnInfo(name = "name")
	public String name;
	
	public User() { }
	
//	public User(String username, String password, String email, String phoneNumber) {
//		this.username = username;
//		this.password = password;
//		this.email = email;
//		this.phoneNumber = phoneNumber;
//	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
