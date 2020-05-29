package com.example.comemierdas.galeria;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import com.example.comemierdas.BD.Archivo;
import com.example.comemierdas.BD.BaseDatos;
import com.example.comemierdas.R;


public class ListaFotos extends AppCompatActivity {


    List<Archivo> archivoList;
    private RecyclerView recyclerViewFotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_fotos);
        getSupportActionBar().hide();


        BaseDatos db = Room.databaseBuilder(getApplicationContext(),
                BaseDatos.class, "ComeMierdas").allowMainThreadQueries().build(); //


        archivoList = db.Dao().selectArchivos();

        recyclerViewFotos = findViewById(R.id.rvFotos);

        recyclerViewFotos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewFotos.setLayoutManager(linearLayoutManager);


        recyclerViewFotos.offsetTopAndBottom(1);

        Adaptador recyclerAdapterPedidos = new Adaptador(archivoList);
        recyclerViewFotos.setAdapter(recyclerAdapterPedidos);
    }


    private void refrescarGrid() {

        BaseDatos db = Room.databaseBuilder(getApplicationContext(),
                BaseDatos.class, "TiendaUd3").allowMainThreadQueries().build(); //

        //refresxamos el recylerView
        archivoList = db.Dao().selectArchivos();
        Adaptador recyclerAdapterPedidos = new Adaptador(archivoList);
        recyclerViewFotos.setAdapter(recyclerAdapterPedidos);
    }

}
