package com.thenameless.bilkenter_media;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.thenameless.bilkenter_media.databinding.ActivityPlacesBinding;
import com.thenameless.bilkenter_media.databinding.ActivityProfileBinding;

public class Places extends AppCompatActivity {
    private ActivityPlacesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlacesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}