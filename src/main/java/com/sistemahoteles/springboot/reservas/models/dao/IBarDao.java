package com.sistemahoteles.springboot.reservas.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemahoteles.springboot.reservas.models.entity.Bar;

public interface IBarDao extends JpaRepository<Bar, Long>{

}
