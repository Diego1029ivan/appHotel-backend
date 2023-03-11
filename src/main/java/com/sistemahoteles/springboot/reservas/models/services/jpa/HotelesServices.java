package com.sistemahoteles.springboot.reservas.models.services.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemahoteles.springboot.reservas.models.dao.IHotelesDao;
import com.sistemahoteles.springboot.reservas.models.entity.Hotel;
import com.sistemahoteles.springboot.reservas.models.services.IHotelesServices;

@Service
public class HotelesServices implements IHotelesServices {
   
	@Autowired
	private IHotelesDao hoteldao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Hotel> findAll() {
		return (List<Hotel>)hoteldao.findAllHoteles();
	}

	@Override
	@Transactional(readOnly = true)
	public Hotel findById(Long id) {
		return hoteldao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		hoteldao.deleteById(id);
		
	}

	@Override
	@Transactional
	public Hotel saveHotel(Hotel hotel) {
		return hoteldao.save(hotel);
	}

	@Override
	public List<Hotel> ListUsuarioxhotel(Long iduser) {
		// TODO Auto-generated method stub
		return hoteldao.ListUsuarioxhotel(iduser);
	}




}
