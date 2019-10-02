package com.example.practica1.Clases;

import android.content.ContentValues;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.practica1.Conexion.ConexionSQLiteHelper;
import com.example.practica1.R;
import com.example.practica1.Utilidades.Utilidades;

public class SignupActivity extends AppCompatActivity {

    EditText campoNombre, campoUsuario, campoEmail, campoClave, campoConfClv;
    Switch campoAdmin;
    EditText campoNumero, campoFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        campoNombre = (EditText)findViewById(R.id.txtNombre);
        campoUsuario = (EditText)findViewById(R.id.txtUsuario);
        campoEmail = (EditText)findViewById(R.id.txtEmail);
        campoClave = (EditText)findViewById(R.id.txtClave);
        campoConfClv = (EditText)findViewById(R.id.txtConfClave);

        campoAdmin = (Switch)findViewById(R.id.switchAdmin);

        campoNumero = (EditText)findViewById(R.id.txtNumero);
        campoFecha = (EditText)findViewById(R.id.txtFecha);


    }

    public void LogIn(View view){
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);

    }

    public void Registrar(View view){
        //signUp();
        signUpSQL();

    }

    public void signUpSQL(){

        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bdECommercer", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();
        String insert = "INSERT INTO "
                +Utilidades.tablaUsuario+" ("
                +Utilidades.campoNombre+", "
                +Utilidades.campoUsuario+", "
                +Utilidades.campoEmail+", "
                +Utilidades.campoClave+", "
                +Utilidades.campoConfClv+", "
                +Utilidades.campoAdmin+", "
                +Utilidades.campoNumero+", "
                +Utilidades.campoFecha+") " +
                "values ('"
                +campoNombre.getText().toString()+
                "', '"+campoUsuario.getText().toString()+
                "', '"+campoEmail.getText().toString()+
                "', '"+campoClave.getText().toString()+
                "', '"+campoConfClv.getText().toString()+
                "', '"+campoAdmin.getText().toString()+
                "', '"+campoNumero.getText().toString()+
                "', '"+campoFecha.getText().toString()+"')";

        db.execSQL(insert);
        db.close();

    }



    public void signUp(){
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bdECommercer", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utilidades.campoNombre, campoNombre.getText().toString());
        values.put(Utilidades.campoUsuario, campoUsuario.getText().toString());
        values.put(Utilidades.campoEmail, campoEmail.getText().toString());
        values.put(Utilidades.campoClave, campoClave.getText().toString());
        values.put(Utilidades.campoConfClv, campoConfClv.getText().toString());
        values.put(Utilidades.campoAdmin, campoAdmin.getText().toString());
        values.put(Utilidades.campoNumero, campoNumero.getText().toString());
        values.put(Utilidades.campoFecha, campoFecha.getText().toString());

        Long nombre = db.insert(Utilidades.tablaUsuario, Utilidades.campoNombre, values);

        Toast.makeText(getApplicationContext(), "Usuario: "+nombre, Toast.LENGTH_SHORT).show();
        db.close();



    }


}
