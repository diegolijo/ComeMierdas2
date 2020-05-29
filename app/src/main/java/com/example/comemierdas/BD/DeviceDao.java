package com.example.comemierdas.BD;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DeviceDao {

    @Query("SELECT * FROM archivo")
    List<Archivo> selectArchivos();

    @Query("SELECT COUNT(*) FROM archivo")
    int countArchivos();

//    @Query("SELECT * FROM archivo WHERE path LIKE :usuario  LIMIT 1")
//    Usuario selectUsuario(String usuario);
//
//    @Query("SELECT * FROM pedido WHERE usuario = :usuario AND estado = :estado")
//    List<Pedido> selectPedidosEstado(String usuario, String estado);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertArchivo(Archivo archivo);

    @Insert
    void insertUsersAndFriends( List<Archivo> archivos);

    @Delete
    void delete(Archivo archivo);

}