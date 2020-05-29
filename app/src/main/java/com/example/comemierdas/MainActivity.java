package com.example.comemierdas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.comemierdas.galeria.ListaFotos;

import java.io.File;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    public final String[] EXTERNAL_PERMS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    public final int EXTERNAL_REQUEST = 50;
    private String wasaPath = "/storage/self/primary/WhatsApp/Media/";
    private Button botonAudio;
    private Button botonFoto;
    private Button botonVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        botonAudio = findViewById(R.id.buttonAudio);
        botonFoto = findViewById(R.id.buttonFoto);
        botonVideo = findViewById(R.id.buttonVideo);
        desmarcarBotones();
    }


    //---------------------- botones ----------------------

    public void onClick(View v) {
        selectCarpeta();
    }

    public void onClickVideo(View v) {
        wasaPath = getString(R.string.CarpetaVideoWasap);
        desmarcarBotones();
        marcarBoton(botonVideo);
    }

    public void onClickFoto(View v) {
        wasaPath = getString(R.string.CarpetaFotoWasap);
        desmarcarBotones();
        marcarBoton(botonFoto);
    }

    public void onClickAudio(View v) {
        wasaPath = getString(R.string.CarpetaAudioWasap);
        desmarcarBotones();
        marcarBoton(botonAudio);
    }


    public void onClickIntent(View v) {
        Intent intent = new Intent(this, ListaFotos.class);
        startActivity(intent);
    }

    public void desmarcarBotones() {
        botonAudio.setBackgroundColor(0xFFE059);
        botonAudio.setTextColor(0xFF9B9B9B);
        botonFoto.setBackgroundColor(0xFFE059);
        botonFoto.setTextColor(0xFF9B9B9B);
        botonVideo.setBackgroundColor(0xFFE059);
        botonVideo.setTextColor(0xFF9B9B9B);
    }

    public void marcarBoton(Button boton) {
        boton.setBackgroundColor(0xFF9B9B9B);
        boton.setTextColor(0xFF252525);
    }


    public void selectCarpeta() {
        if (requestForPermission()) {
            File carpeta = new File(wasaPath);
            if (carpeta.exists() && carpeta.isDirectory()) {
                RecorrerCarpetas escaneo = new RecorrerCarpetas(carpeta, this);
                escaneo.run();
            }
        }
    }


    //----------------------------- permisos --------------------------------

    public boolean requestForPermission() {

        boolean isPermissionOn = true;
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            if (!canAccessExternalSd()) {
                isPermissionOn = false;
                requestPermissions(EXTERNAL_PERMS, EXTERNAL_REQUEST);
            }
        }
        return isPermissionOn;
    }

    public boolean canAccessExternalSd() {
        return (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE));
    }

    //-----------------------------------------------------------------------


}
