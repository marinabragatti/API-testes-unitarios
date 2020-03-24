package com.gft.api;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.model.Evento;
import com.gft.service.EventosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Eventos")
@RestController
@RequestMapping("/api/eventos")
public class EventoResource {

	@Autowired
	private EventosService eventosService;
	
	@ApiOperation("Listar os eventos")
	@GetMapping
	public ResponseEntity<List<Evento>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.listar());
	}
	
	@ApiOperation("Salvar um novo evento")
	@PostMapping
	public ResponseEntity<Void> salvar(@ApiParam(value = "Dados de um Evento") @Valid @RequestBody Evento evento){
		evento = eventosService.salvar(evento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(evento.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation("Buscar um evento pelo id")
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscar(@ApiParam(value = "ID de um Evento", example = "1") @PathVariable ("codigo") Long codigo) throws Exception{
		Evento evento = eventosService.buscar(codigo);
		CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(evento);
	}
	
	@ApiOperation("Atualizar um evento pelo id")
	@PutMapping("/{codigo}")
	public ResponseEntity<Void> atualizar(@ApiParam(value = "Dados de um Evento") @RequestBody Evento evento, @ApiParam(value = "ID de um Evento", example = "1") @PathVariable ("codigo") Long codigo) throws Exception{
		evento.setCodigo(codigo);
		eventosService.atualizar(evento);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Deletar um evento pelo id")
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@ApiParam(value = "ID de um Evento", example = "1") @PathVariable Long codigo) throws Exception{
		eventosService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Listar os eventos em ordem crescente de capacidade")
	@GetMapping("/capacidade/asc")
	public ResponseEntity<List<Evento>> listarCapCrescente(){
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.listarCapCrescente());
	}
	
	@ApiOperation("Listar os eventos em ordem decrescente de capacidade")
	@GetMapping("/capacidade/desc")
	public ResponseEntity<List<Evento>> listarCapDecrescente(){
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.listarCapDecrescente());
	}
	
	@ApiOperation("Listar os eventos em ordem crescente pela data do evento")
	@GetMapping("/data/asc")
	public ResponseEntity<List<Evento>> listarDataCrescente(){
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.listarDataCrescente());
	}
	
	@ApiOperation("Listar os eventos em ordem decrescente pela data do evento")
	@GetMapping("/data/desc")
	public ResponseEntity<List<Evento>> listarDataDecrescente(){
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.listarDataDecrescente());
	}
	
	@ApiOperation("Listar os eventos em ordem alfabética crescente")
	@GetMapping("/nome/asc")
	public ResponseEntity<List<Evento>> listarNomeCrescente(){
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.listarNomeCrescente());
	}
	
	@ApiOperation("Listar os eventos em ordem alfabética decrescente")
	@GetMapping("/nome/desc")
	public ResponseEntity<List<Evento>> listarNomeDecrescente(){
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.listarNomeDecrescente());
	}
	
	@ApiOperation("Listar os eventos em ordem crescente de preço")
	@GetMapping("/preco/asc")
	public ResponseEntity<List<Evento>> listarValorCrescente(){
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.listarValorCrescente());
	}
	
	@ApiOperation("Listar os eventos em ordem decrescente de preço")
	@GetMapping("/preco/desc")
	public ResponseEntity<List<Evento>> listarValorDecrescente(){
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.listarValorDecrescente());
	}
}
