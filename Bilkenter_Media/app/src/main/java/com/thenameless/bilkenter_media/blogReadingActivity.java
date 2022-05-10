package com.thenameless.bilkenter_media;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.thenameless.bilkenter_media.databinding.ActivityBlogReadingBinding;
import com.thenameless.bilkenter_media.databinding.ActivityBlogsBinding;
import com.thenameless.bilkenter_media.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class blogReadingActivity extends AppCompatActivity {

    private Intent intent;
    private ActivityBlogReadingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        binding = ActivityBlogReadingBinding.inflate(getLayoutInflater());
        binding.username.setText(intent.getStringExtra("userName"));
        binding.userBlog.setText(intent.getStringExtra("blogName"));
        View view = binding.getRoot();
        setContentView(view);
    }


}