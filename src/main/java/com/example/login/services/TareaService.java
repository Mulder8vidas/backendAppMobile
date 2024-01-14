package com.example.login.services;

import com.example.login.entity.*;
import com.example.login.enumerate.EstadosTarea;
import com.example.login.models.Response;
import com.example.login.models.ResponseTareas;
import com.example.login.models.TareaDO;
import com.example.login.models.TareaDetalleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TareaService {

    @Autowired
    private TareasRepository tareasRepository;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private AuthRepository authRepository;

    public boolean crearTarea(TareaDO payload) {

        List<TrabajadorEntity> collect = this.trabajadorRepository.findAll().stream().filter(trabajadorEntity -> (String.valueOf(trabajadorEntity.getId())).equalsIgnoreCase(payload.getIdTrabajador().split(":")[0])).collect(Collectors.toList());

        if (!collect.isEmpty()) {
            try {
                TareasEntity tareasEntity = new TareasEntity();
                tareasEntity.setTarea(payload.getTarea());
                tareasEntity.setDireccion(payload.getDireccion());
                tareasEntity.setHora(payload.getHora());
                tareasEntity.setIdTrabajador(collect.get(0).getId());

                TareasEntity save = this.tareasRepository.save(tareasEntity);

                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        } else {
            return false;
        }


    }

    public List<ResponseTareas> tareasUsuario(int id) {
        return this.tareasRepository.findAll().stream().filter(a -> {


            TrabajadorEntity trabajadorEntity = this.trabajadorRepository.findAll().stream().filter(b -> {

                System.out.println(id);
                System.out.println(b.getIdAuth());

                return b.getIdAuth() == id;

            }).findFirst().get();

            return trabajadorEntity.getId()==a.getIdTrabajador();

        }).map(b -> new ResponseTareas(b.getTarea(), b.getId())).collect(Collectors.toList());
    }
    public List<ResponseTareas> listaTareas() {
        return this.tareasRepository.findAll().stream().map(b -> {
            System.out.println(b.getIdTrabajador());
            TrabajadorEntity trabajadorEntity = this.trabajadorRepository.findAll().stream().filter(a->a.getId()==b.getIdTrabajador()).findFirst().get();
            return new ResponseTareas(b.getTarea() + "-----"+b.getEstado().name()+" : "+trabajadorEntity.getNombre() + " "+trabajadorEntity.getApellido(), b.getId());
        }).collect(Collectors.toList());

    }

    public TareaDetalleDO detalleTarea(int idTarea) {


        return this.tareasRepository.findAll().stream()
                .filter(a -> a.getId() == idTarea)
                .map(a -> {

                    TrabajadorEntity trabajdor = this.buscarTrabajador(a.getIdTrabajador());

                    return new TareaDetalleDO(trabajdor.getNombre() + " " + trabajdor.getApellido(), a.getTarea(), a.getHora(), a.getDireccion(), a.getEstado());
                }).findFirst().get();
    }

    public TrabajadorEntity buscarTrabajador(int id) {




        List<TrabajadorEntity> collect = this.trabajadorRepository.findAll().stream().filter(a -> a.getId() == id).collect(Collectors.toList());
        System.out.println(collect);
        return this.trabajadorRepository.findAll().stream().filter(a -> a.getId() == id).findFirst().get();
    }

    public Response actualizarEstado(int id, EstadosTarea estado) {


        TareasEntity tareasEntity = this.tareasRepository.findById(id).get();
        tareasEntity.setEstado(estado);
        this.tareasRepository.save(tareasEntity);
        return new Response("Actualizado","","","","",0);
    }
}
