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

    /*public void ingresar(View v){
        DBHelper admin=new DBHelper(this,"BDbdECommercer",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        String user=usuario.getText().toString();
        String pass=clave.getText().toString();
        fila=db.rawQuery("select usuario,clave from Usuario where usuario='"+user+"' and clave='"+pass+"'",null);
                //preguntamos si el cursor tiene algun valor almacenado
        while(fila.moveToFirst()==true){
            //capturamos los valores del cursos y lo almacenamos en variable
            String usua=fila.getString(0);
            String contra=fila.getString(1);
            //preguntamos si los datos ingresados son iguales
            if (user.equals(usua)&&pass.equals(contra)){
                //si son iguales entonces vamos a otra ventana
                //Menu es una nueva actividad empty
                Intent ven=new Intent(this,InicioActivity.class);
                startActivity(ven);
                //limpiamos las las cajas de texto
                usuario.setText("");
                clave.setText("");

            }
            else {
                Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_LONG).show();

            }
        }
    }*/

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnLogin:
                String u = usuario.getText().toString();
                String p = clave.getText().toString();
                if(con.Login(u, p)==1){
                /*Usuario us = con.getUsuario(u, p);
                Toast.makeText(this, "Datos Correctos", Toast.LENGTH_LONG).show();*/
                Intent inicio = new Intent(this, InicioActivity.class);
                //inicio.putExtra("Id", us.getId());
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

    /*public void SignUp(View view){
        Intent signup = new Intent(this, SignupActivity.class);
        startActivity(signup);

    }

    public void PassWord(View view){
        Intent password = new Intent(this, PasswordActivity.class);
        startActivity(password);

    }*/



    /*public void LogIn(View view){
        String u = usuario.getText().toString();
        String p = clave.getText().toString();
        if(u.equals("") && p.equals("")){
            Toast.makeText(this, "ERROR: Campos vacíos", Toast.LENGTH_LONG).show();

        }
        else if(con.Login(u, p) == 1){
            Usuario us = con.getUsuario(u, p);
            Toast.makeText(this, "Datos Correctos", Toast.LENGTH_LONG).show();
            Intent inicio = new Intent(this, InicioActivity.class);
            inicio.putExtra("Id", us.getId());
            startActivity(inicio);
            //finish();

        }
        else{
            Toast.makeText(this, "Usuario o contraseña incorrecto", Toast.LENGTH_LONG).show();
        }



    }*/

    /*@Override
    public void onClick(View v) {

    }*/
}
