package com.thenameless.bilkenter_media.PlacePart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.thenameless.bilkenter_media.databinding.ActivityBlogReadingBinding;
import com.thenameless.bilkenter_media.databinding.ActivityPlacedetailsBinding;

import java.util.ArrayList;
import java.util.Map;

public class placedetails extends AppCompatActivity {
    ActivityPlacedetailsBinding binding;
    ArrayList<Comment> comments;
    private FirebaseFirestore firebaseFirestore;
    Intent intent;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlacedetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        comments = new ArrayList<Comment>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        intent = getIntent();
        binding.photo.setImageResource(intent.getIntExtra("photo", 0));
        binding.placename.setText(intent.getStringExtra("placename"));
        name = intent.getStringExtra("placename");
        binding.opennigTime.setText(intent.getStringExtra("openingTime"));
        binding.closedTime.setText(intent.getStringExtra("closingTime"));
        /**
        getData();
        float rank = (float) getRankAverage(comments);
        binding.rank.setText(""+ rank+ "");
         */

    }
    public void goToComments(View view){
        Intent intent = new Intent(placedetails.this,readComment.class);
        intent.putExtra("name",name);
        startActivity(intent);
    }
    public void goToCommentAdd(View view){
        Intent intent = new Intent(placedetails.this,CommentAdd.class);
        intent.putExtra("name",name);
        startActivity(intent);
    }

    private void getData() {
        firebaseFirestore.collection(name).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(placedetails.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                if (value != null) {
                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        Map<String, Object> data = snapshot.getData();
                        String comment = (String) data.get("comment");
                        float rank = (float) data.get("rank");
                        Comment commentAdd = new Comment(comment,rank);
                        comments.add(commentAdd);
                    }
                }
            }
        });
    }

    private float getRankAverage(ArrayList<Comment> arr){
        float sum = 0;
        for(Comment rank : arr) {
            sum = sum + rank.rank;
        }
        return sum / arr.size();
        }
    }
