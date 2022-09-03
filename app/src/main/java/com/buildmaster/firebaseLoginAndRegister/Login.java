package com.buildmaster.firebaseLoginAndRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {
     Button login,signup;
     TextInputLayout username_var, password_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = findViewById(R.id.btn_signup);
        login = findViewById(R.id.btn_login);
        username_var = findViewById(R.id.username_text);
        password_var = findViewById(R.id.password);

        login.setOnClickListener(view -> {

            String username_ = Objects.requireNonNull(username_var.getEditText()).getText().toString();
            String password_= password_var.getEditText().getText().toString();

            if(!username_.isEmpty()){
                username_var.setError(null);
                username_var.setErrorEnabled(false);

                if(!password_.isEmpty()){
                    password_var.setError(null);
                    password_var.setErrorEnabled(false);

                    final String username_data = username_var.getEditText().getText().toString();
                    final String password_data = password_var.getEditText().getText().toString();

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference("dataset");

                    Query check_username = databaseReference.orderByChild("username").equalTo(username_data);
                    check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                username_var.setError(null);
                                username_var.setErrorEnabled(false);

                                String check_password = snapshot.child(username_data).child("password").getValue(String.class);
                                if(check_password.equals(password_data)){
                                    password_var.setError(null);
                                    password_var.setErrorEnabled(false);

                                    Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }else {
                                    password_var.setError("Wrong password");
                                }
                            }else {
                                username_var.setError("Username not present");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }else {
                    password_var.setError("please enter the password");
                }

            }else {
                username_var.setError("please enter the username");
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , signUp.class);
                startActivity(intent);
            }
        });
    }
}