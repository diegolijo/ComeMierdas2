package com.example.comemierdas.BD;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Archivo.class}, version = 1, exportSchema = false)
public abstract class BaseDatos extends RoomDatabase {
    public abstract DeviceDao Dao();
}
