package com.sistemahoteles.springboot.reservas.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemahoteles.springboot.reservas.models.entity.Cochera;

public interface ICocheraDao extends JpaRepository<Cochera, Long> {

}
