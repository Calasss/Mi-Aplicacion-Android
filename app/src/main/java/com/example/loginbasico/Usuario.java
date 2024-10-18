package com.example.loginbasico;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class Usuario extends AppCompatActivity {
    //Declaramos varaible ProgressBar
    private ProgressBar progressBar;
    private LinearLayout contentLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_usuario);
        progressBar = findViewById(R.id.progressBar);
        contentLayout = findViewById(R.id.contentLayout);

        // Ocultamos el contenido principal al inicio
        contentLayout.setVisibility(View.GONE);

        // Simula una carga al iniciar la actividad
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

