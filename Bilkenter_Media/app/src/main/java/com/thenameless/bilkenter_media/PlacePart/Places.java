package com.thenameless.bilkenter_media.PlacePart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.thenameless.bilkenter_media.R;
import com.thenameless.bilkenter_media.databinding.ActivityPlacesBinding;

import java.util.ArrayList;

public class Places extends AppCompatActivity {
    private ActivityPlacesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlacesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // data
        ArrayList<Place> places = new ArrayList<Place>();
        double yemekhaneEnlem = 39.8705899;
        double yemekhaneBoylam = 32.7506622;
        double mozartE = 39.8687489;
        double mozartB = 32.7479186;
        double speedE =  39.8663148;
        double speedB = 32.7461129;
        double coffeE = 39.8702701;
        double coffeB = 32.7494944;
        double fameoE = 39.868446;
        double fameoB = 32.763683;
        Place mozart = new Place("MOZART","08:00","18:45", R.drawable.mozart ,mozartE,mozartB);
        Place fameo = new Place("FAMEO","08:00","18:00", R.drawable.fameo,fameoE,fameoB );
        Place coffee_break = new Place("COFFEE BREAK","08:00","17:30", R.drawable.coffeebreak,coffeE,coffeB );
        Place yemekhane = new Place("CAFETERIA","08:00","20:00", R.drawable.yemekhane,yemekhaneEnlem,yemekhaneBoylam );
        Place speed = new Place("SPEED","07:30","21:30", R.drawable.speed,speedE,speedB );
        places.add(coffee_break);
        places.add(fameo);
        places.add(mozart);
        places.add(yemekhane);
        places.add(speed);
        binding.placesRecycler.setLayoutManager(new LinearLayoutManager(this));
        PlaceAdapter placeAdapter = new PlaceAdapter(places);
        binding.placesRecycler.setAdapter(placeAdapter);
    }
}