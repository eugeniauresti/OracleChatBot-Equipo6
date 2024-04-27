package com.example.Microservice_OAD.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.example.Microservice_OAD.model.Usuario;

@Repository
@Transactional
@EnableTransactionManagement
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByTelegramId(Long telegramId);  
} 
