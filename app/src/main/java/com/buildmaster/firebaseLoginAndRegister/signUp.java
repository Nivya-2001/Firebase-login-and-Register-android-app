package com.buildmaster.firebaseLoginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signUp extends AppCompatActivity {
    Button register , reg_login;
    TextInputLayout fullname_var, username_var, email_var , phone_var ,password_var;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        register = findViewById(R.id.btn_register);
        reg_login = findViewById(R.id.btn_reg_login);

        fullname_var = findViewById(R.id.fullname);
        username_var = findViewById(R.id.reg_username_text);
        email_var = findViewById(R.id.email);
        phone_var = findViewById(R.id.phone_number);
        password_var = findViewById(R.id.reg_password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname_ = fullname_var.getEditText().getText().toString();
                String username_ = fullname_var.getEditText().getText().toString();
                String email_ = fullname_var.getEditText().getText().toString();
                String phone_ = fullname_var.getEditText().getText().toString();
                String password_ = fullname_var.getEditText().getText().toString();

                if(!fullname_.isEmpty()){
                    fullname_var.setError(null);
                    fullname_var.setErrorEnabled(false);
                    if(!username_.isEmpty()){
                        username_var.setError(null);
                        username_var.setErrorEnabled(false);
                         if(!email_.isEmpty()){
                             email_var.setError(null);
                             email_var.setErrorEnabled(false);
                             if(!phone_.isEmpty()){
                                 phone_var.setError(null);
                                 phone_var.setErrorEnabled(false);
                                 if(!password_.isEmpty()){
                                     password_var.setError(null);
                                     password_var.setErrorEnabled(false);


                                             firebaseDatabase = FirebaseDatabase.getInstance();
                                             reference = firebaseDatabase.getReference("dataset");

                                             String fullname_s = fullname_var.getEditText().getText().toString();
                                             String username_s = fullname_var.getEditText().getText().toString();
                                             String email_s = fullname_var.getEditText().getText().toString();
                                             String phone_s = fullname_var.getEditText().getText().toString();
                                             String password_s = fullname_var.getEditText().getText().toString();

                                             StoreData storedata = new StoreData(fullname_s,username_s,email_s,phone_s,password_s);
                                             reference.child(username_s).setValue(storedata);

                                             Toast.makeText(signUp.this, "Register successfully", Toast.LENGTH_SHORT).show();

                                             Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                             startActivity(intent);
                                             finish();


                                 }else {
                                     password_var.setError("please enter the password");
                                 }
                             }else {
                                 phone_var.setError("please enter the Email");
                             }
                         }else {
                             email_var.setError("please enter the email");
                         }
                    }else {
                        username_var.setError("please enter the username");
                    }

                }else {
                    fullname_var.setError("please enter the fullname");
                }

            }
        });



        reg_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}