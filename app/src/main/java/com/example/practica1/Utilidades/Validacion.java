package com.example.practica1.Utilidades;

import android.widget.EditText;

public class Validacion {

    public Validacion()
    {

    }

    public static boolean Vacio(EditText editText){
        String input = editText.getText().toString().trim();
        return input.length() == 0;
    }

    public static boolean ClaveValida(final EditText view, final String pass) {
        if (pass.length() < 4 || pass.length() > 20) {
            view.setError("Password Must consist of 4 to 20 characters");
            return false;
        }
        return true;
    }

    public static boolean CorreoValido(final EditText view, final String email) {
        if (email.length() < 4 || email.length() > 30) {
            view.setError("Email Must consist of 4 to 30 characters");
            return false;
        } else if (!email.matches("^[A-za-z0-9.@]+")) {
            view.setError("Only . and @ characters allowed");
            return false;
        } else if (!email.contains("@") || !email.contains(".")) {
            view.setError("Email must contain @ and .");
            return false;
        }
        return true;
    }

}
