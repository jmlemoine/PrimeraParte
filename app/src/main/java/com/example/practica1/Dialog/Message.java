package com.example.practica1.Dialog;

import android.app.AlertDialog;
import android.content.Context;

import com.example.practica1.R;

public class Message {

    private static Message instance;
    private static AlertDialog.Builder builder;

    private Message(){ }

    public static Message getInstance(Context context){
        builder = new AlertDialog.Builder(context);
        if(instance == null){
            instance = new Message();
        }
        return instance;
    }

    public void informationDialog(String message){
        builder.setIcon(R.drawable.ic_error1);
        builder.setTitle("Information Message").setMessage(message);
        builder.create().show();
    }

    public void errorDialog(String message){
        builder.setIcon(R.drawable.ic_error2);
        builder.setTitle("Error Message").setMessage(message);
        builder.create().show();
    }

}
