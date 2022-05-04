package com.thenameless.bilkenter_media;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.thenameless.bilkenter_media.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();

        /**
         * After Home Screen is designed, this one will activate bcs this code checking current user and support no more effort to sign in again
         *
         */

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            Intent intent = new Intent(MainActivity.this,Home.class);
            startActivity(intent);
            finish();
        }

    }


    public void signIn(View view){
        String email = binding.email.getText().toString();
        String password = binding.password.getText().toString();
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show(); //Show Message if failure exists
        }else {
            mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,Home.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show(); //Understandable Message if failure exists
                }
            });
        }
    }
    public void signUp(View view){
        Intent intent = new Intent(MainActivity.this,SignUp.class);
        startActivity(intent);

    }
}