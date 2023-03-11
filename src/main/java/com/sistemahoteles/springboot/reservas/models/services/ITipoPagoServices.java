package com.sistemahoteles.springboot.reservas.models.services;

import java.util.List;

import com.sistemahoteles.springboot.reservas.models.entity.TipoPago;

public interface ITipoPagoServices {

	public TipoPago findById(Long id);

	public List<TipoPago> findAll();
}
