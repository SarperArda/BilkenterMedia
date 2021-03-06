package com.thenameless.bilkenter_media.PlacePart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.thenameless.bilkenter_media.databinding.ActivityReadCommentBinding;

import java.util.ArrayList;
import java.util.Map;

public class readComment extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    Intent intent;
    private ActivityReadCommentBinding binding;
    ArrayList<Comment> comments;
    CommentReadAdapter readAdapter;
    String placename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadCommentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        comments = new ArrayList<Comment>();
        intent = getIntent();
        placename = intent.getStringExtra("name");
        firebaseFirestore = FirebaseFirestore.getInstance();


        // data

        getData();
        readAdapter = new CommentReadAdapter(comments);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(readAdapter);


    }
      private void getData() {
        firebaseFirestore.collection(placename).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(readComment.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                if (value != null) {
                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        Map<String, Object> data = snapshot.getData();
                        String comment = (String) data.get("comment");
                        Comment commentAdd = new Comment(comment);
                        comments.add(commentAdd);
                    }
                    readAdapter.notifyDataSetChanged();
                }
            }
        });
        }

    }