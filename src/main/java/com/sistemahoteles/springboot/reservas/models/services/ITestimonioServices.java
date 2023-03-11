package com.sistemahoteles.springboot.reservas.models.services;

import java.util.List;

import com.sistemahoteles.springboot.reservas.models.entity.Testimonio;


public interface ITestimonioServices {

	public List<Testimonio> findAll();
	
	public Testimonio findById(Long id);
	
	public Testimonio saveTestimonio(Testimonio testimonio);
	
	public void delete(Long id);
}
