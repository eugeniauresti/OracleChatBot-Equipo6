package com.example.Microservice_OAD.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Microservice_OAD.repository.UsuarioRepository;
import com.example.Microservice_OAD.model.Usuario;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario saveUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> saveUsuarios(List<Usuario> usuarios) {
        return repository.saveAll(usuarios);
    }

    public List<Usuario> getUsuarios() {
        return repository.findAll();
    }

    public Usuario getIdUsuario(int IdUsuario) {
        return repository.findById(IdUsuario).orElse(null);
    }

    public Usuario getTelegramId(long telegramId) {
        return repository.findByTelegramId(telegramId).orElse(null);
    }



}