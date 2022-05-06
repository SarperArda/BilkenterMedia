package com.thenameless.bilkenter_media;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.thenameless.bilkenter_media.databinding.ActivityHomeBinding;
import com.thenameless.bilkenter_media.databinding.ActivityProfileBinding;

public class Profile extends AppCompatActivity {

    TextView fullName, email, birthday;
    String name, surname;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        email = findViewById(R.id.email);
        birthday = findViewById(R.id.birthdayDate);
        fullName = findViewById(R.id.fullName);
        //name = findViewById(R.id.Username);
        //surname = findViewById(R.id.UserSurname);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("Users Database").document(userID);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                email.setText(value.getString("E-mail"));
                birthday.setText(value.getString("Birthday"));
                name = value.getString("Name");
                surname = value.getString("Surname");
                fullName.setText(name + " " + surname);

                //String firstName = value.getString(String.valueOf(name));
                //String secondName = value.getString(String.valueOf(surname));
                //fullName.setText(firstName + secondName);
                //birthday.setText(value.getString("Birthday"));
            }
        });
    }

    public void goToSetting(View view){
        Intent intent = new Intent(Profile.this,Settings.class);
        startActivity(intent);
        finish();
    }
    public void goToBlogs(View view){
        Intent intent = new Intent(Profile.this, Blog.class);
        startActivity(intent);
        finish();
    }
    public void goToEditProfile(View view){
        Intent intent = new Intent(Profile.this, EditProfile.class);
        startActivity(intent);
        finish();
    }
}