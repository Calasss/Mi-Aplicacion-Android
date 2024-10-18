package com.example.loginbasico;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Bundle; // Importa la clase Bundle para guardar el estado de la actividad.
import android.preference.PreferenceManager; // Importa PreferenceManager para gestionar las preferencias de la aplicación.
import android.view.View; // Importa la clase View para manejar vistas en la interfaz.
import android.widget.AdapterView; // Importa AdapterView para manejar eventos en vistas adaptables.
import android.widget.ArrayAdapter; // Importa ArrayAdapter para adaptar arrays a vistas como Spinners.
import android.widget.ProgressBar;
import android.widget.Spinner; // Importa Spinner, que es una lista desplegable en Android.
import org.osmdroid.config.Configuration; // Importa la clase Configuration para configurar el mapa.
import org.osmdroid.tileprovider.tilesource.TileSourceFactory; // Importa TileSourceFactory para definir los tipos de mapas disponibles.
import org.osmdroid.tileprovider.tilesource.XYTileSource; // Importa XYTileSource para crear un proveedor de azulejos específico para mapas personalizados.
import org.osmdroid.util.GeoPoint; // Importa GeoPoint, que representa coordenadas geográficas (latitud y longitud).
import org.osmdroid.views.MapView; // Importa MapView, que es el componente visual del mapa.
import org.osmdroid.views.overlay.Marker; // Importa Marker para agregar marcadores en el mapa.
import org.osmdroid.views.overlay.Polyline; // Importa Polyline para dibujar líneas en el mapa.

import androidx.appcompat.app.AppCompatActivity;

public class Mapa extends AppCompatActivity {
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);

        //Configuracion para el mapa usando las referencias predeterminadas
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        //Obtener la referencia al componente MapView del layout
        MapView mapView = findViewById(R.id.mapView);
        //Fuente de azulejos del mapa a MAPNIK(mapa estandar)
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        //Activa los controles del zoom en el mapa
        mapView.setBuiltInZoomControls(true);
        //Activar el control multitactil para el mapa
        mapView.setMultiTouchControls(true);
        //Coordenadas seleccionadas
        double Art1Latitud = 35.6762; //Latitud del Artista 1
        double Art1Longitud = 139.6503; //Latitud del Artista 1
        //Crear objeto GeoPoint para las coordenadas definidas
        GeoPoint Artista1Point = new GeoPoint(Art1Latitud, Art1Longitud);
        //Configurar la vista inicial del mapa centrada en el Artista 1 con zoom de 15
        mapView.getController().setZoom(15.0);
        mapView.getController().setCenter(Artista1Point);

        //Crear un marcador para el Artista 1 y crear un marcador en el mapa
        Marker marcadorArtista1 = new Marker(mapView);
        //Establecer la posicion del marcador en el punto
        marcadorArtista1.setPosition(Artista1Point);
        //Establecer el ancla del marcador
        marcadorArtista1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        //Establecer el titulo del marcador
        marcadorArtista1.setTitle("Hayao Miyazaki, Cofundador Studio Ghibli");
        //Establecer una descripcion del marcador
        marcadorArtista1.setSnippet("Tokio, Japón");
        //Agregar los marcadores al mapa
        mapView.getOverlays().add(marcadorArtista1);


        //Coordenadas seleccionadas
        double Art2Latitud = 35.9096; //Latitud del Artista 2
        double Art2Longitud = 138.1226; //Latitud del Artista 2
        //Crear objeto GeoPoint para las coordenadas definidas
        GeoPoint Artista2Point = new GeoPoint(Art2Latitud, Art2Longitud);

        //Crear un marcador para el Artista 2 y crear un marcador en el mapa
        Marker marcadorArtista2 = new Marker(mapView);
        //Establecer la posicion del marcador en el punto
        marcadorArtista2.setPosition(Artista2Point);
        //Establecer el ancla del marcador
        marcadorArtista2.setAnchor(0.2f, 0.4f);
        marcadorArtista2.setInfoWindowAnchor(0.2f, 0.0f);
        //Establecer el titulo del marcador
        marcadorArtista2.setTitle("Makoto Shinkai,");
        //Establecer una descripcion del marcador
        marcadorArtista2.setSnippet("Koumi, Japón");
        //Cambiar el icono al marcador
        marcadorArtista2.setIcon(getResources().getDrawable(R.drawable.artista));
        //Agregar los marcadores al mapa
        mapView.getOverlays().add(marcadorArtista2);

        //Crear linea para conectar los marcadores
        Polyline linea = new Polyline();
        //Añadir punto del Artista 1 y Artista 2
        linea.addPoint(Artista1Point);
        linea.addPoint(Artista2Point);
        //Cambiar el color de la linea
        linea.setColor(0xFF0000FF);
        //Establecer el ancho de la linea por pixeles
        linea.setWidth(5);
        //Añadir la lina al mapa
        mapView.getOverlayManager().add(linea);

        //Configurar el spinner para cambiar el tipo de mapa y obtener la referencia al componente Spinner del id del xml
        Spinner mapTypeSpinner = findViewById(R.id.mapTypeSpinner);
        //Definir un array con los tipos de mapas
        String[] mapTypes = {"Mapa Normal", "Mapa de Transporte", "Mapa Topografico"};
        //Crear un ArrayAdapter para poblar el Spinner con los tipos de mapas
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mapTypes);
        //Asignar el adaptador al spinner
        mapTypeSpinner.setAdapter(adapter);

        //Listener para detectar cambios en la seleccion del spinner
        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mapView.setTileSource(TileSourceFactory.MAPNIK);
                        break;
                    case 1:
                        mapView.setTileSource(new XYTileSource(
                                "PublicTransport",
                                0, 18, 256, ".png", new String[]{
                                "https://tile.memomaps.de/tilegen/"}));
                        break;
                    case 2:
                        mapView.setTileSource(new XYTileSource(
                                "USGS_Satellite", 0 , 18 , 256 , ".png" , new String[]{
                                "https://a.tile.opentopomap.org/",
                                "https://b.tile.opentopomap.org/",
                                "https://c.tile.opentopomap.org/"}));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
