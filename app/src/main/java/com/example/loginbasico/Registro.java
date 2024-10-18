package com.example.loginbasico;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {

    // Declaramos las variables del ProgressBar y el layout principal
    private ProgressBar progressBar;
    private LinearLayout contentLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        // Referenciamos los elementos del layout
        progressBar = findViewById(R.id.progressBar);
        contentLayout = findViewById(R.id.contentLayout);

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
}

