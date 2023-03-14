package com.sistemahoteles.springboot.reservas.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;


import com.sistemahoteles.springboot.reservas.models.entity.Rating;




public interface IRatingDAO extends JpaRepository<Rating, Long>{
	
	
}
