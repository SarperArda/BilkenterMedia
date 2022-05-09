package com.thenameless.bilkenter_media;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.thenameless.bilkenter_media.databinding.ActivityBlogsBinding;

import java.util.ArrayList;

public class Blogs extends AppCompatActivity {

    private ActivityBlogsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBlogsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ArrayList<Blog> list = new ArrayList<Blog>();
        Blog blog1 = new Blog("ercan");
        list.add(blog1);
        binding.recylerView.setLayoutManager(new LinearLayoutManager(this));
        BlogAdapter adapt = new BlogAdapter(list);
        binding.recylerView.setAdapter(adapt);
    }
}                                                           