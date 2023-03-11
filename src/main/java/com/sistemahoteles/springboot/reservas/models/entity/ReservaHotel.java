package com.sistemahoteles.springboot.reservas.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reservas_hoteles")
public class ReservaHotel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_entrada")
	@Temporal(TemporalType.DATE)
	private Date fechaEntrada;

	@Column(name = "fecha_salida")
	@Temporal(TemporalType.DATE)
	private Date fechaSalida;

	@Column(name = "adelanto_reserva")
	private Integer adelantoReservas;
	
	private Integer estado;
	
	private Integer cantidadHab;
	private Integer cantidad_dias;
	
	private double costo_total;

	@ManyToOne(fetch = FetchType.EAGER) // CARGA PERESOSA
	@JoinColumn(name = "hoteles_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Hotel hotel;
	
	@ManyToOne(fetch = FetchType.EAGER) // CARGA PERESOSA
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.EAGER) // CARGA PERESOSA
	@JoinColumn(name = "tipopago_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private TipoPago tipoPago;
	
	@ManyToOne(fetch = FetchType.EAGER) // CARGA PERESOSA
	@JoinColumn(name = "precio_tipoHabitacion")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private PrecioxTipoHabitacion tipohab ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Integer getAdelantoReservas() {
		return adelantoReservas;
	}

	public void setAdelantoReservas(Integer adelantoReservas) {
		this.adelantoReservas = adelantoReservas;
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


	public TipoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}


	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}


	public Integer getCantidadHab() {
		return cantidadHab;
	}

	public void setCantidadHab(Integer cantidadHab) {
		this.cantidadHab = cantidadHab;
	}

	public PrecioxTipoHabitacion getTipohab() {
		return tipohab;
	}

	public void setTipohab(PrecioxTipoHabitacion tipohab) {
		this.tipohab = tipohab;
	}
	


	public Integer getCantidad_dias() {
		return cantidad_dias;
	}

	public void setCantidad_dias(Integer cantidad_dias) {
		this.cantidad_dias = cantidad_dias;
	}

	public double getCosto_total() {
		return costo_total;
	}

	public void setCosto_total(double costo_total) {
		this.costo_total = costo_total;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
