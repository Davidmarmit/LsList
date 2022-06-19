package com.todo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tarea implements Serializable {
    @SerializedName("userId")
    private Integer basura1;
    @SerializedName("id")
    private Integer basura2;
    @SerializedName("title")
    private String nombre;
    @SerializedName("completed")
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
