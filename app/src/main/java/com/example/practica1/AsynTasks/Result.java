package com.example.practica1.AsynTasks;

public class Result<T> {

    private T result;
    private Exception error;

    public T getResult(){
        return result;
    }

    public Exception getError(){
        return error;
    }

    public Result(T result){
        super();
        this.result = result;
    }

}
