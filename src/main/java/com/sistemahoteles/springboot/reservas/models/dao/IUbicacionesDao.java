package com.sistemahoteles.springboot.reservas.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemahoteles.springboot.reservas.models.entity.Ubicacion;


public interface IUbicacionesDao extends JpaRepository<Ubicacion, Long>{

}
