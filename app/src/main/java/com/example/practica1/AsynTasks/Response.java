package com.example.practica1.AsynTasks;

public class Response<T> {

    public interface Listener<T>{
        void onResponse(T response);
    }

    public interface ErrorListener{
        void onErrorResponse(Exception error);
    }

}
