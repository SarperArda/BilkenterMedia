package com.thenameless.bilkenter_media.PlacePart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import com.thenameless.bilkenter_media.databinding.ActivityReadCommentBinding;

import java.util.ArrayList;

public class readComment extends AppCompatActivity {
    private ActivityReadCommentBinding binding;
    ArrayList<Comment> comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadCommentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // data
        comments = new ArrayList<Comment>();
        comments.add(new Comment("good", "Ercan"));
        CommentReadAdapter readAdapter = new CommentReadAdapter(comments);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(readAdapter);

    }
}