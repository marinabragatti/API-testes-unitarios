package com.gft.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.model.Usuario;
import com.gft.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Usuários")
@RestController
@RequestMapping("/api/users")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	@ApiOperation("Listar os usuários")
	@GetMapping
	public ResponseEntity<List<Usuario>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listar());
	}
	
	@ApiOperation("Buscar um usuário pelo id")
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscar(@ApiParam(value = "ID de um usuário", example = "1") @PathVariable ("codigo") Long codigo) throws Exception{
		Usuario usuario = usuarioService.buscar(codigo);
		CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(usuario);
	}
}
