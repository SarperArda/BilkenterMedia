package com.thenameless.bilkenter_media;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thenameless.bilkenter_media.BlogPart.Blogs;
import com.thenameless.bilkenter_media.BlogPart.blogAdd;
import com.thenameless.bilkenter_media.PlacePart.CommentAdd;
import com.thenameless.bilkenter_media.PlacePart.Places;
import com.thenameless.bilkenter_media.ProfilePart.Profile;
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
        Intent intent = new Intent(Home.this, Places.class);
        startActivity(intent);

    }
    public void goToMap(View view){
        Intent intent = new Intent(Home.this,Maps.class);
        startActivity(intent);

    }
    public void goToProfile(View view){
        Intent intent = new Intent(Home.this, Profile.class);
        startActivity(intent);

    }
    public void goToBlog(View view){
        Intent intent = new Intent(Home.this, Blogs.class);
        startActivity(intent);

    }
    public void goToInfo(View view){
        Intent intent = new Intent(Home.this,Info.class);
        startActivity(intent);

    }
    public void addBlog(View view){
        Intent intent = new Intent(Home.this, blogAdd.class);
        startActivity(intent);

    }
    public void goc(View view){
        Intent intent = new Intent(Home.this, CommentAdd.class);
        startActivity(intent);

    }
}