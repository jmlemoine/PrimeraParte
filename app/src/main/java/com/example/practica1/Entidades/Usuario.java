package com.example.practica1.Entidades;

public class Usuario {

    int id;
    String nombre, usuario, email, clave, confclv, admin, numero, fecha;

    public Usuario(){

    }

    public Usuario(String nombre, String usuario, String email, String clave, String confclv, String admin, String numero, String fecha) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.email = email;
        this.clave = clave;
        this.confclv = confclv;
        this.admin = admin;
        this.numero = numero;
        this.fecha = fecha;
    }

    public boolean isNull(){
        if(nombre.equals("") && usuario.equals("") && email.equals("") && clave.equals("") && confclv.equals("") && admin.equals("") && numero.equals("") && fecha.equals("")){
            return false;

        }
        else{
            return true;

        }

    }


    public String toString(){
        return "Usuario{"+
                "Id" + id +
                ", Nombre='" + nombre + '\'' +
                ", Usuario='" + usuario + '\'' +
                ", Email='" + email + '\'' +
                ", Clave='" + clave + '\'' +
                ", Confirmar='" + confclv + '\'' +
                ", Admin='" + admin + '\'' +
                ", Numero='" + numero + '\'' +
                ", Fecha='" + fecha + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getConfclv() {
        return confclv;
    }

    public void setConfclv(String confclv) {
        this.confclv = confclv;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
