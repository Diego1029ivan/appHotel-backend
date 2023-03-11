package com.sistemahoteles.springboot.reservas.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemahoteles.springboot.reservas.models.entity.ReservaHotel;

public interface IReservasDAO extends JpaRepository<ReservaHotel, Long>{

}
