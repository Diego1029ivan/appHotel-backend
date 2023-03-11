package com.sistemahoteles.springboot.reservas.models.services;

import com.sistemahoteles.springboot.reservas.models.entity.Cochera;

public interface ICocheraServices {
	
   public Cochera findById(Long id);
   
   public Cochera saveCochera(Cochera cochera);
   
}
