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
@Table(name = "picinas")
public class Picina implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "precio_picina")
	private Integer precioPicina;

	@Column(name = "horario_inicio")

	private String horarioInicio;

	@Column(name = "horario_cierre")

	private String horarioCierre;

	@Column(name = "descripcion_picina")
	private String descripcionPicina;

	@Column(name = "foto_picina")
	private String fotoPicina;
	
	@ManyToOne(fetch = FetchType.EAGER)//CARGA PERESOSA
    @JoinColumn(name="hoteles_id")
	@JsonIgnoreProperties(value={"picinas","hibernateLazyInitializer","handler"}, allowSetters = true)
	private Hotel hotel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPrecioPicina() {
		return precioPicina;
	}



	public String getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public String getHorarioCierre() {
		return horarioCierre;
	}

	public void setHorarioCierre(String horarioCierre) {
		this.horarioCierre = horarioCierre;
	}

	public void setPrecioPicina(Integer precioPicina) {
		this.precioPicina = precioPicina;
	}


	public String getDescripcionPicina() {
		return descripcionPicina;
	}

	public void setDescripcionPicina(String descripcionPicina) {
		this.descripcionPicina = descripcionPicina;
	}

	public String getFotoPicina() {
		return fotoPicina;
	}

	public void setFotoPicina(String fotoPicina) {
		this.fotoPicina = fotoPicina;
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
