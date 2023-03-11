package com.sistemahoteles.springboot.reservas.models.services.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemahoteles.springboot.reservas.models.dao.ITestimonioDAO;
import com.sistemahoteles.springboot.reservas.models.entity.Testimonio;
import com.sistemahoteles.springboot.reservas.models.services.ITestimonioServices;

@Service
public class TestimonioServices implements ITestimonioServices{

	@Autowired
	private ITestimonioDAO testimonioDao;
	
	@Override
	public List<Testimonio> findAll() {
		return (List<Testimonio>)testimonioDao.findAll();
	}

	@Override
	public Testimonio saveTestimonio(Testimonio testimonio) {
		
		return testimonioDao.save(testimonio);
	}

	@Override
	public void delete(Long id) {
		testimonioDao.deleteById(id);
		
	}

	@Override
	public Testimonio findById(Long id) {
		
		return testimonioDao.findById(id).orElse(null);
	}

}
