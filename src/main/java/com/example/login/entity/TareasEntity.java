package com.example.login.entity;

import com.example.login.enumerate.EstadosTarea;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tareas")
@Getter
@Setter
public class TareasEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idTrabajador;
    private String tarea;
    private String hora;
    private String direccion;

    @Enumerated(EnumType.STRING)
    @Column
    private EstadosTarea estado;

    public TareasEntity() {
        this.estado=EstadosTarea.RECIBIDO;
    }
}
