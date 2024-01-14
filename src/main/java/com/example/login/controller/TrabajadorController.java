package com.example.login.controller;

import com.example.login.entity.TrabajadorEntity;
import com.example.login.models.RequestCreateWorkerModel;
import com.example.login.models.Response;
import com.example.login.services.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trabajador")
public class TrabajadorController {


    @Autowired
    private TrabajadorService trabajadorService;

    @PostMapping("")
    public ResponseEntity<?> crearTrabajador(@RequestBody RequestCreateWorkerModel payload){


        boolean b = this.trabajadorService.crearTrabajador(payload);
        if(b){
            return ResponseEntity.ok(new Response("Trabajador Creado",payload.getNombre(),payload.getApellido(),"","",0));
        }else{
            return  ResponseEntity.badRequest().build();

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") int id){
        List<TrabajadorEntity> trabajadores = (List<TrabajadorEntity>)this.trabajadorService.listaTrabajadoresCompleto().get("trabajadores");
        List<TrabajadorEntity> list = trabajadores.stream().filter(a -> a.getId() == id).toList();

        Map<String,Object> mapa = new HashMap<>();
        mapa.put("trabajadores",list);

        return ResponseEntity.ok(mapa);

    }

    @GetMapping("")
    public ResponseEntity<?> findAllTrabajadores(){

        return ResponseEntity.ok(this.trabajadorService.listaTrabajadores());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){

        boolean deleted = this.trabajadorService.deleteById(id);
        return deleted ? ResponseEntity.ok(new Response("Trabajador Eliminado","","","","",0)) :
                ResponseEntity.ok(new Response("No se pudo eliminar el trabajador","","","","",0));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrabajador(@PathVariable("id") int id,@RequestBody RequestCreateWorkerModel modelo ){


        boolean updated = this.trabajadorService.updateById(id,modelo);

        return updated ? ResponseEntity.ok(new Response("Trabajador Actualizado","","","","",0)):
                ResponseEntity.ok(new Response("No se pudo actualizar el trabajador","","","","",0));

    }

}
