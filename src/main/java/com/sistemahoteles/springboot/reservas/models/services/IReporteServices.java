package com.sistemahoteles.springboot.reservas.models.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.sistemahoteles.springboot.reservas.models.entity.ReporteReservasDTO;


import net.sf.jasperreports.engine.JRException;

public interface IReporteServices {

		ReporteReservasDTO obtenerReporteReserva(Map<String, Object> params) throws JRException, IOException,SQLException;
		ReporteReservasDTO obtenerReporteReservaUsuario(Map<String, Object> params) throws JRException, IOException,SQLException;
}
