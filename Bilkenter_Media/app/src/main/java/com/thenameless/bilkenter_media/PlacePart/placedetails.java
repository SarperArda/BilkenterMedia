package com.thenameless.bilkenter_media.PlacePart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.thenameless.bilkenter_media.databinding.ActivityBlogReadingBinding;
import com.thenameless.bilkenter_media.databinding.ActivityPlacedetailsBinding;

public class placedetails extends AppCompatActivity {
    ActivityPlacedetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlacedetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        binding.photo.setImageResource(intent.getIntExtra("photo", 0));
        binding.placename.setText(intent.getStringExtra("placename"));
    }
    public void goToComments(View view){
        Intent intent = new Intent(placedetails.this,CommentAdd.class);
        startActivity(intent);
    }
    public void goToCommentAdd(View view){
        Intent intent = new Intent(placedetails.this,CommentAdd.class);
        startActivity(intent);
    }
}