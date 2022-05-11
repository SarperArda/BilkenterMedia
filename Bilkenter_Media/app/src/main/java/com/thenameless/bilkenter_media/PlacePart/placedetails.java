package com.thenameless.bilkenter_media.PlacePart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.thenameless.bilkenter_media.Maps;
import com.thenameless.bilkenter_media.databinding.ActivityBlogReadingBinding;
import com.thenameless.bilkenter_media.databinding.ActivityPlacedetailsBinding;

import java.util.ArrayList;
import java.util.Map;

public class placedetails extends AppCompatActivity {
    ActivityPlacedetailsBinding binding;
    ArrayList<Comment> comments;
    Intent intent;
    String name;
    double enlem;
    double boylam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlacedetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        comments = new ArrayList<Comment>();
        intent = getIntent();
        binding.photo.setImageResource(intent.getIntExtra("photo", 0));
        binding.placename.setText(intent.getStringExtra("placename"));
        name = intent.getStringExtra("placename");
        enlem = intent.getDoubleExtra("enlem",0);
        boylam = intent.getDoubleExtra("boylam",0);
        binding.opennigTime.setText(intent.getStringExtra("openingTime"));
        binding.closedTime.setText(intent.getStringExtra("closingTime"));


    }
    public void goToComments(View view){
        Intent intent = new Intent(placedetails.this,readComment.class);
        intent.putExtra("name",name);
        startActivity(intent);
    }
    public void goToCommentAdd(View view){
        Intent intent = new Intent(placedetails.this,CommentAdd.class);
        intent.putExtra("name",name);
        startActivity(intent);
    }
    public void goToMap(View view){
        Intent intent = new Intent(placedetails.this, Maps.class);
        intent.putExtra("enlem",enlem);
        intent.putExtra("boylam", boylam);
        startActivity(intent);
    }

    }
