package com.example.comemierdas.galeria;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comemierdas.BD.Archivo;
import com.example.comemierdas.R;

import java.util.List;


public class Adaptador extends RecyclerView.Adapter<Adaptador.ArchivosViewHolder> implements View.OnClickListener {


    private List<Archivo> archivoList;
    private View.OnClickListener listener;

    public Adaptador(List<Archivo> archivoList) {
        this.archivoList = archivoList;
    }


    @NonNull
    @Override
    public ArchivosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        //contruimos ViewHolder para el return
        Context context = parent.getContext();
        int layoutId = R.layout.row_foto;
        LayoutInflater infater = LayoutInflater.from(context);
        boolean velocidad = false;
        View view = infater.inflate(layoutId, parent, velocidad);

        // evento
        view.setOnClickListener(this);


        return new ArchivosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArchivosViewHolder holder, int position) {

        holder.asignarDatos(archivoList.get(position));

        //  String texto = pedidoList.get(position).producto + " " + pedidoList.get(position).categoria;
        //   holder.textViewPedidos.setText(texto);

    }

    @Override
    public int getItemCount() {
        return archivoList.size();
    }


    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }


    public void evento(View.OnClickListener listener) {
        this.listener = listener;
    }


    public class ArchivosViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewArchivos;

        public ArchivosViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewArchivos = itemView.findViewById(R.id.imageView);
        }


        void asignarDatos(Archivo archivo) {

            Uri uriImage = Uri.parse(archivo.path);
            //Uri uriImagen = Uri.parse("R.drawable.mi_imagen");
            imageViewArchivos.setImageURI(uriImage);

        }


    }


}