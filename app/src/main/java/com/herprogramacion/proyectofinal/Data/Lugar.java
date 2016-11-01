package com.herprogramacion.proyectofinal.Data;

/**
 * Created by lds on 30/10/2016.
 */
public class Lugar {

    private String nombre;
    private String tipo;


    public Lugar(String nombre,String tipo){

        this.nombre=nombre;
        this.tipo=tipo;
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


}
