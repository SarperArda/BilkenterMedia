package com.thenameless.bilkenter_media;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
    EditText mName, mSurname, mPassword, mEmail, mBirthDay;
    Button mSignUpBtn;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        mName = findViewById(R.id.Username);
        mSurname = findViewById(R.id.UserSurname);
        mPassword = findViewById(R.id.userPassword);
        mEmail = findViewById(R.id.userMail);
        mBirthDay = findViewById(R.id.userBirthday);
        mSignUpBtn = findViewById(R.id.SignUp);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }

        //binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        //View view = binding.getRoot();
        //setContentView(view);

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String name = mName.getText().toString();
                String surname = mSurname.getText().toString();
                String birthday = mBirthDay.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("E-mail is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required.");
                }
                if (password.length() < 6) {
                    mPassword.setError("Password must be longer or equal to 6 characters.");
                    return;
                }


                //String email = binding.userMail.getText().toString();
                //String password = binding.userPassword.getText().toString();

                //name = mName.getText().toString().trim();
                //surname = mSurname.getText().toString().trim();
                //mail = mEmail.getText().toString().trim();
                //birthday = mBirthDay.getText().toString().trim();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    //If success user goes to home screen
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "User Created.", Toast.LENGTH_SHORT).show();
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Users Database").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("Name", name);
                            user.put("Surname", surname);
                            user.put("Birthday", birthday);
                            user.put("E-mail", email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "Success! User profile is created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Failure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(SignUp.this, Home.class));
                        } else {
                            Toast.makeText(SignUp.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    public void goBackToLogin(View view){
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}