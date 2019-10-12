package com.example.practica1.Clases;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practica1.Clases.Inicio.InicioActivityStart;
import com.example.practica1.Clases.LoginActivity;
import com.example.practica1.Conexion.ConexionSQLiteHelper;
import com.example.practica1.Entidades.DBHelper;
import com.example.practica1.Entidades.Usuario;
import com.example.practica1.Entidades.conUsuario;
import com.example.practica1.R;
import com.example.practica1.Utilidades.Utilidades;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText usuario, clave;
    Button login, signup, passw;
    private Cursor fila;
    conUsuario con;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText)findViewById(R.id.txtUsuario);
        clave = (EditText)findViewById(R.id.txtClave);

        login = (Button)findViewById(R.id.btnLogin);
        signup = (Button)findViewById(R.id.btnSignUp);
        passw = (Button)findViewById(R.id.btnPass);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        passw.setOnClickListener(this);
        con = new conUsuario(this);



    }



    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnLogin:
                String u = usuario.getText().toString();
                String p = clave.getText().toString();
                if(con.Login(u, p)==true){
                Usuario us = con.getUsuario(u, p);
                Toast.makeText(this, "Datos Correctos", Toast.LENGTH_LONG).show();
                Intent inicio = new Intent(this, InicioActivityStart.class);
                inicio.putExtra("Id", us.getId());
                startActivity(inicio);
                finish();

                }
                else if(u.equals("") && p.equals("")){
                    Toast.makeText(this, "ERROR: Campos vacíos", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(this, "Usuario o contraseña incorrecto", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnSignUp:
                Intent signup = new Intent(this, SignupActivity.class);
                startActivity(signup);
                break;

            case R.id.btnPass:
                Intent password = new Intent(this, PasswordActivity.class);
                startActivity(password);
                break;
        }
    }

}
