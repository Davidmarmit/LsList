package com.todo;

public class Tarea {
    private String nombre;
    private Boolean hecha;

    public Tarea(String nombre, Boolean hecha) {
        this.nombre = nombre;
        this.hecha = hecha;
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
