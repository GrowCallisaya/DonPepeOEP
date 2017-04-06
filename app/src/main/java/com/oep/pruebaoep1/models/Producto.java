package com.oep.pruebaoep1.models;

/**
 * Created by growcallisaya on 4/4/17.
 */

public class Producto {
    private String imagen;
    private String nombre;
    private int precio;

    public Producto(String imagen, String nombre, int precio) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
