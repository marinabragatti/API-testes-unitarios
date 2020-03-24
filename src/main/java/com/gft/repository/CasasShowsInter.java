package com.gft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.model.Casa;

public interface CasasShowsInter extends JpaRepository<Casa, Long> {

	List <Casa> findByNomeCasa(String nomeCasa);
}
