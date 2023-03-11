package com.sistemahoteles.springboot.reservas.models.services;

import com.sistemahoteles.springboot.reservas.models.entity.Picina;

public interface IPicinaServices {
	
   public Picina findById(Long id);
   public Picina savePicina(Picina picina);
}
