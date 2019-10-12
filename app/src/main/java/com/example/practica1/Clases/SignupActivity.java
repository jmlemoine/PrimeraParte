package com.example.practica1.Clases;

import android.content.ContentValues;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practica1.Conexion.ConexionSQLiteHelper;
import com.example.practica1.Entidades.Usuario;
import com.example.practica1.Entidades.conUsuario;
import com.example.practica1.R;
import com.example.practica1.Utilidades.Utilidades;

public class SignupActivity extends AppCompatActivity /*implements View.OnClickListener*/{

    EditText campoNombre, campoUsuario, campoEmail, campoClave, campoConfClv;
    Switch campoAdmin;
    TextView campoAdminn;
    EditText campoNumero, campoFecha;
    Button signup, login;
    conUsuario con;


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
        campoAdminn = (TextView)findViewById(R.id.txtAdmin);

        campoNumero = (EditText)findViewById(R.id.txtNumero);
        campoFecha = (EditText)findViewById(R.id.txtFecha);

        con = new conUsuario(this);

        campoAdminn.setText("NO es Admin");




    }

    public void LogIn(View view){
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);

    }



    public void SignUp(View view){
        Usuario u = new Usuario();
        u.setNombre(campoNombre.getText().toString());
        u.setUsuario(campoUsuario.getText().toString());
        u.setEmail(campoEmail.getText().toString());
        u.setClave(campoClave.getText().toString());
        u.setConfclv(campoConfClv.getText().toString());
        u.setAdmin(campoAdminn.getText().toString());
        u.setNumero(campoNumero.getText().toString());
        u.setFecha(campoFecha.getText().toString());
        if(!u.isNull()){
            Toast.makeText(this, "ERROR: Campos Vacíos", Toast.LENGTH_LONG).show();

        }
        else if(con.crearUsuario(u)){
            Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_LONG).show();
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);


        }
        else{
            Toast.makeText(this, "Usuario ya registrado, debe poner otro", Toast.LENGTH_LONG).show();

        }

        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);

    }

    public void swtAdmin(View view){
        if(view.getId() == R.id.switchAdmin){
            if(campoAdmin.isChecked()){
                campoAdminn.setText("Es Admin");
            }
            else {
                campoAdminn.setText("NO es Admin");
            }
        }

    }

}
