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

import com.gft.model.Compra;
import com.gft.service.CompraService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Vendas de Ingresso")
@RestController
@RequestMapping("/api/vendas")
public class CompraResource {

	@Autowired
	private CompraService compraService;
	
	@ApiOperation("Listar as vendas de eventos")
	@GetMapping
	public ResponseEntity<List<Compra>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(compraService.listar());
	}
	
	@ApiOperation("Listar as vendas de eventos pelo id")
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscar(@ApiParam(value = "ID de uma Compra", example = "1") @PathVariable ("codigo") Long codigo) throws Exception{
		Compra compra = compraService.buscar(codigo);
		CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(compra);
	}
}
