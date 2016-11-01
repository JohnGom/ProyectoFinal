package com.herprogramacion.proyectofinal.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.herprogramacion.proyectofinal.Adapter.FirstMapFragment;
import com.herprogramacion.proyectofinal.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FirstMapFragment mFirstMapFragment;

    String lati;
    String longi;
    String nombre;
    float lati1;
    float longi1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        lati = intent.getStringExtra("latitud");
        longi = intent.getStringExtra("longitud");
        nombre = intent.getStringExtra("nombre");

        lati1 = Float.parseFloat(lati);
        longi1 = Float.parseFloat(longi);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mFirstMapFragment = FirstMapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map_container, mFirstMapFragment)
                .commit();

        // Registrar escucha onMapReadyCallback
        mFirstMapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        LatLng lugar = new LatLng(lati1, longi1);
        googleMap.addMarker(new MarkerOptions()
                .position(lugar)
                .title(nombre));



        CameraPosition cameraPosition = CameraPosition.builder()
                .target(lugar)
                .zoom(15)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}