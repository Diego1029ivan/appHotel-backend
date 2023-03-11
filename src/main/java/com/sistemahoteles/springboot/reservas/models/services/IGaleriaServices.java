package com.sistemahoteles.springboot.reservas.models.services;

import com.sistemahoteles.springboot.reservas.models.entity.Galeria;

public interface IGaleriaServices {
	public Galeria findById(Long id);

	public Galeria saveCochera(Galeria galeria);
}
