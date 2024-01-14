package com.example.login.services;

import com.example.login.entity.AuthEntity;
import com.example.login.entity.AuthRepository;
import com.example.login.models.RegisterDO;
import com.example.login.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {


    private final AuthRepository authRepository;


    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Response login(String username,String password){


        List<AuthEntity> list = this.authRepository.findAll().stream().filter(a -> a.getUsername().equalsIgnoreCase(username) && a.getPassword().equalsIgnoreCase(password)).toList();
        if(list.isEmpty()){
            return new Response("","","","","",0);
        }
        AuthEntity authEntity = list.get(0);
        return new Response(username, authEntity.getNombre(), authEntity.getApellido(), authEntity.getFoto(), authEntity.getRol(), authEntity.getId());
    }

    public Response registrar(RegisterDO data){
        try {
            AuthEntity authEntity = new AuthEntity();
            authEntity.setUsername(data.getUsername());
            authEntity.setPassword(data.getPassword());
            authEntity.setNombre(data.getNombre());
            authEntity.setApellido(data.getApellido());
            authEntity.setRol("USER");
            AuthEntity save = this.authRepository.save(authEntity);
            return new Response("Usuario creado correctamente",data.getNombre(),data.getApellido(),"",authEntity.getRol(),authEntity.getId());
        }catch (Exception ex){
            return new Response(ex.getMessage(),"","","","",0);
        }
    }
}
