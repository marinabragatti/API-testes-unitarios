package com.gft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gft.model.Usuario;

public interface UsuarioInter extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);
	
	@Query("from Usuario where username=?1")
	public List<Usuario> buscarUsuario(String username);
}
