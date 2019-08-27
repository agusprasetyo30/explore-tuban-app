package com.example.project2.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.project2.dao.MakananDao;
import com.example.project2.dao.OlehOlehDao;
import com.example.project2.dao.PendidikanDao;
import com.example.project2.dao.PenginapanDao;
import com.example.project2.dao.UserDao;
import com.example.project2.dao.WisataDao;
import com.example.project2.models.Belanja;
import com.example.project2.models.Makanan;
import com.example.project2.models.OlehOleh;
import com.example.project2.models.Pendidikan;
import com.example.project2.models.Penginapan;
import com.example.project2.models.User;
import com.example.project2.models.Wisata;

// Class ini adalah class yang bertugas mengatur segala urusan dengan database yang ada pada aplikasi kita.
@Database(entities = {User.class, Belanja.class, Makanan.class, OlehOleh.class, Pendidikan.class, Penginapan.class, Wisata.class},
	version = 1, exportSchema = false)

public abstract class DTSAppDatabase extends RoomDatabase
{
	public abstract UserDao userDao();
	public abstract WisataDao wisataDao();
	public abstract PendidikanDao pendidikanDao();
	public abstract MakananDao makananDao();
	public abstract PenginapanDao penginapanDao();
	public abstract OlehOlehDao olehOlehDao();
}
