package com.gft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.model.Compra;
import com.gft.repository.CompraInter;

@Service
public class CompraService {

	@Autowired
	private CompraInter compraInter;
	
	public List<Compra> listar(){
		return compraInter.findAll();
	}
	
	public Compra buscar(Long id) throws Exception {
		Compra compra = compraInter.findById(id).get();
		if(compra == null) {
			throw new Exception("Compra não encontrada.");
		}
		return compra;
	}
}
