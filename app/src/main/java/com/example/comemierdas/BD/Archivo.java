package com.example.comemierdas.BD;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Archivo {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "path")
    public String path;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "tipo")
    public String tipo;

    @ColumnInfo(name = "revisado")
    public boolean revisado;

    @ColumnInfo(name = "borrar")
    public boolean borrar;

}
