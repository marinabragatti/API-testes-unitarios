package com.gft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.model.Evento;

public interface EventosInter extends JpaRepository<Evento, Long>{

}
