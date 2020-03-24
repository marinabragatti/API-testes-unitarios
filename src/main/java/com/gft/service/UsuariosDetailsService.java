package com.gft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gft.model.Usuario;
import com.gft.model.UsuarioUserDetails;
import com.gft.repository.UsuarioInter;

@Service
public class UsuariosDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioInter usuarioInter;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioInter.findByUsername(username);
		if(usuario==null)
			throw new UsernameNotFoundException("Usuário não cadastrado");
		
		return new UsuarioUserDetails(usuario);
	}

}
