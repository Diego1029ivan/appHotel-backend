package com.sistemahoteles.springboot.reservas.models.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "hoteles")
@SQLDelete(sql = "UPDATE hoteles SET estado_hotel=0 WHERE id=?")
@Where(clause = "estado_hotel = 1")
public class Hotel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "no puede estar vacio")
	@Column(name = "nombre_hotel", unique = true)
	private String nombre;

	private Integer ruc;

	private String logo;

	@Column(name = "cantidad_habitacion")
	private Integer cantidadHabitacion;

	@Column(name = "descripcion_hotel")
	private String descripcionHotel;

	@Column(name = "estado_hotel")
	private Integer estadoHotel = 1;

	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties(value={ "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ubicacion_id")
	@JsonIgnoreProperties(value={ "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Ubicacion ubicacion;
     
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "hotel", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value={"hotel","hibernateLazyInitializer","handler"}, allowSetters = true)
	private Set<Bar> bares = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "hotel", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value={"hotel","hibernateLazyInitializer","handler"}, allowSetters = true)
	private Set<Cochera> cocheras = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "hotel", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value={"hotel","hibernateLazyInitializer","handler"}, allowSetters = true)
	private Set<Picina> piscinas = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "hotel", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value={"hotel","hibernateLazyInitializer","handler"}, allowSetters = true)
	private Set<Galeria> galeria = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "hotel", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value={"hotel","hibernateLazyInitializer","handler"}, allowSetters = true)
	private Set<PrecioxTipoHabitacion> precioxtipohabitacion = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getRuc() {
		return ruc;
	}

	public void setRuc(Integer ruc) {
		this.ruc = ruc;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getCantidadHabitacion() {
		return cantidadHabitacion;
	}

	public void setCantidadHabitacion(Integer cantidadHabitacion) {
		this.cantidadHabitacion = cantidadHabitacion;
	}

	public String getDescripcionHotel() {
		return descripcionHotel;
	}

	public void setDescripcionHotel(String descripcionHotel) {
		this.descripcionHotel = descripcionHotel;
	}

	public Integer getEstadoHotel() {
		return estadoHotel;
	}

	public void setEstadoHotel(Integer estadoHotel) {
		this.estadoHotel = estadoHotel;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Bar> getBares() {
		return bares;
	}

	public void setBares(Set<Bar> bares) {
		this.bares = bares;
	}

	public Set<Cochera> getCocheras() {
		return cocheras;
	}

	public void setCocheras(Set<Cochera> cocheras) {
		this.cocheras = cocheras;
	}

	public Set<Picina> getPiscinas() {
		return piscinas;
	}

	public void setPiscinas(Set<Picina> piscinas) {
		this.piscinas = piscinas;
	}

	public Set<Galeria> getGaleria() {
		return galeria;
	}

	public void setGaleria(Set<Galeria> galeria) {
		this.galeria = galeria;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Set<PrecioxTipoHabitacion> getPrecioxtipohabitacion() {
		return precioxtipohabitacion;
	}

	public void setPrecioxtipohabitacion(Set<PrecioxTipoHabitacion> precioxtipohabitacion) {
		this.precioxtipohabitacion = precioxtipohabitacion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
