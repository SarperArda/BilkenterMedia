package com.thenameless.bilkenter_media;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.thenameless.bilkenter_media.databinding.ActivityPlacesBinding;
import com.thenameless.bilkenter_media.databinding.ActivityProfileBinding;

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
        ranks = new ArrayList<Rank>();
        comments = new ArrayList<Comment>();
        Place mozart = new Place("Mozart","10:30","22:00",ranks,comments);
        places.add(mozart);
        binding.placesRecycler.setLayoutManager(new LinearLayoutManager(this));
        PlaceAdapter placeAdapter = new PlaceAdapter(places);
        binding.placesRecycler.setAdapter(placeAdapter);
    }
}