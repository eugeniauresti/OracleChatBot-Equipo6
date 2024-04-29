package com.example.Microservice_OAD.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Microservice_OAD.model.Tarea;

@Repository
@Transactional
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    List<Tarea> findByResponsable(String responsable);
}

