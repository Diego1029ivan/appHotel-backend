package com.sistemahoteles.springboot.reservas.models.services.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemahoteles.springboot.reservas.models.dao.ITipoPagoDAO;
import com.sistemahoteles.springboot.reservas.models.entity.TipoPago;
import com.sistemahoteles.springboot.reservas.models.services.ITipoPagoServices;

@Service
public class TipoPagoServices implements ITipoPagoServices {

	@Autowired
	private ITipoPagoDAO tipoPagoDAO;

	@Override
	public TipoPago findById(Long id) {
		
		return tipoPagoDAO.findById(id).orElse(null);
	}

	@Override
	public List<TipoPago> findAll() {
		// TODO Auto-generated method stub
		return (List<TipoPago>)tipoPagoDAO.findAll();
	}

}
