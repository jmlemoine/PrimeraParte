package com.example.practica1.Clases;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import static java.lang.Thread.sleep;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText usuario, clave;
    Button login, signup, passw;
    private Cursor fila;
    conUsuario con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //checkConnection();

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

    public void checkConnection(){
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        String u = usuario.getText().toString();
        String p = clave.getText().toString();
        Usuario us = con.getUsuario(u, p);


        if (null != activeNetwork){
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                Intent inicio = new Intent(this, InicioActivityStart.class);
                inicio.putExtra("Id", us.getId());
                startActivity(inicio);
                finish();
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "WiFi Activado", Toast.LENGTH_SHORT).show();


            }
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){

                /*inicio.putExtra("Id", us.getId());
                startActivity(inicio);
                finish();*/
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "Red de Datos Activa", Toast.LENGTH_SHORT).show();

            }

        }
        else {
                Toast.makeText(this, "No hay Conexión a Internet", Toast.LENGTH_SHORT).show();

        }

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnLogin:
                String u = usuario.getText().toString();
                String p = clave.getText().toString();
                Usuario us = con.getUsuario(u, p);
                if(con.Login(u, p)==true){
                Toast.makeText(this, "Datos Correctos", Toast.LENGTH_LONG).show();
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkConnection();
                /*Intent inicio = new Intent(this, InicioActivityStart.class);
                inicio.putExtra("Id", us.getId());
                startActivity(inicio);
                finish();*/

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
