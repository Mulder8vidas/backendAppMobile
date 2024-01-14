package com.example.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "auth")
@Getter
@Setter
public class AuthEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username",length = 100 )
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String rol;
    @Column(name = "foto",length = 5000)
    private String foto;

    @Override
    public String toString() {
        return "AuthEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
