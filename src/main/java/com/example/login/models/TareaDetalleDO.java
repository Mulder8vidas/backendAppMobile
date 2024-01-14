package com.example.login.models;

import com.example.login.enumerate.EstadosTarea;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TareaDetalleDO {

    private String idTrabajador;
    private String tarea;
    private String hora;
    private String direccion;
    private EstadosTarea estado;

    public TareaDetalleDO() {
    }

    public TareaDetalleDO(String idTrabajador, String tarea, String hora, String direccion, EstadosTarea estado) {
        this.idTrabajador = idTrabajador;
        this.tarea = tarea;
        this.hora = hora;
        this.direccion = direccion;
        this.estado = estado;
    }
}
