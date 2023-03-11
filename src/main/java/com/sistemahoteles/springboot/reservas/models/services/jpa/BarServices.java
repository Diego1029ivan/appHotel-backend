package com.sistemahoteles.springboot.reservas.models.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemahoteles.springboot.reservas.models.dao.IBarDao;
import com.sistemahoteles.springboot.reservas.models.entity.Bar;
import com.sistemahoteles.springboot.reservas.models.services.IBarServices;
@Service
public class BarServices implements IBarServices{

	@Autowired
	private IBarDao barDao;
	
	@Override
	@Transactional(readOnly = true)
	public Bar findById(Long id) {

		return barDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Bar saveBar(Bar bar) {

		return barDao.save(bar);
	}

}
