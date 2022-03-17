package com.todo;

import java.io.Serializable;

public class Tarea implements Serializable {
    private String nombre;
    private Boolean hecha;

    public Tarea(String nombre) {
        this.nombre = nombre;
        this.hecha = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getHecha() {
        return hecha;
    }

    public void setHecha(Boolean hecha) {
        this.hecha = hecha;
    }
}
