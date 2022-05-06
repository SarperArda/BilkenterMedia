package com.thenameless.bilkenter_media;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class EditProfile extends AppCompatActivity {

    Button changePicture, resetPassword, goBackToProfile, saveProfile;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;
    FirebaseUser user;
    ImageView profileImage;
    StorageReference fStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        resetPassword = findViewById(R.id.reset);

        profileImage = findViewById(R.id.profileImage);
        changePicture = findViewById(R.id.changePicture);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        fStorage = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = fStorage.child("Users Database/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });

        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetPassword = new EditText(v.getContext());
                AlertDialog.Builder resetDialog = new AlertDialog.Builder(v.getContext());
                resetDialog.setTitle("Reset Password?");
                resetDialog.setMessage("Enter New Password");
                resetDialog.setView(resetPassword);

                resetDialog.setPositiveButton("Done", (dialog, which)  -> {
                    String newPassword = resetPassword.getText().toString();
                    user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(EditProfile.this, "Password Reset Successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditProfile.this, "Password Reset Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                });

                resetDialog.setNegativeButton("Cancel", (dialog, which) ->{
                });
                resetDialog.create().show();
            }
        });

        changePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opening the gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                //profileImage.setImageURI(imageUri);
                uploadImage(imageUri);
            }
        }
    }

    private void uploadImage(Uri imageUri){
        StorageReference ref = fStorage.child("Users Database/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImage);
                    }
                });
                Toast.makeText(EditProfile.this, "Image Uploaded!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfile.this, "Upload Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goBackToProfile(View view){
        Intent intent = new Intent(EditProfile.this, Profile.class);
        startActivity(intent);
        finish();
    }
}