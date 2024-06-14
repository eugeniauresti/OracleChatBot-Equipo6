package com.example.Microservice_OAD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Microservice_OAD.model.Usuario;
import com.example.Microservice_OAD.service.UsuarioService;


@RestController
public class UsuarioController {
    
    @Autowired
	private UsuarioService service;

	@PostMapping("/addUsuario")
	public Usuario addUsuario(@RequestBody Usuario usuario){
		return service.saveUsuario(usuario);
	}

	@PostMapping("/addUsuarios")
	public java.util.List<Usuario> addUsuarios(@RequestBody java.util.List<Usuario> usuarios){
		return service.saveUsuarios(usuarios);
	}

	@GetMapping("/usuarios")
	public java.util.List<Usuario> finUsuarios() {
		return service.getUsuarios();
	}

	@GetMapping("/usuarioPorId/{IdUsuario}")
	public Usuario findUsuario(@PathVariable int IdUsuario){
		return service.getIdUsuario(IdUsuario);
	}

	@GetMapping("/telegramId/{telegramId}")
	public Usuario findTelegramId(@PathVariable int telegramId){
		return service.getIdUsuario(telegramId);
	}
    

}
