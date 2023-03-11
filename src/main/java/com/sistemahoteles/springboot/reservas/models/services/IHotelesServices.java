package com.sistemahoteles.springboot.reservas.models.services;

import java.util.List;

import com.sistemahoteles.springboot.reservas.models.entity.Hotel;

public interface IHotelesServices {
	
	public List<Hotel> findAll();
	
	public Hotel findById(Long id);
	
	public void delete(Long id);
	
	public Hotel saveHotel(Hotel hotel);
	
	public List<Hotel> ListUsuarioxhotel(Long iduser);
	
	
}
