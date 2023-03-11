package com.sistemahoteles.springboot.reservas.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="ubicaciones")
public class Ubicacion implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "pais")
	private String pais;
	
	@Column(name = "departamento")
	private String departamento;
	
	@Column(name = "ciudad")
	private String ciudad;
	
	@Column(name = "descripcion_ciudad")
	private String descripcionCiudad;
	
	@Column(name = "foto_ciudad")
	private String fotoCiudad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDescripcionCiudad() {
		return descripcionCiudad;
	}

	public void setDescripcionCiudad(String descripcionCiudad) {
		this.descripcionCiudad = descripcionCiudad;
	}

	public String getFotoCiudad() {
		return fotoCiudad;
	}

	public void setFotoCiudad(String fotoCiudad) {
		this.fotoCiudad = fotoCiudad;
	}

	public Ubicacion(String pais, String departamento, String ciudad, String descripcionCiudad, String fotoCiudad) {
		super();
		this.pais = pais;
		this.departamento = departamento;
		this.ciudad = ciudad;
		this.descripcionCiudad = descripcionCiudad;
		this.fotoCiudad = fotoCiudad;
	}

	public Ubicacion() {
		super();
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
}
