package com.sistemahoteles.springboot.reservas.models.services;

import java.util.List;

import com.sistemahoteles.springboot.reservas.models.entity.ReservaHotel;

public interface IReservasService {

		public List<ReservaHotel> findAll();
		
		public ReservaHotel findById(Long id);
		
		public ReservaHotel saveReserva(ReservaHotel reserva);
		
		public void delete (Long id);
}
