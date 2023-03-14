package com.sistemahoteles.springboot.reservas.models.services;

import java.util.List;

import com.sistemahoteles.springboot.reservas.models.entity.Rating;



public interface IRatingServices {

	public List<Rating> findAll();
	
	
	public Rating saveRating(Rating rating);
}
