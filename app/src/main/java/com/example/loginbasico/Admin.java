package com.example.loginbasico;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class Admin extends AppCompatActivity {

    private ProgressBar progressBar;
    private LinearLayout contentLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        progressBar = findViewById(R.id.progressBar);
        contentLayout = findViewById(R.id.contentLayout);
        contentLayout = findViewById(R.id.contentLayout1);

        // Ocultamos el contenido principal al inicio
        contentLayout.setVisibility(View.GONE);

        // Simulamos la carga
        simulateLoading();
    }

    // Método para simular la carga con un retraso de 3 segundos
    private void simulateLoading() {
        progressBar.setVisibility(View.VISIBLE); // Mostramos el ProgressBar

        // Usamos un Handler para retrasar la ejecución en el hilo principal
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Ocultamos el ProgressBar y mostramos el contenido
                progressBar.setVisibility(View.GONE);
                contentLayout.setVisibility(View.VISIBLE);
            }
        }, 3000); // Tiempo de espera: 3 segundos
    }

    // Método para cerrar sesión
    public void onClickCerrar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Método para ir al mapa
    public void irAlMapa(View view) {
        Intent intent = new Intent(this, Mapa.class);
        startActivity(intent);
    }
}


