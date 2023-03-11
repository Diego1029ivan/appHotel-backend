package com.sistemahoteles.springboot.reservas.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemahoteles.springboot.reservas.models.entity.Galeria;

public interface IGaleriaDao extends JpaRepository<Galeria, Long>{

}
