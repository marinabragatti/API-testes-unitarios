package com.gft.model;

public enum GeneroMusical {
	
	POP("Pop"),
	MPB("MPB"),
	AXE("Axé"),
	SERTANEJO("Sertanejo"),
	ROCK("Rock"),
	SAMBA("Samba"),
	PAGODE("Pagode"),
	ELETRONICO("Eletrônico"),
	FUNK("Funk");
	
	private String generos;
	
	GeneroMusical(String generos){
		this.generos = generos;
	}
	
	public String getGeneros() {
		return generos;
	}
}
