package com.sistemahoteles.springboot.reservas.models.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemahoteles.springboot.reservas.models.dao.IPrecioxTipoHabitacionDao;
import com.sistemahoteles.springboot.reservas.models.entity.PrecioxTipoHabitacion;
import com.sistemahoteles.springboot.reservas.models.services.IPrecioxTHabitacionServices;

@Service
public class PrecioxTHabitacionServices implements IPrecioxTHabitacionServices{
   
	@Autowired
	private IPrecioxTipoHabitacionDao precioxTipoHabitacionDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public PrecioxTipoHabitacion findById(Long id) {

		return precioxTipoHabitacionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public PrecioxTipoHabitacion savePrecioxTipoHabitacion(PrecioxTipoHabitacion precioxTipoHabitacion) {
		
		return precioxTipoHabitacionDao.save(precioxTipoHabitacion);
	}

}
