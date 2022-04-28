package com.thenameless.bilkenter_media;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.thenameless.bilkenter_media.databinding.ActivityMapsBinding;

import java.util.ArrayList;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    ActivityResultLauncher<String> permissionLauncher;
    LocationManager user;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        registerLaunch();
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
        user = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                LatLng userCurrent = new LatLng(location.getLatitude(),location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userCurrent,18));
            }
        };
        //Request Location from user
        if(ContextCompat.checkSelfPermission(Maps.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(Maps.this,Manifest.permission.ACCESS_FINE_LOCATION)){
                Snackbar.make(binding.getRoot(),"Location Permision is Needed!",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission for Maps", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
                    }
                });
            }else{
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            }
        }else{
            user.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,50,locationListener);
        }
        markPlaces();

    }

    public void registerLaunch(){
        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if(result){
                    if(ContextCompat.checkSelfPermission(Maps.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        user.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,50,locationListener);
                    }

                }
                else{
                    Toast.makeText(Maps.this, "Location Permision is Needed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //Türker will continue this method.

    public void markPlaces(){

        ArrayList<LatLng> places = new ArrayList<>();
        LatLng yemekhane = new LatLng(39.8705899,32.7506622);
        places.add(yemekhane);
        LatLng cafeIn1 = new LatLng(39.8699283,32.7505348);
        places.add(cafeIn1);
        // created
        // cafe2

        for (int i = 0; i < places.size(); i++) {
            mMap.addMarker(new MarkerOptions().position(places.get(i)));
        }
    }

    

}