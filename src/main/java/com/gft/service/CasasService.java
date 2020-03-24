package com.gft.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gft.model.Casa;
import com.gft.repository.CasasShowsInter;

@Service
public class CasasService {

	@Autowired
	private CasasShowsInter casaShowInter;
	
	public List<Casa> listar(){
		return casaShowInter.findAll();
	}
	
	public Casa salvar(Casa casa) {
		//casa.setCodigo(null);
		return casaShowInter.save(casa);
	}
	
	public Casa buscar(Long codigo) throws Exception {
		Casa casa = casaShowInter.findById(codigo).get();
		if(casa == null) {
			throw new Exception("Casa não encontrada.");
		}
		return casa;
	}
	
	public void atualizar(Casa casa) throws Exception {
		buscar(casa.getCodigo());
		casaShowInter.save(casa);
	}
	
	public void deletar(Long codigo) throws Exception {
		try {
			casaShowInter.deleteById(codigo);
		} catch (NoSuchElementException e){
			throw new Exception("Casa não encontrada.");
		}
	}

	public List<Casa> listarCrescente(){
		return casaShowInter.findAll(Sort.by(Sort.Direction.ASC, "nomeCasa"));
	}
	
	public List<Casa> listarDecrescente(){
		return casaShowInter.findAll(Sort.by(Sort.Direction.DESC, "nomeCasa"));
	}
	

	public List<Casa> pesquisar(final List<Casa> list, String nomeCasa) throws Exception{
		nomeCasa = nomeCasa.toLowerCase();
		List <Casa> casa = casaShowInter.findByNomeCasa(nomeCasa);
		if(casa.isEmpty()) {
			throw new Exception("Casa não encontrada.");
		}
		return casa;
	}
}
