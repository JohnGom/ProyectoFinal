package com.herprogramacion.proyectofinal.Data;

/**
 * Created by lds on 30/10/2016.
 */
public class Lugar2 {

    private String id;
    private String nombre;
    private String tipo;
    private String latitud;
    private String longitud;


    public Lugar2(String id, String nombre,String tipo, String latitud, String longitud){

        this.id=id;
        this.nombre=nombre;
        this.tipo=tipo;
        this.latitud=latitud;
        this.longitud=longitud;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}

