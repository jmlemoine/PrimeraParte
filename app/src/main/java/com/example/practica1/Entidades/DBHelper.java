package com.example.practica1.Entidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/** * Created by RAUL on 26/05/2016. */
public class DBHelper  extends SQLiteOpenHelper{
    public DBHelper(Context context, String name,
                    SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Usuario(id integer primary key autoincrement, nombre text, usuario text, email text, clave text, confclv text, admin text, numero text, fecha text)");
        //db.execSQL("insert into Usuario values('admin','admin')");
    }

    @Override    public void onUpgrade(SQLiteDatabase db, int
            oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Usuario(id integer primary key autoincrement, nombre text, usuario text, email text, clave text, confclv text, admin text, numero text, fecha text)");
        //db.execSQL("insert into Usuario values('admin','admin')");
    }
}
