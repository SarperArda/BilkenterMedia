package com.thenameless.bilkenter_media.PlacePart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.thenameless.bilkenter_media.PlacePart.Comment;
import com.thenameless.bilkenter_media.PlacePart.Place;
import com.thenameless.bilkenter_media.PlacePart.PlaceAdapter;
import com.thenameless.bilkenter_media.PlacePart.Rank;
import com.thenameless.bilkenter_media.R;
import com.thenameless.bilkenter_media.databinding.ActivityPlacesBinding;

import java.util.ArrayList;

public class Places extends AppCompatActivity {
    private ActivityPlacesBinding binding;
    ArrayList<Comment> comments;
    ArrayList<Rank> ranks;
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
        Place mozart = new Place("Mozart","10:30","22:00",ranks,comments, R.drawable.mozart );
        places.add(mozart);
        binding.placesRecycler.setLayoutManager(new LinearLayoutManager(this));
        PlaceAdapter placeAdapter = new PlaceAdapter(places);
        binding.placesRecycler.setAdapter(placeAdapter);
    }
}