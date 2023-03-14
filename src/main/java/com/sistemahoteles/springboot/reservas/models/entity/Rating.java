package com.sistemahoteles.springboot.reservas.models.entity;




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
@Table(name = "rating")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double clasificacion;
	private String detalle;

	@ManyToOne(fetch = FetchType.EAGER) // CARGA PERESOSA
	@JoinColumn(name = "hoteles_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Hotel hotel;
	
	@ManyToOne(fetch = FetchType.EAGER) // CARGA PERESOSA
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario usuario;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Double clasificacion) {
		this.clasificacion = clasificacion;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
	
	
	
	
}
