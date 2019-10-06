package com.example.practica1.Entidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class conUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> listUsuario;
    SQLiteDatabase sql;
    String db = "dbECommercer";
    String tabla = "CREATE TABLE IF NOT EXISTS Usuario(id integer primary key autoincrement, nombre text, usuario text, email text, clave text, confclv text, admin text, numero text, fecha text)";

    public conUsuario(Context c){
        this.c = c;
        sql = c.openOrCreateDatabase(db, c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u = new Usuario();

    }

    public boolean crearUsuario(Usuario u){
        if(buscar(u.getUsuario())==0){
            ContentValues cv = new ContentValues();
            cv.put("nombre", u.getNombre());
            cv.put("usuario", u.getUsuario());
            cv.put("email", u.getEmail());
            cv.put("clave", u.getClave());
            cv.put("confclv", u.getConfclv());
            cv.put("admin", u.getAdmin());
            cv.put("numero", u.getNumero());
            cv.put("fecha", u.getFecha());
            return (sql.insert("Usuario", null, cv) > 0);

        }
        else{
            return false;

        }
    }

    public int buscar(String u){
        int x=0;
        listUsuario = selectUsuarios();
        for (Usuario us : listUsuario){
            if(us.getUsuario().equals(u)){
                x++;

            }
        }
        return x;

    }

    public ArrayList<Usuario> selectUsuarios(){
        ArrayList<Usuario> listUsuario = new ArrayList<Usuario>();
        listUsuario.clear();
        Cursor cur = sql.rawQuery("SELECT * FROM Usuario", null);
        if(cur != null && cur.moveToFirst()){
            do {
                Usuario u = new Usuario();
                u.setId(cur.getInt(0));
                u.setNombre(cur.getString(1));
                u.setUsuario(cur.getString(2));
                u.setEmail(cur.getString(3));
                u.setClave(cur.getString(4));
                u.setConfclv(cur.getString(5));
                u.setAdmin(cur.getString(6));
                u.setNumero(cur.getString(7));
                u.setFecha(cur.getString(8));
                listUsuario.add(u);

            }while (cur.moveToNext());
        }
        return listUsuario;
    }

    public int Login(String u, String p){
        int a = 0;
        Cursor cur = sql.rawQuery("SELECT * FROM Usuario", null);
        if(cur!=null && cur.moveToFirst()){
            do {
                if(cur.getString(1).equals(u) && cur.getString(2).equals(p)){
                    a++;
                }
            }while (cur.moveToNext());
        }
        return a;
    }

    public Usuario getUsuario(String u, String p){
        listUsuario = selectUsuarios();
        for(Usuario us: listUsuario){
            if(us.getUsuario().equals(u) && us.getClave().equals(p)){
                return us;
            }
        }
        return null;

    }

    public Usuario getUsuarioByID(int id){
        listUsuario = selectUsuarios();
        for(Usuario us: listUsuario){
            if(us.getId() == id){
                return us;
            }
        }
        return null;
    }





}
