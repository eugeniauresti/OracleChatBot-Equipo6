package com.example.Microservice_OAD.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Microservice_OAD.model.Desarrollador;
import com.example.Microservice_OAD.repository.DesarrolladorRepository;


@Service
public class DesarrolladorService {

    @Autowired
    private DesarrolladorRepository desarrolladorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Desarrollador> findAll(){
        List<Desarrollador> desarrolladores = desarrolladorRepository.findAll();
        return desarrolladores;
    }

    public Desarrollador getDesarrolladorPorIdUsuario(int idUsuario) {
        // Crea la consulta
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Desarrollador> cq = cb.createQuery(Desarrollador.class);
        Root<Desarrollador> root = cq.from(Desarrollador.class);

        // Añade la condición de que el IdUsuario debe ser igual al proporcionado
        cq.where(cb.equal(root.get("usuario").get("IdUsuario"), idUsuario));

        // Ejecuta la consulta y devuelve el resultado
        return entityManager.createQuery(cq).getSingleResult();
    }
}

