package com.cooldevs.ticketit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class RegistrationActivity extends AppCompatActivity {

    //variables
    EditText regName,regUserName,regEmail,regPhoneNo,regPassword,regKey;
    Button regBtn,regToLoginBtn ;
    ProgressBar progressBar;
FirebaseFirestore fStore;
String userID;
Switch toggleKey;
TextInputLayout regInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        regName=findViewById(R.id.full_name_edit);
        regUserName=findViewById(R.id.username_edit_text);
        regEmail=findViewById(R.id.email_edt_text);
        regPhoneNo=findViewById((R.id.phonenumber_edt_text));
        regPassword=findViewById(R.id.pass_edt_text);
        regBtn=findViewById(R.id.regBtn);
        regToLoginBtn=findViewById(R.id.regToLoginBtn);
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);
        regKey = findViewById(R.id.key_edit);
        toggleKey= findViewById(R.id.switch1);
        regInput=findViewById(R.id.key_input_edit);

        regInput.setVisibility(View.GONE);
        if (fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
        toggleKey.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(toggleKey.isChecked())
                        {

                            regInput.setVisibility(View.VISIBLE);
                        }
                        else
                        {

                            regInput.setVisibility(View.GONE);
                        }
                    }
                }
        );
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            final String email=regEmail.getText().toString().trim();
            final String password =regPassword.getText().toString().trim();
            final String fullName = regName.getText().toString().trim();
            final String username = regUserName.getText().toString().trim();
            final String phone= regPhoneNo.getText().toString().trim();
            final String key = regKey.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                regEmail.setError("Email is Required");
                return;
            }
            if(TextUtils.isEmpty(password))
            {
                regPassword.setError("Password is Required.");
            }
            if(password.length()<6)
            {
                regPassword.setError("Password must be >= 6 Characters.");
                return;
            }
            if(key.equals("admin"))
            {
                regInput.setError("Invalid Admin key");
                return;
            }
            progressBar.setVisibility(View.VISIBLE);

            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener((task) ->
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(RegistrationActivity.this,"User Created",Toast.LENGTH_LONG).show();
                            userID= fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",fullName);
                            user.put("email",email);
                            user.put("phoneNo",phone);
                            user.put("username",username);
                            if (key.equals("admin"))
                            {
                                user.put("admin",true);
                            }

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"onSuccess: user Profile is created for " +userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }

                        else
                        {
                            Toast.makeText(RegistrationActivity.this,"Error ! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    });

            }
        });

    }
}