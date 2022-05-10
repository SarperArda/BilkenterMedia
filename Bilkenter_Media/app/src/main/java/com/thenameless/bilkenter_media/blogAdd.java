package com.thenameless.bilkenter_media;

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
import com.thenameless.bilkenter_media.databinding.ActivityBlogAddBinding;
import com.thenameless.bilkenter_media.databinding.ActivityPlacesBinding;

import org.w3c.dom.Document;

import java.util.HashMap;

public class blogAdd extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityBlogAddBinding binding;
    private FirebaseFirestore firebaseFirestore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBlogAddBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void saveBlog(View view){
        String blog = binding.blogbyuser.getText().toString();
        String blogName = binding.blogName.getText().toString();
        FirebaseUser userCurrent = mAuth.getCurrentUser();
        userID = userCurrent.getUid();
        DocumentReference docRef = firebaseFirestore.collection("blogs").document(userID);

        String user = userCurrent.getDisplayName();
        HashMap<String,Object> postBlog = new HashMap<>();
        postBlog.put("blogName",blogName);
        postBlog.put("blog",blog);
       // postBlog.put("user",user);
        postBlog.put("date", FieldValue.serverTimestamp());
        firebaseFirestore.collection("blog").add(postBlog).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Intent intent = new Intent(blogAdd.this,Blogs.class);
                intent.putExtra("name", new Profile().getName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(blogAdd.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}