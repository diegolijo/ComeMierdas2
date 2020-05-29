package com.example.comemierdas;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.room.Room;

import com.example.comemierdas.BD.Archivo;
import com.example.comemierdas.BD.BaseDatos;

import java.io.File;


public class RecorrerCarpetas implements Runnable {
    private File path;
    private MainActivity activity;
    private int numCarpetas;
    private int numArchivos;
    private String textoLista;
    private String tipo;

    public RecorrerCarpetas(File path, MainActivity activity) {
        this.path = path;
        this.activity = activity;
        this.numArchivos = 0;
        this.numCarpetas = 0;
    }


    @Override
    public void run() {
        textoLista = "";
        numCarpetas = 0;
        numArchivos = 0;

        recorrerArchivos(path,tipo);
        setTexto();
    }


    private void recorrerArchivos(File path,String tipo) {

        BaseDatos db = Room.databaseBuilder(activity.getApplicationContext(),
                BaseDatos.class, "ComeMierdas").allowMainThreadQueries().build(); //
        File[] lista = path.listFiles();

        for (File file : lista) {

            if (file.isDirectory()) {
                numCarpetas += 1;
                recorrerArchivos(file,tipo);
            } else {
                numArchivos += 1;
                textoLista += file.getName() + "\n";

                //  file.getAbsolutePath()


                insertarArchivo(db, file);
            }
        }
        textoLista = "" + db.Dao().countArchivos();
    }

    private void insertarArchivo(BaseDatos db, File file) {

        Archivo reg = new Archivo();

        reg.path = file.getAbsolutePath();
        reg.nombre = file.getName();
        reg.revisado = false;
        reg.borrar = false;

        db.Dao().insertArchivo(reg);

    }


    private void setTexto() {
        TextView textView = activity.findViewById(R.id.textViewPath);
        String text = "Carpetas: " + numCarpetas + "  -  Archivos: " + numArchivos;
        textView.setText(text);

        TextView textView2 = activity.findViewById(R.id.textViewScroll);
        textView2.setMovementMethod(new ScrollingMovementMethod());
        textView2.setText(textoLista);

    }

}
