package com.example.login.controller;

import com.example.login.enumerate.EstadosTarea;
import com.example.login.models.Response;
import com.example.login.models.TareaDO;
import com.example.login.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarea")
public class TareController {

    @Autowired
    private TareaService tareaService;

    @PostMapping("")
    public ResponseEntity<?> crearTarea(@RequestBody TareaDO payload) {

        boolean b = this.tareaService.crearTarea(payload);
        return b ? ResponseEntity.ok(new Response("Tarea creada", "", "", "", "", 0)) : ResponseEntity.badRequest().build();

    }

    @GetMapping("/persona/{id}")
    public ResponseEntity<?> getTareasPerUsuario(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.tareaService.tareasUsuario(id));
    }
    @GetMapping("")
    public ResponseEntity<?> getTareas() {
        return ResponseEntity.ok(this.tareaService.listaTareas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetalleTarea(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.tareaService.detalleTarea(id));
    }

    @GetMapping("/{id}/{estado}")
    public ResponseEntity<?> actualizarEstado(@PathVariable("id") int id, @PathVariable("estado")EstadosTarea estado){
        return ResponseEntity.ok(this.tareaService.actualizarEstado(id,estado));
    }
}
