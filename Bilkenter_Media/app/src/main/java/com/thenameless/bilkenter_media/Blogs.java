package com.thenameless.bilkenter_media;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.thenameless.bilkenter_media.databinding.ActivityBlogsBinding;

import java.util.ArrayList;
import java.util.Map;

public class Blogs extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<Blog> blogArr;
    BlogAdapter adapt;

    private ActivityBlogsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBlogsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        blogArr = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        getData();

        binding.recylerView.setLayoutManager(new LinearLayoutManager(this));
        adapt = new BlogAdapter(blogArr);
        binding.recylerView.setAdapter(adapt);

    }
    private void getData(){
        firebaseFirestore.collection("blog").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent( QuerySnapshot value,  FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(Blogs.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                if(value != null){
                    for (DocumentSnapshot snapshot: value.getDocuments()){
                        Map<String,Object> data = snapshot.getData();
                        String blogName = (String) data.get("blogName");
                        String user = (String) data.get("user");
                        String blog = (String) data.get("blog");
                        //FieldValue date = (FieldValue) data.get("date");
                        Blog blogAdd = new Blog(blogName,user,blog);
                        blogArr.add(blogAdd);
                    }
                    adapt.notifyDataSetChanged();
                }
            }
        });
    }
}                                                           