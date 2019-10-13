package com.example.practica1.networksync;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request extends AsyncTask<Void, Void, String> {

    public Request(){

    }

    @Override
    protected String doInBackground(Void... parametros){
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://ec2-3-86-40-181.compute-1.amazonaws.com:9876/token").openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);
            String jsonInputString = "{'email': 'jeanmelvinlp27@gmail.com', 'uuid':'', 'token':''}";
            connection.getOutputStream().write(jsonInputString.getBytes("utf-8"));

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while((responseLine = br.readLine()) != null){
                response.append(responseLine.trim());

            }
            return response.toString();

        }
        catch (Exception e){
            e.printStackTrace();

        }
        return null;

    }

    @Override
    protected void onPostExecute(String token){
        Log.i("TOKEN", token);

    }

}
