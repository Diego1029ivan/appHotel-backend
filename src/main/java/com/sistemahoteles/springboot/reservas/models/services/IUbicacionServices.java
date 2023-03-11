package com.sistemahoteles.springboot.reservas.models.services;


import java.util.List;

import com.sistemahoteles.springboot.reservas.models.entity.Ubicacion;

public interface IUbicacionServices {
	
	public List<Ubicacion> findAll();

	public Ubicacion findById(Long id);
	   
	public Ubicacion saveUbicacion(Ubicacion ubicacion);
}