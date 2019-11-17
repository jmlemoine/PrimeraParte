package com.example.practica1.AsynTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.practica1.Entidades.Usuario;
import com.example.practica1.Utilidades.HttpConnection;

/*import com.google.gson.Gson;
import com.google.gson.GsonBuilder;*/

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SignUpTask extends AsyncTask<Void, Void, Result> {

    private static final String REGISTER_URL = "http://ec2-3-86-40-181.compute-1.amazonaws.com:6789/register";
    private Usuario user;
    private Response.Listener listener;
    private Response.ErrorListener errorListener;

    public SignUpTask(Usuario user, Response.Listener<JSONObject> listener){

    }

    public SignUpTask(Usuario user, Response.Listener listener, Response.ErrorListener errorListener){
        this.user = user;
        this.listener = listener;
        this.errorListener = errorListener;
    }

    @Override
    protected Result doInBackground(Void... params) {
        /*final HttpConnection connection = new HttpConnection(REGISTER_URL, "POST");
        String result;
        if(this.user != null){
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            try {
                return new Result(connection.post(gson.toJson(this.user).toString()));

            }
            catch (IOException e){
                return new Result<>(new Exception(e));

            }

        }*/
        return null;

    }


}
