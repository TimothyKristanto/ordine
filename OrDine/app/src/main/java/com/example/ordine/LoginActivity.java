package com.example.ordine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout txtInputEmailLogin, txtInputPasswordLogin;
    private TextView txtRegisterLogin;
    private Button btnLoginLogin;
    private Boolean emailIsValid = false, passwordIsValid = false;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private LoadingDialogActivity loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loading = new LoadingDialogActivity(LoginActivity.this);
        txtInputEmailLogin = findViewById(R.id.txtInputEmailLogin);
        txtInputPasswordLogin = findViewById(R.id.txtInputPasswordLogin);
        txtRegisterLogin = findViewById(R.id.txtRegisterLogin);
        btnLoginLogin = findViewById(R.id.btnLoginLogin);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://ordine-9e7da-default-rtdb.asia-southeast1.firebasedatabase.app/");

        txtInputEmailLogin.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = txtInputEmailLogin.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailIsValid = false;
                    txtInputEmailLogin.setError("Kolom email masih kosong!");
                }else{
                    emailIsValid = true;
                    txtInputEmailLogin.setError("");
                }

                if(emailIsValid && passwordIsValid){
                    btnLoginLogin.setEnabled(true);
                }else{
                    btnLoginLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtInputPasswordLogin.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = txtInputPasswordLogin.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(password)){
                    passwordIsValid = false;
                    txtInputPasswordLogin.setError("Kolom password masih kosong!");
                }else{
                    passwordIsValid = true;
                    txtInputEmailLogin.setError("");
                }

                if(emailIsValid && passwordIsValid){
                    btnLoginLogin.setEnabled(true);
                }else{
                    btnLoginLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterPage.class);
                startActivity(intent);
            }
        });

        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtInputEmailLogin.getEditText().getText().toString().trim();
                String password = txtInputPasswordLogin.getEditText().getText().toString();

                loginUser(email, password);
            }
        });
    }

    private void loginUser(String email, String password){
        loading.startLoading();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String uid = auth.getCurrentUser().getUid();
                    Toast.makeText(LoginActivity.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    loading.stopLoading();
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Login gagal!", Toast.LENGTH_SHORT).show();
                    loading.stopLoading();
                }
            }
        });
    }
}