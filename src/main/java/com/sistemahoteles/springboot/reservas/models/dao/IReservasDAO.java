package com.sistemahoteles.springboot.reservas.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistemahoteles.springboot.reservas.models.entity.ReservaHotel;

public interface IReservasDAO extends JpaRepository<ReservaHotel, Long>{
	@Query(value="SELECT *  FROM reservas_hoteles where hoteles_id=?1 " , nativeQuery = true)
	  public List<ReservaHotel> lisReservaxhotel(Long iduser);
}
