package com.example.ordine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Random;

public class RegisterPage extends AppCompatActivity {
    private TextInputLayout txtInputNamaRegister, txtInputEmailRegister, txtInputPasswordRegister;
    private TextView txtLoginRegister;
    private Button btnRegisterRegister;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private HashMap<String, Object> map;
    private boolean emailIsValid = false;
    private boolean passwordIsValid = false;
    private boolean namaIsValid = false;
    private boolean registerSuccessful = false;
    private boolean emailIsTaken;
    private Random randint;
    private LoadingDialogActivity loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        loading = new LoadingDialogActivity(RegisterPage.this);
        txtLoginRegister = findViewById(R.id.txtLoginRegister);
        btnRegisterRegister = findViewById(R.id.btnRegisterRegister);
        txtInputNamaRegister = findViewById(R.id.txtInputNamaRegister);
        txtInputEmailRegister = findViewById(R.id.txtInputEmailRegister);
        txtInputPasswordRegister = findViewById(R.id.txtInputPasswordRegister);
        database = FirebaseDatabase.getInstance("https://ordine-9e7da-default-rtdb.asia-southeast1.firebasedatabase.app/");
        randint = new Random();
        map = new HashMap<>();

        auth = FirebaseAuth.getInstance();

        txtLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        txtInputEmailRegister.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = txtInputEmailRegister.getEditText().getText().toString().trim();

                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailIsValid = false;
                    txtInputEmailRegister.setError("Format email salah!");
                }else{
                    emailIsValid = true;
                    txtInputEmailRegister.setError("");
                }

                if(namaIsValid && emailIsValid && passwordIsValid){
                    btnRegisterRegister.setEnabled(true);
                }else{
                    btnRegisterRegister.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtInputNamaRegister.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String nama = txtInputNamaRegister.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(nama)){
                    namaIsValid = false;
                    txtInputNamaRegister.setError("Kolom Nama masih kosong!");
                }else{
                    namaIsValid = true;
                    txtInputNamaRegister.setError("");
                }

                if(namaIsValid && emailIsValid && passwordIsValid){
                    btnRegisterRegister.setEnabled(true);
                }else{
                    btnRegisterRegister.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtInputPasswordRegister.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = txtInputPasswordRegister.getEditText().getText().toString();

                if(password.length() < 6){
                    passwordIsValid = false;
                    txtInputPasswordRegister.setError("Password minimal 6 karakter!");
                }else{
                    passwordIsValid = true;
                    txtInputPasswordRegister.setError("");
                }

                if(namaIsValid && emailIsValid && passwordIsValid){
                    btnRegisterRegister.setEnabled(true);
                }else{
                    btnRegisterRegister.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnRegisterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtInputEmailRegister.getEditText().getText().toString().trim();
                String password = txtInputPasswordRegister.getEditText().getText().toString();

                registerUser(email, password);
            }
        });
    }

    private void registerUser(String email, String password){
        loading.startLoading();
        emailIsTaken = false;
        database.getReference().child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                emailIsTaken = checkUserRegistered(snapshot, email);

                if(!emailIsTaken){
                    String nama = txtInputNamaRegister.getEditText().getText().toString().trim();
                    String uid = auth.getCurrentUser().getUid();
                    int tableNum = randint.nextInt(100) + 1;
                    map.put("nama", nama);
                    map.put("email", email);
                    map.put("tableNum", tableNum);

                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isComplete()){
                                database.getReference().child("user").child(uid).updateChildren(map);
                                loading.stopLoading();
                                Toast.makeText(RegisterPage.this, "Register successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterPage.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                loading.stopLoading();
                                Toast.makeText(RegisterPage.this, "Register failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    loading.stopLoading();
                    Toast.makeText(RegisterPage.this, "Email has been taken!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }

    private boolean checkUserRegistered(DataSnapshot snapshot, String email){
        for(DataSnapshot dataUser : snapshot.getChildren()){
            if(email.equalsIgnoreCase(dataUser.child("email").getValue().toString())){
                return true;
            }
        }
        return false;
    }
}