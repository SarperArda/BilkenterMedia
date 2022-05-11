package com.thenameless.bilkenter_media.PlacePart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.thenameless.bilkenter_media.BlogPart.Blogs;
import com.thenameless.bilkenter_media.BlogPart.blogAdd;
import com.thenameless.bilkenter_media.databinding.ActivityBlogAddBinding;
import com.thenameless.bilkenter_media.databinding.ActivityCommentAddBinding;

import java.util.HashMap;

public class CommentAdd extends AppCompatActivity {
    private ActivityCommentAddBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    String placename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentAddBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        placename = intent.getStringExtra("name");
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void saveComment(View view){
        String comment = binding.commentByUser.getText().toString();
        float rank = binding.ratingBar.getNumStars();
        FirebaseUser userCurrent = mAuth.getCurrentUser();
        //String userName = userCurrent.getDisplayName();
        HashMap<String,Object> comments = new HashMap<>();
        comments.put("comment",comment);
        comments.put("rank",rank);
        //comments.put("date", FieldValue.serverTimestamp());
        firebaseFirestore.collection(placename).add(comments).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Intent intent = new Intent(CommentAdd.this, readComment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CommentAdd.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}