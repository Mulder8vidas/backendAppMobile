package com.example.login.services;

import com.example.login.entity.AuthEntity;
import com.example.login.entity.AuthRepository;
import com.example.login.entity.TrabajadorEntity;
import com.example.login.entity.TrabajadorRepository;
import com.example.login.models.RequestCreateWorkerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrabajadorService {


    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private AuthRepository authRepository;



    public boolean crearTrabajador(RequestCreateWorkerModel payload){


        try {
            TrabajadorEntity trabajadorEntity = new TrabajadorEntity();
            trabajadorEntity.setNombre(payload.getNombre());
            trabajadorEntity.setApellido(payload.getApellido());
            trabajadorEntity.setDni(payload.getDni());
            trabajadorEntity.setArea(payload.getArea());

            AuthEntity authEntity = new AuthEntity();
            authEntity.setNombre(trabajadorEntity.getNombre());
            authEntity.setApellido(trabajadorEntity.getApellido());
            authEntity.setRol("USER");
            authEntity.setUsername(payload.getNombre().substring(0,2)+payload.getApellido().substring(0,2));
            authEntity.setPassword(payload.getDni());
            AuthEntity save1 = this.authRepository.save(authEntity);
            trabajadorEntity.setIdAuth(save1.getId());
            TrabajadorEntity save = this.trabajadorRepository.save(trabajadorEntity);
            return true;
        }catch (Exception ex){
            return false;
        }




    }

    public Map<String, Object> listaTrabajadores(){

        Map<String, Object> lista  = new HashMap<>();

        List<TrabajadorEntity> all = this.trabajadorRepository.findAll();
        List<String> list = all.stream().map(trabajadorEntity -> trabajadorEntity.getId()+":"+trabajadorEntity.getNombre() + " " + trabajadorEntity.getApellido()).toList();

        lista.put("trabajadores",list);
        
        return lista;
    }
    public Map<String, Object> listaTrabajadoresCompleto(){

        Map<String, Object> lista  = new HashMap<>();

        List<TrabajadorEntity> all = this.trabajadorRepository.findAll();
        lista.put("trabajadores",all);

        return lista;
    }

    public boolean deleteById(int id) {

        try {
            this.trabajadorRepository.deleteById(id);



            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }

    public boolean updateById(int id, RequestCreateWorkerModel modelo) {

        try {
            TrabajadorEntity trabajadorEntity = this.trabajadorRepository.findById(id).get();
            trabajadorEntity.setNombre(modelo.getNombre());
            trabajadorEntity.setApellido(modelo.getApellido());
            trabajadorEntity.setDni(modelo.getDni());
            TrabajadorEntity save = this.trabajadorRepository.save(trabajadorEntity);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }
}
