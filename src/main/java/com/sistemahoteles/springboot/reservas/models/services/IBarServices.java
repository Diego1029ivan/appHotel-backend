package com.sistemahoteles.springboot.reservas.models.services;

import com.sistemahoteles.springboot.reservas.models.entity.Bar;

public interface IBarServices {
	
   public Bar findById(Long id);
   
   public Bar saveBar(Bar bar);
}
