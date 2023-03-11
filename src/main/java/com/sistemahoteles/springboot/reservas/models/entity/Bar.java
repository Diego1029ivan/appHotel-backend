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
@Table(name = "bar")
public class Bar implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombrebar;

	
	@Column(name = "descripcion_bar")
	private String descripcionBar;

	@Column(name = "foto_bar")
	private String fotoBar;
	
	@ManyToOne(fetch = FetchType.EAGER)//CARGA PERESOSA
    @JoinColumn(name="hoteles_id")
	@JsonIgnoreProperties(value={"bar","hibernateLazyInitializer", "handler"}, allowSetters = true )
	private Hotel hotel;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcionBar() {
		return descripcionBar;
	}

	public void setDescripcionBar(String descripcionBar) {
		this.descripcionBar = descripcionBar;
	}

	public String getFotoBar() {
		return fotoBar;
	}

	public void setFotoBar(String fotoBar) {
		this.fotoBar = fotoBar;
	}

	
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getNombrebar() {
		return nombrebar;
	}

	public void setNombrebar(String nombrebar) {
		this.nombrebar = nombrebar;
	}
     
	
	public Bar(String nombrebar, String descripcionBar, Hotel hotel) {
		super();
		this.nombrebar = nombrebar;
		this.descripcionBar = descripcionBar;
		
		this.hotel = hotel;
	}


	public Bar() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
