package com.example.practica1.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practica1.AsynTasks.Response;
import com.example.practica1.AsynTasks.SignUpTask;
import com.example.practica1.Conexion.ConexionSQLiteHelper;
import com.example.practica1.Dialog.Message;
import com.example.practica1.Entidades.Usuario;
import com.example.practica1.Entidades.conUsuario;
import com.example.practica1.R;
import com.example.practica1.Utilidades.Utilidades;
import com.example.practica1.Utilidades.Validacion;

import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity /*implements View.OnClickListener*/{

    private EditText campoNombre, campoUsuario, campoEmail, campoClave, campoConfClv;
    private Switch campoAdmin;
    private TextView campoAdminn;
    private EditText campoNumero, campoFecha;
    private Button signup, login;
    private Context applicationContext;
    private ProgressBar progressBar;

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

    private void updateUI(){
        this.applicationContext = this;
        this.campoNombre = (EditText) findViewById(R.id.txtNombre);
        this.campoUsuario = (EditText) findViewById(R.id.txtUsuario);
        this.campoEmail = (EditText) findViewById(R.id.txtEmail);
        this.campoClave = (EditText) findViewById(R.id.txtClave);
        this.campoConfClv = (EditText) findViewById(R.id.txtConfClave);
        this.campoNumero = (EditText) findViewById(R.id.txtNumero);

        this.campoNumero.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        this.signup = (Button) findViewById(R.id.btnSignup);
        this.progressBar = (ProgressBar) findViewById(R.id.progress_bar_signup);

        this.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

    }

    public void Register(){
        boolean validacion = true;
        if(Validacion.Vacio(this.campoNombre)){
            this.campoNombre.setError("El campo Nombre está vacío!");
            validacion = false;
            progressBarInvisibleRegisterVisible();

        }
        if(Validacion.Vacio(this.campoUsuario)){
            this.campoUsuario.setError("El campo Usuario está vacío!");
            validacion = false;
            progressBarInvisibleRegisterVisible();
        }
        if(Validacion.Vacio(this.campoEmail)){
            this.campoEmail.setError("El campo Email está vacío!");
            validacion = false;
            progressBarInvisibleRegisterVisible();
        }
        if(Validacion.Vacio(this.campoClave)){
            this.campoEmail.setError("El campo Contraseña está vacío!");
            validacion = false;
            progressBarInvisibleRegisterVisible();
        }
        if(Validacion.Vacio(this.campoConfClv)){
            this.campoConfClv.setError("El campo Confirmar Contraseña está vacío!");
            validacion = false;
            progressBarInvisibleRegisterVisible();
        }
        else if (Validacion.CorreoValido(this.campoEmail, this.campoEmail.getText().toString())){
            progressBarInvisibleRegisterVisible();
        }

        if(validacion){
            progressBarVisibleRegisterInvisible();

            if(!this.campoClave.getText().toString().equals(this.campoConfClv.getText().toString())){
                this.campoClave.setError("Las contraseñas son diferentes!");
                this.campoConfClv.setError("Las contraseñas son diferentes!");
                progressBarInvisibleRegisterVisible();
            }
            else {
                Usuario user = new Usuario(this.campoNombre.getText().toString(),
                        this.campoUsuario.getText().toString(),
                        this.campoEmail.getText().toString(),
                        this.campoClave.getText().toString(),
                        this.campoConfClv.getText().toString(),
                        this.campoAdminn.getText().toString(),
                        this.campoNumero.getText().toString(),
                        this.campoFecha.getText().toString());
                SignUpTask signupTask = new SignUpTask(user, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBarInvisibleRegisterVisible();
                        limpiar();
                        Toast toast = Toast.makeText(applicationContext, "Usuario creado!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(Exception error) {
                        Message.getInstance(applicationContext).errorDialog("["+campoEmail.getText().toString()+"]"+error.getMessage());
                        progressBarInvisibleRegisterVisible();

                    }
                });
                signupTask.execute();

            }

        }
        else {
            progressBarInvisibleRegisterVisible();

        }

    }

    private void limpiar() {
        this.campoNombre.setText(null);
        this.campoUsuario.setText(null);
        this.campoEmail.setText(null);
        this.campoClave.setText(null);
        this.campoConfClv.setText(null);
        this.campoNumero.setText(null);
        this.campoFecha.setText(null);

    }

    private void progressBarInvisibleRegisterVisible(){
        progressBar.setVisibility(View.GONE);
        signup.setVisibility(View.VISIBLE);
    }

    private void progressBarVisibleRegisterInvisible(){
        progressBar.setVisibility(View.VISIBLE);
        signup.setVisibility(View.GONE);
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
