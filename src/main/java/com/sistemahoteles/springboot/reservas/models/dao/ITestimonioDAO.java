package com.sistemahoteles.springboot.reservas.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemahoteles.springboot.reservas.models.entity.Testimonio;



public interface ITestimonioDAO extends JpaRepository<Testimonio, Long>{

}
