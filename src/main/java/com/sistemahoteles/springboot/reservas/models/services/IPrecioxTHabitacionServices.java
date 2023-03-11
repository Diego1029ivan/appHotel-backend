package com.sistemahoteles.springboot.reservas.models.services;

import com.sistemahoteles.springboot.reservas.models.entity.PrecioxTipoHabitacion;

public interface IPrecioxTHabitacionServices {

	public PrecioxTipoHabitacion findById(Long id);

	public PrecioxTipoHabitacion savePrecioxTipoHabitacion(PrecioxTipoHabitacion precioxTipoHabitacion);
}
