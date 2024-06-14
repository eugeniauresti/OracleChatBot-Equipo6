package com.example.Microservice_OAD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Microservice_OAD.service.TareaService;
import com.example.Microservice_OAD.model.Tarea;

import java.util.List;

@RestController
public class TareaController {
    
    @Autowired
    private TareaService tareaService;

    @GetMapping(value = "/listaTarea")
    public List<Tarea> getAllTareas(){
        return tareaService.findAll();
    }

    @GetMapping(value = "/listaTarea/{IdTarea}")
    public ResponseEntity<Tarea> getTareaId(@PathVariable int IdTarea) {
        try {
            ResponseEntity<Tarea> responseEntity = tareaService.getTareaById(IdTarea);
            return new ResponseEntity<Tarea>(responseEntity.getBody(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/listaTarea")
    public ResponseEntity addTarea(@RequestBody Tarea tarea) throws Exception {
        Tarea td = tareaService.addTarea(tarea);
        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.set("location",""+td.getIdTarea());
        responHeaders.set("Access-Control-Expose-Headers","location");

        return ResponseEntity.ok().headers(responHeaders).build();  
    }

    @PutMapping(value = "listaTarea/{IdTarea}")
    public ResponseEntity updateTarea(@RequestBody Tarea tarea, @PathVariable int TareaId){
        try{
            Tarea tarea1 = tareaService.updateTarea(TareaId, tarea);
            System.out.println(tarea1.toString());
            return new ResponseEntity<>(tarea1,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "listaTarea/{IdTarea}")
    public ResponseEntity<Boolean> deleteTarea(@PathVariable("IdTarea") int IdTarea){
        Boolean flag = false;
        try {
            flag = tareaService.deleteTarea(IdTarea);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }

}
