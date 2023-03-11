package com.sistemahoteles.springboot.reservas.models.services.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemahoteles.springboot.reservas.models.dao.IUbicacionesDao;
import com.sistemahoteles.springboot.reservas.models.entity.Ubicacion;
import com.sistemahoteles.springboot.reservas.models.services.IUbicacionServices;

@Service
public class UbicacionServices implements IUbicacionServices {

	@Autowired
	private IUbicacionesDao ubicacionDao;
	
	@Override
	@Transactional(readOnly = true)
	public Ubicacion findById(Long id) {

		return ubicacionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Ubicacion saveUbicacion(Ubicacion ubicacion) {
	
		return ubicacionDao.save(ubicacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ubicacion> findAll() {
		
		return ubicacionDao.findAll();
	}

}