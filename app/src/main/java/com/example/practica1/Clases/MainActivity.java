package com.example.practica1.Clases;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica1.Conexion.ConexionSQLiteHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bdECommercer", null, 1);

    }

}
