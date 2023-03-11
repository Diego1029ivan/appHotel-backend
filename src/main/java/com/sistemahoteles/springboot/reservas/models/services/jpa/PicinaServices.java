package com.sistemahoteles.springboot.reservas.models.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemahoteles.springboot.reservas.models.dao.IPicinaDao;
import com.sistemahoteles.springboot.reservas.models.entity.Picina;
import com.sistemahoteles.springboot.reservas.models.services.IPicinaServices;
@Service
public class PicinaServices implements IPicinaServices {
	@Autowired
	private IPicinaDao picinaDao;

	@Override
	@Transactional(readOnly = true)
	public Picina findById(Long id) {
		return picinaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Picina savePicina(Picina picina) {
		return picinaDao.save(picina);
	}



}
