package com.example.plmakal2.myapplication100;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng abrm = new LatLng(52.143162, 21.039356);
        mMap.addMarker(new MarkerOptions().position(abrm).title("Akademia Badmitnona Robert Mateusiak"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(abrm));

        LatLng powerbad = new LatLng(52.072745, 20.695736);
        mMap.addMarker(new MarkerOptions().position(powerbad).title("Powerbad"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(powerbad));

        LatLng paczek = new LatLng(52.242015, 20.947648);
        mMap.addMarker(new MarkerOptions().position(paczek).title("Pączek Badmitnon School"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(paczek));

        LatLng wacha = new LatLng(52.157745, 21.069501);
        mMap.addMarker(new MarkerOptions().position(wacha).title("Wacha Badmitnon Coach"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(wacha));

        LatLng popieluszki5 = new LatLng(52.264515, 20.976283);
        mMap.addMarker(new MarkerOptions().position(popieluszki5).title("Akademia Badmintona"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(popieluszki5));

        LatLng zawodzie26 = new LatLng(52.185689, 21.079563);
        mMap.addMarker(new MarkerOptions().position(zawodzie26).title("Akademia Badmintona, " +
                "Kahuna"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(zawodzie26));

        LatLng pulawska17 = new LatLng(52.210042, 21.022350);
        mMap.addMarker(new MarkerOptions().position(pulawska17).title("Akademia Badmintona, " +
                "Calypso"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pulawska17));

        LatLng skarzynskiego8 = new LatLng(52.207786, 20.971287);
        mMap.addMarker(new MarkerOptions().position(skarzynskiego8).title("Badmitnomania"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(skarzynskiego8));

        LatLng siemienskiego6 = new LatLng(52.206426, 20.975430);
        mMap.addMarker(new MarkerOptions().position(siemienskiego6).title("Badmintomania"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(siemienskiego6));

        LatLng solec71 = new LatLng(52.233065, 21.036375);
        mMap.addMarker(new MarkerOptions().position(solec71).title("Akademia Badmintona, " +
                "Badmintomania"));
        // mMap.addMarker(new MarkerOptions().position(solec71).title("Badmintomania"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(solec71));

    }
}
