package com.thenameless.bilkenter_media;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.thenameless.bilkenter_media.databinding.ActivitySignUpBinding;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    public static final String TAG = null;
    private ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;
    EditText mName, mSurname, mEmail, mBirthDay;
    String name, surname, mail, birthday;
    FirebaseFirestore fStore;
    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());

        setContentView(R.layout.activity_sign_up);
        mName = findViewById(R.id.Username);
        mSurname = findViewById(R.id.UserSurname);
        mEmail = findViewById(R.id.userMail);
        mBirthDay = findViewById(R.id.userBirthday);

        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }
    public void signUp(View view){
        String email = binding.userMail.getText().toString();
        String password = binding.userPassword.getText().toString();

        name = mName.getText().toString().trim();
        surname = mSurname.getText().toString().trim();
        mail = mEmail.getText().toString().trim();
        birthday = mBirthDay.getText().toString().trim();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show(); //Show Message if failure exists
        }else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                //If success user goes to home screen
                public void onSuccess(AuthResult authResult) {
                    userID = mAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("users").document(userID);
                    Map<String,Object> user = new HashMap<>();
                    user.put("Name", name);
                    user.put("Surname", surname);
                    user.put("E-mail", mail);
                    user.put("Birthday", birthday);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "onSuccess: user profile is created for" + userID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                             Log.d(TAG,"onFailure: "+ e.toString());
                        }
                    });


                    Intent intent = new Intent(SignUp.this,Home.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUp.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show(); //Understandable Message if failure exists
                }
            });
        }

    }

}