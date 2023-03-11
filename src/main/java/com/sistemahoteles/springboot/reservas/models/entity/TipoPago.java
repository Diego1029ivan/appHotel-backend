package com.sistemahoteles.springboot.reservas.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_pago")
public class TipoPago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "detalle_tipo", unique = true, length = 20)
	private String detalle_tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetalle_tipo() {
		return detalle_tipo;
	}

	public void setDetalle_tipo(String detalle_tipo) {
		this.detalle_tipo = detalle_tipo;
	}
	
	
}
