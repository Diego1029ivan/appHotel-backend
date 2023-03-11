package com.sistemahoteles.springboot.reservas.models.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemahoteles.springboot.reservas.models.dao.ICocheraDao;
import com.sistemahoteles.springboot.reservas.models.entity.Cochera;
import com.sistemahoteles.springboot.reservas.models.services.ICocheraServices;

@Service
public class CocheraService implements ICocheraServices {

	@Autowired
	private ICocheraDao cocheraDao;
	
	@Override
	@Transactional(readOnly = true)
	public Cochera findById(Long id) {

		return cocheraDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cochera saveCochera(Cochera cochera) {

		return cocheraDao.save(cochera);
	}


}
