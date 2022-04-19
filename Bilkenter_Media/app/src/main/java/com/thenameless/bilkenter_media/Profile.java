package com.thenameless.bilkenter_media;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thenameless.bilkenter_media.databinding.ActivityHomeBinding;
import com.thenameless.bilkenter_media.databinding.ActivityProfileBinding;

public class Profile extends AppCompatActivity {
    private ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void goToSetting(View view){
        Intent intent = new Intent(Profile.this,Settings.class);
        startActivity(intent);
        finish();
    }
}