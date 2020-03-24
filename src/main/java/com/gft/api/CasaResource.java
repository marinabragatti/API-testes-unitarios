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

import com.gft.model.Casa;
import com.gft.service.CasasService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Casas de Show")
@RestController
@RequestMapping("/api/casas")
public class CasaResource {

	@Autowired
	private CasasService casasService;
	
	@ApiOperation("Listar as casas de show")
	@GetMapping
	public ResponseEntity<List<Casa>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(casasService.listar());
	}
	
	@ApiOperation("Salvar uma nova casa de show")
	@PostMapping
	public ResponseEntity<Void> salvar(@ApiParam(value = "Dados de uma Casa de Show") @Valid @RequestBody Casa casa){
		casa = casasService.salvar(casa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(casa.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation("Buscar uma casa de show pelo id")
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscar(@ApiParam(value = "ID de uma Casa de Show", example = "1") @PathVariable ("codigo") Long codigo) throws Exception{
		Casa casa = casasService.buscar(codigo);
		CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(casa);
	}
	
	@ApiOperation("Atualizar uma casa de show pelo id")
	@PutMapping("/{codigo}")
	public ResponseEntity<Void> atualizar(@ApiParam(value = "Dados de uma Casa de Show") @RequestBody Casa casa, @ApiParam(value = "ID de uma Casa de Show", example = "1") @PathVariable ("codigo") Long codigo) throws Exception{
		casa.setCodigo(codigo);
		casasService.atualizar(casa);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Deletar uma casa de show pelo id")
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@ApiParam(value = "ID de uma Casa de Show", example = "1") @PathVariable ("codigo") Long codigo) throws Exception{
		casasService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Listar as casas de show em ordem alfabética crescente")
	@GetMapping("/asc")
	public ResponseEntity<List<Casa>> listarCrescente(){
		return ResponseEntity.status(HttpStatus.OK).body(casasService.listarCrescente());
	}
	
	@ApiOperation("Listar as casas de show em ordem alfabética decrescente")
	@GetMapping("/desc")
	public ResponseEntity<List<Casa>> listarDecrescente(){
		return ResponseEntity.status(HttpStatus.OK).body(casasService.listarDecrescente());
	}
	
	@ApiOperation("Buscar uma casa de show pelo nome")
	@GetMapping("/nome/{nomeCasa}")
	public ResponseEntity<List<Casa>> pesquisa(@ApiParam(value = "Nome de uma Casa de Show", example = "Allianz Park") @PathVariable ("nomeCasa") String nomeCasa) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(casasService.pesquisar(null, nomeCasa));
	}
}
