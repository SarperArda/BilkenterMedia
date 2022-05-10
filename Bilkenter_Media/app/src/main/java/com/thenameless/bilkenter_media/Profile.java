package com.thenameless.bilkenter_media;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Profile extends AppCompatActivity {

    TextView fullName, email, birthday;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String name, surname;
    String userID;
    ImageView profilePicture;
    Button resetPassword, deleteAccount, goEditProfile;
    FirebaseUser user;
    StorageReference fStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        email = findViewById(R.id.email);
        birthday = findViewById(R.id.birthdayDate);
        fullName = findViewById(R.id.fullName);
        profilePicture = findViewById(R.id.profilePicture);

        deleteAccount = findViewById(R.id.deleteAccount);

        resetPassword = findViewById(R.id.reset);

        goEditProfile = findViewById(R.id.goEditProfile);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        fStorage = FirebaseStorage.getInstance().getReference();

        userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        user = mAuth.getCurrentUser();


        StorageReference profileRef = fStorage.child("Users Database/"+mAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilePicture);
            }
        });


        DocumentReference documentReference = fStore.collection("Users Database").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onEvent(DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value != null){
                    if (!value.exists()) {
                        Log.d("tag", "Document does not exists.");
                    } else {
                        email.setText(value.getString("E-mail"));
                        birthday.setText(value.getString("Birthday"));
                        name = value.getString("Name");
                        surname = value.getString("Surname");
                        fullName.setText(name + " " + surname);
                    }
                }

            }
        });

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
                            Toast.makeText(Profile.this, "Password Reset Successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Profile.this, "Password Reset Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                });

                resetDialog.setNegativeButton("Cancel", (dialog, which) ->{
                });
                resetDialog.create().show();
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Profile.this);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Deleting this account will result in completely removing your account.");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Profile.this, "Account Deleted", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Profile.this, SignUp.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(Profile.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });

        goEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opening the gallery
                Intent i = new Intent(v.getContext(), EditProfile.class);
                i.putExtra("Name", name);//name
                i.putExtra("Surname", surname);//surname
                i.putExtra("birthday", birthday.getText().toString());//birthday.getText().toString()
                i.putExtra("e-mail", email.getText().toString());//email.getText().toString()
                startActivity(i);
            }
        });
    }
    public void logOut(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
    public void goToMenu(View view){
        Intent intent = new Intent(Profile.this, Home.class);
        startActivity(intent);
        finish();
    }

}