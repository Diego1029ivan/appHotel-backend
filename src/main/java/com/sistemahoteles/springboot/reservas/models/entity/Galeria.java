package com.sistemahoteles.springboot.reservas.models.entity;

import java.io.Serializable;

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
@Table(name = "galerias")
public class Galeria implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String foto;
	private String descripcionf1;

	private String foto2;
	private String descripcionf2;

	private String foto3;
	private String descripcionf3;
	
	private String descripcion;

	@ManyToOne(fetch = FetchType.EAGER) // CARGA PERESOSA
	@JoinColumn(name = "hoteles_id")
	@JsonIgnoreProperties(value={"galerias","hibernateLazyInitializer","handler"}, allowSetters = true)
	private Hotel hotel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getFoto2() {
		return foto2;
	}

	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}

	public String getFoto3() {
		return foto3;
	}

	public void setFoto3(String foto3) {
		this.foto3 = foto3;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	public String getDescripcionf1() {
		return descripcionf1;
	}

	public void setDescripcionf1(String descripcionf1) {
		this.descripcionf1 = descripcionf1;
	}

	public String getDescripcionf2() {
		return descripcionf2;
	}

	public void setDescripcionf2(String descripcionf2) {
		this.descripcionf2 = descripcionf2;
	}

	public String getDescripcionf3() {
		return descripcionf3;
	}

	public void setDescripcionf3(String descripcionf3) {
		this.descripcionf3 = descripcionf3;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
