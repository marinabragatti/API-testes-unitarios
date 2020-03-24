package com.gft.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Evento {

	@ApiModelProperty(value = "ID do Evento", example = "1")
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ApiModelProperty(value = "Nome do Evento", example = "Show da Anitta")
	@NotEmpty(message = "Nome do Evento inválido")
	private String nomeEvento;
	
	@ApiModelProperty(value = "Capacidade de pessoas no evento", example = "100")
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Valores inválidos")
	@NumberFormat(pattern = "#,##0")
	@Min(value = 0L, message = "A capacidade deve ser positiva")
	private int capacidade;
	
	@ApiModelProperty(value = "Data do Evento", example = "01/02/2020")
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Data inválida")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataEvento;
	
	@ApiModelProperty(value = "Valor do ingresso", example = "10.99")
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Valor do ingresso inválido")
	@NumberFormat(pattern = "#,##0.00")
	@DecimalMin(value = "0.01", message = "O valor não pode ser menor que R$0,01")
	private BigDecimal valorIngresso;
	
	@ApiModelProperty(value = "Informações da Casa de Show", example = "1")
	@JsonInclude(Include.NON_NULL)
	@ManyToOne
	@JoinColumn(name = "casa_show_codigo")
	private Casa casaShow;
	
	@ApiModelProperty(value = "Gênero musical do Evento", example = "ROCK")
	@Enumerated(EnumType.STRING)
	private GeneroMusical genero;
	
		
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNomeEvento() {
		return nomeEvento;
	}
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public Date getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}
	public BigDecimal getValorIngresso() {
		return valorIngresso;
	}
	public void setValorIngresso(BigDecimal valorIngresso) {
		this.valorIngresso = valorIngresso;
	}
	public Casa getCasaShow() {
		return casaShow;
	}
	public void setCasaShow(Casa casaShow) {
		this.casaShow = casaShow;
	}
	public GeneroMusical getGenero() {
		return genero;
	}
	public void setGenero(GeneroMusical genero) {
		this.genero = genero;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacidade;
		result = prime * result + ((casaShow == null) ? 0 : casaShow.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((dataEvento == null) ? 0 : dataEvento.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((nomeEvento == null) ? 0 : nomeEvento.hashCode());
		result = prime * result + ((valorIngresso == null) ? 0 : valorIngresso.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (capacidade != other.capacidade)
			return false;
		if (casaShow == null) {
			if (other.casaShow != null)
				return false;
		} else if (!casaShow.equals(other.casaShow))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataEvento == null) {
			if (other.dataEvento != null)
				return false;
		} else if (!dataEvento.equals(other.dataEvento))
			return false;
		if (genero != other.genero)
			return false;
		if (nomeEvento == null) {
			if (other.nomeEvento != null)
				return false;
		} else if (!nomeEvento.equals(other.nomeEvento))
			return false;
		if (valorIngresso == null) {
			if (other.valorIngresso != null)
				return false;
		} else if (!valorIngresso.equals(other.valorIngresso))
			return false;
		return true;
	}
	
	
	
}
