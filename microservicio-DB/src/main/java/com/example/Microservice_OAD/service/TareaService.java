package com.example.Microservice_OAD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Microservice_OAD.repository.TareaRepository;
import com.example.Microservice_OAD.model.Tarea;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {
    
    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> findAll(){
        List<Tarea> tareas = tareaRepository.findAll();
        return tareas;
    }

    public ResponseEntity<Tarea> getTareaById(int IdTarea) {
        Optional<Tarea> tareaData = tareaRepository.findById(IdTarea);
        if(tareaData.isPresent()) {
            return new ResponseEntity<>(tareaData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Tarea addTarea(Tarea tarea) {
        return tareaRepository.save(tarea); 
    }

    public boolean deleteTarea(int IdTarea){
        try{
            tareaRepository.deleteById(IdTarea);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public Tarea getTitulo(String titulo) {
        return tareaRepository.findByTitulo(titulo).orElse(null);
    }

    public List<Tarea> getTareasPorUsuario(String usuario) {
        return tareaRepository.findByResponsable(usuario);
    }

    public Tarea updateTarea(int IdTarea, Tarea td) {
        Optional<Tarea> tareaItemData = tareaRepository.findById(IdTarea);
        if(tareaItemData.isPresent()) {
            Tarea tarea = tareaItemData.get();
            tarea.setIdTarea(IdTarea);
            tarea.setDesarrollador(td.getDesarrollador());
            tarea.setProyecto(td.getProyecto());
            tarea.setSprint(td.getSprint());
            tarea.setTitulo(td.getTitulo());
            tarea.setDescripcion(td.getDescripcion());
            tarea.setFechaInicio(td.getFechaInicio());
            tarea.setFechaFinTarea(td.getFechaFinTarea());
            tarea.setCreadoPor(td.getCreadoPor());
            tarea.setResponsable(td.getResponsable());
            tarea.setEstatusTarea(td.getEstatusTarea());
            tarea.setPrioridad(td.getPrioridadTarea());
            tarea.setIsActive(td.getIsActive());
            return tareaRepository.save(tarea);
        }
        else {
            return null;
        }
    }

}
