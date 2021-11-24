package com.cooldevs.ticketit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button regBtn,loginBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fAuth = FirebaseAuth.getInstance();
        username=findViewById(R.id.email_edt_text);
        password=findViewById(R.id.pass_edt_text);
        regBtn = findViewById(R.id.signup_btn);
        loginBtn= findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString().trim();
                String pass = password.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    username.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(pass))
                {
                    password.setError("Password is Required.");
                }
                if(pass.length()<6)
                {
                    password.setError("Password must be >= 6 Characters.");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(
                        new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finishActivity(1000);
                            }
                        }
                );

            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
                                      }
                                  }
        );
    }
}