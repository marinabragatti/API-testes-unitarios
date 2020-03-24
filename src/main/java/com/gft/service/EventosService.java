package com.gft.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gft.model.Evento;
import com.gft.repository.EventosInter;

@Service
public class EventosService {

	@Autowired
	private EventosInter eventosInter;
	
	public List<Evento> listar(){
		return eventosInter.findAll();
	}
	
	public Evento salvar(Evento evento) {
		evento.setCodigo(null);
		return eventosInter.save(evento);
	}
	
	public Evento buscar(Long id) throws Exception {
		Evento evento = eventosInter.findById(id).get();
		if(evento == null) {
			throw new Exception("Evento não encontrado.");
		}
		return evento;
	}
	
	public void atualizar(Evento evento) throws Exception {
		buscar(evento.getCodigo());
		eventosInter.save(evento);
	}
	
	public void deletar(Long codigo) throws Exception {
		try {
			eventosInter.deleteById(codigo);
		} catch (NoSuchElementException e){
			throw new Exception("Evento não encontrado.");
		}
	}
	
	public List<Evento> listarCapCrescente(){
		return eventosInter.findAll(Sort.by(Sort.Direction.ASC, "capacidade"));
	}
	
	public List<Evento> listarCapDecrescente(){
		return eventosInter.findAll(Sort.by(Sort.Direction.DESC, "capacidade"));
	}
	
	public List<Evento> listarDataCrescente(){
		return eventosInter.findAll(Sort.by(Sort.Direction.ASC, "dataEvento"));
	}
	
	public List<Evento> listarDataDecrescente(){
		return eventosInter.findAll(Sort.by(Sort.Direction.DESC, "dataEvento"));
	}
	
	public List<Evento> listarNomeCrescente(){
		return eventosInter.findAll(Sort.by(Sort.Direction.ASC, "nomeEvento"));
	}
	
	public List<Evento> listarNomeDecrescente(){
		return eventosInter.findAll(Sort.by(Sort.Direction.DESC, "nomeEvento"));
	}
	
	public List<Evento> listarValorCrescente(){
		return eventosInter.findAll(Sort.by(Sort.Direction.ASC, "valorIngresso"));
	}
	
	public List<Evento> listarValorDecrescente(){
		return eventosInter.findAll(Sort.by(Sort.Direction.DESC, "valorIngresso"));
	}
}
