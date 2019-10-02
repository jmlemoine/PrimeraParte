package com.example.practica1.Clases;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.practica1.R;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }

    public void SignUp(View view){
        Intent signup = new Intent(this, SignupActivity.class);
        startActivity(signup);

    }

    public void PassWord(View view){
        Intent password = new Intent(this, PasswordActivity.class);
        startActivity(password);

    }

    public void Inicio(View view){
        Intent inicio = new Intent(this, InicioActivity.class);
        startActivity(inicio);

    }

}
