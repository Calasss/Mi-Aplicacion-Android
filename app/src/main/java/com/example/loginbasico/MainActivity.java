package com.example.loginbasico;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Declaramos atributos
    private EditText usuarioEditText;
    private EditText contrasenaEditText;
    private Spinner spinner;
    private ProgressBar progressBar;
    private LinearLayout contentLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazo los atributos con los ids de la vista main
        usuarioEditText = findViewById(R.id.usuario);
        contrasenaEditText = findViewById(R.id.contrasena);
        spinner = findViewById(R.id.spinnerRoles);
        progressBar = findViewById(R.id.progressBar);
        contentLayout = findViewById(R.id.contentLayout);

        // Ocultamos el contenido principal al inicio
        contentLayout.setVisibility(View.GONE);

        // Creo un Arreglo con los elementos del Spinner
        String[] roles = {"Administrador", "Usuario"};
        // Creamos un ArrayAdapter para poblar el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // Asigno el ArrayAdapter al Spinner
        spinner.setAdapter(adapter);

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

    // Programamos un método onClick para ir a otra vista
    // Dependiendo de un usuario específico
    public void onClickAcceder(View view) {
        // Declaramos variables para obtener los valores ingresados
        // Por el usuario
        String user = usuarioEditText.getText().toString().trim();
        String pass = contrasenaEditText.getText().toString().trim();
        String rol = spinner.getSelectedItem().toString();

        // Si el campo usuario está vacío salta una alerta
        if (user.isEmpty()) {
            Toast.makeText(this, "Ingrese el Usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifico las credenciales y dependiendo del usuario
        // Me llevará a diferentes vistas o actividades
        if (user.equals("user") && pass.equals("1234") && rol.equals("Usuario")) {
            Intent intent = new Intent(this, Usuario.class);
            startActivity(intent);
        } else if (user.equals("admin") && pass.equals("123") && rol.equals("Administrador")) {
            Intent intent = new Intent(this, Admin.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickCrear(View view){
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }

    public void onClickCerrar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
