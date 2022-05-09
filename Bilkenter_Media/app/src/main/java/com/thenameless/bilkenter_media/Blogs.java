package com.thenameless.bilkenter_media;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.thenameless.bilkenter_media.databinding.ActivityBlogBinding;

public class Blog extends AppCompatActivity {

    private ActivityBlogBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBlogBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}                                                           