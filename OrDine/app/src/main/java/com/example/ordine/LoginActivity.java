package com.example.ordine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout txtInputEmailLogin, txtInputPasswordLogin;
    private TextView txtRegisterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtInputEmailLogin = findViewById(R.id.txtInputEmailLogin);
        txtInputPasswordLogin = findViewById(R.id.txtInputPasswordLogin);
        txtRegisterLogin = findViewById(R.id.txtRegisterLogin);

        txtRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterPage.class);
                startActivity(intent);
            }
        });
    }
}