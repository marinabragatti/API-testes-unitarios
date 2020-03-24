package com.gft.model;

public enum FormaPagto {

	CARTAOMASTER("Crédito Master"), 
	CARTAOVISA("Crédito Visa"), 
	CARTAODEBITO("Débito"),
	BOLETO("Boleto"),
	PAYPAL("PayPal");

	private String formaPagto;

	FormaPagto(String formaPagto) {
		this.formaPagto = formaPagto;
	}

	public String getFormaPagto() {
		return formaPagto;
	}

}