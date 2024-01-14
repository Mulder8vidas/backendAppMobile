package com.example.login.models;

import java.util.List;

public class ResponseTareas {

    public String tarea;
    public int id;

    public ResponseTareas() {
    }

    public ResponseTareas(String tarea, int id) {
        this.tarea = tarea;
        this.id = id;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
