package com.sistemahoteles.springboot.reservas.models.services.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemahoteles.springboot.reservas.models.dao.IReservasDAO;
import com.sistemahoteles.springboot.reservas.models.entity.ReservaHotel;
import com.sistemahoteles.springboot.reservas.models.services.IReservasService;

@Service
public class ReservaServices implements IReservasService{

	@Autowired
	private IReservasDAO reservasDao;
	@Override
	public List<ReservaHotel> findAll() {
		
		return (List<ReservaHotel>)reservasDao.findAll();
	}

	@Override
	public ReservaHotel findById(Long id) {
		
		return reservasDao.findById(id).orElse(null);
	}

	@Override
	public ReservaHotel saveReserva(ReservaHotel reserva) {
		// TODO Auto-generated method stub
		return reservasDao.save(reserva);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

		
}
