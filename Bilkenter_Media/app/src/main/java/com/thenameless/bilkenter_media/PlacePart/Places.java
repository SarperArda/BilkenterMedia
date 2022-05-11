package com.thenameless.bilkenter_media.PlacePart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

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
        Place mozart = new Place("mozart","10:30","22:00", R.drawable.mozart );
        Place fameo = new Place("fameo","10:30","22:00", R.drawable.mozart );
        places.add(mozart);
        places.add(fameo);
        binding.placesRecycler.setLayoutManager(new LinearLayoutManager(this));
        PlaceAdapter placeAdapter = new PlaceAdapter(places);
        binding.placesRecycler.setAdapter(placeAdapter);
    }
}