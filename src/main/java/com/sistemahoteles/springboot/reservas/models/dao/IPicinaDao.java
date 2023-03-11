package com.sistemahoteles.springboot.reservas.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemahoteles.springboot.reservas.models.entity.Picina;

public interface IPicinaDao extends JpaRepository<Picina, Long> {

}
