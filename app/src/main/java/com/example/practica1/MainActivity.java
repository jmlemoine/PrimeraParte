package com.example.practica1;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void SignUp(View view){
        Intent signup = new Intent(this, SignupActivity.class);
        startActivity(signup);

    }

    /*public void PassWord(View view){
        Intent password = new Intent(this, PasswordActivity.class);
        startActivity(password);

    }*/

}
