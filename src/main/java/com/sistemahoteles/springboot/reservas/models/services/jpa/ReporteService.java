package com.sistemahoteles.springboot.reservas.models.services.jpa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemahoteles.springboot.reservas.componentes.JasperReportManager;
import com.sistemahoteles.springboot.reservas.enums.TipoReporteRnum;
import com.sistemahoteles.springboot.reservas.models.entity.ReporteReservasDTO;
import com.sistemahoteles.springboot.reservas.models.services.IReporteServices;

import net.sf.jasperreports.engine.JRException;

@Service
public class ReporteService implements IReporteServices{

	@Autowired
	private JasperReportManager reportManager;

	@Autowired
	private DataSource dataSource;
	
	@Override
	public ReporteReservasDTO obtenerReporteReserva(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "reporte_reservas";
		ReporteReservasDTO dto = new ReporteReservasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteRnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);//adjuntar nombre del archivo con la extension

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();//array de bytes
		dto.setStream(new ByteArrayInputStream(bs));//de out a un input
		dto.setLenght(bs.length);

		return dto;
	}
	
	@Override
	public ReporteReservasDTO obtenerReporteReservaUsuario(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "reporte_user_v1.1";
		ReporteReservasDTO dto = new ReporteReservasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteRnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);//adjuntar nombre del archivo con la extension

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();//array de bytes
		dto.setStream(new ByteArrayInputStream(bs));//de out a un input
		dto.setLenght(bs.length);

		return dto;
	}

}
