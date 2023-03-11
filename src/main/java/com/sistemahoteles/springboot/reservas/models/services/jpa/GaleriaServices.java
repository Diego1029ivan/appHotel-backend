package com.sistemahoteles.springboot.reservas.models.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemahoteles.springboot.reservas.models.dao.IGaleriaDao;
import com.sistemahoteles.springboot.reservas.models.entity.Galeria;
import com.sistemahoteles.springboot.reservas.models.services.IGaleriaServices;

@Service
public class GaleriaServices implements IGaleriaServices{
	
	@Autowired
	private IGaleriaDao galeriaDao;

	@Override
	@Transactional(readOnly = true)
	public Galeria findById(Long id) {
	
		return galeriaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Galeria saveCochera(Galeria galeria) {
		
		return galeriaDao.save(galeria);
	}

}
