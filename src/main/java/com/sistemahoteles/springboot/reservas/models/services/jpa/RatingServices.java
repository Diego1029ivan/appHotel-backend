package com.sistemahoteles.springboot.reservas.models.services.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemahoteles.springboot.reservas.models.dao.IRatingDAO;
import com.sistemahoteles.springboot.reservas.models.entity.Rating;
import com.sistemahoteles.springboot.reservas.models.services.IRatingServices;

@Service
public class RatingServices implements IRatingServices{

	
	@Autowired
	private IRatingDAO ratingDao;
	@Override
	public List<Rating> findAll() {
		
		return (List<Rating>)ratingDao.findAll();
	}

	
	@Override
	public Rating saveRating(Rating rating) {
		// TODO Auto-generated method stub
		return ratingDao.save(rating);
	}
	

	

}
