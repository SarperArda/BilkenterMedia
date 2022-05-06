package com.thenameless.bilkenter_media;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.thenameless.bilkenter_media.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity {
    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }

    public void goToPlaces(View view){
        Intent intent = new Intent(Home.this,Places.class);
        startActivity(intent);
        finish();
    }
    public void goToMap(View view){
        Intent intent = new Intent(Home.this,Maps.class);
        startActivity(intent);
        finish();
    }
    public void goToProfile(View view){
        Intent intent = new Intent(Home.this,Profile.class);
        startActivity(intent);
        finish();
    }
    public void goToBlog(View view){
        Intent intent = new Intent(Home.this,Blog.class);
        startActivity(intent);
        finish();
    }
    public void goToInfo(View view){
        Intent intent = new Intent(Home.this,Info.class);
        startActivity(intent);
        finish();
    }

}