package com.sistemahoteles.springboot.reservas.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cocheras")
public class Cochera implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descripcion_cochera")
	private String descripcionCochera;

	@Column(name = "foto_cochera")
	private String fotoCochera;

	@ManyToOne(fetch = FetchType.EAGER) // CARGA PERESOSA
	@JoinColumn(name = "hoteles_id")
	@JsonIgnoreProperties(value={"cocheras","hibernateLazyInitializer","handler"}, allowSetters = true)
	private Hotel hotel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcionCochera() {
		return descripcionCochera;
	}

	public void setDescripcionCochera(String descripcionCochera) {
		this.descripcionCochera = descripcionCochera;
	}

	public String getFotoCochera() {
		return fotoCochera;
	}

	public void setFotoCochera(String fotoCochera) {
		this.fotoCochera = fotoCochera;
	}

	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
