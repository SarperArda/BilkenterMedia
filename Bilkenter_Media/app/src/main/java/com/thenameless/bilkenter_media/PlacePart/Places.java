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
        Place mozart = new Place("mozart","08:00","18:45", R.drawable.mozart );
        Place fameo = new Place("fameo","08:00","18:00", R.drawable.fameo );
        Place coffee_break = new Place("coffee_break","08:00","17:30", R.drawable.coffeebreak );
        Place yemekhane = new Place("yemekhane","08:00","20:00", R.drawable.yemekhane );
        Place speed = new Place("speed","07:30","21:30", R.drawable.speed );
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