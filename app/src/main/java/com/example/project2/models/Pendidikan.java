package com.example.project2.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pendidikan")
public class Pendidikan {
	
	@PrimaryKey(autoGenerate = true )
	@NonNull
	private int id;
	
	@ColumnInfo(name = "nama")
	public String nama;
	
	@ColumnInfo(name = "foto")
	public String foto;
	
	public Pendidikan() {
	}
	
	//	public Pendidikan(String nama, String foto) {
//		this.nama = nama;
//		this.foto = foto;
//	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNama() {
		return nama;
	}
	
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
