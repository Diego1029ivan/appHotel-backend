package com.sistemahoteles.springboot.reservas.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemahoteles.springboot.reservas.models.entity.PrecioxTipoHabitacion;

public interface IPrecioxTipoHabitacionDao extends JpaRepository<PrecioxTipoHabitacion, Long> {

}
