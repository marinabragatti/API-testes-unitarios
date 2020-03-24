package com.gft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.model.Usuario;
import com.gft.repository.UsuarioInter;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioInter usuarioInter;
	
	public List<Usuario> listar(){
		return usuarioInter.findAll();
	}
	
	public Usuario buscar(Long codigo) throws Exception {
		Usuario usuario = usuarioInter.findById(codigo).get();
		if(usuario == null) {
			throw new Exception("Usuário não encontrada.");
		}
		return usuario;
	}
}
