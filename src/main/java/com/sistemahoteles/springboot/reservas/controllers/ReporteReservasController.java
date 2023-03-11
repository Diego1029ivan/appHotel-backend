package com.sistemahoteles.springboot.reservas.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistemahoteles.springboot.reservas.enums.TipoReporteRnum;
import com.sistemahoteles.springboot.reservas.models.entity.ReporteReservasDTO;
import com.sistemahoteles.springboot.reservas.models.services.jpa.EmailService;
import com.sistemahoteles.springboot.reservas.models.services.jpa.ReporteService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/reporte")
public class ReporteReservasController {

	@Autowired
	private ReporteService reporteReservas;
	
	@Autowired
	private EmailService emailService;

	@GetMapping(path = "/reservas/download")
	public ResponseEntity<Resource> download(@RequestParam Map<String, Object> params)
			throws JRException, IOException, SQLException {
		ReporteReservasDTO dto = reporteReservas.obtenerReporteReserva(params);

		InputStreamResource streamResource = new InputStreamResource(dto.getStream());
		MediaType mediaType = null;
		if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteRnum.EXCEL.name())) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;

		} else {
			mediaType = MediaType.APPLICATION_PDF;
		}
		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
				.contentLength(dto.getLenght()).contentType(mediaType).body(streamResource);// asignar la impresión de
																							// comilla
	}
	
	@GetMapping(path = "/reservas/usuario")
	public ResponseEntity<Resource> downloadUser(@RequestParam Map<String, Object> params)
			throws JRException, IOException, SQLException {
		ReporteReservasDTO dto = reporteReservas.obtenerReporteReservaUsuario(params);

		InputStreamResource streamResource = new InputStreamResource(dto.getStream());
		MediaType mediaType = null;
		if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteRnum.EXCEL.name())) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;

		} else {
			mediaType = MediaType.APPLICATION_PDF;
		}
		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
				.contentLength(dto.getLenght()).contentType(mediaType).body(streamResource);// asignar la impresión de
																							// comilla
	}
	
	@GetMapping(path ="/email/{correo}/{nombre}/{asunto}/cel/{celular}")
	public ResponseEntity<?>sendEmail(@PathVariable String correo,@PathVariable String asunto,
			@PathVariable String nombre,@PathVariable String celular) throws MessagingException {
		Map<String, Object> response = new HashMap<>();
		try {
			this.emailService.enviar(correo,nombre,celular,asunto);
			this.emailService.respuesta(correo, nombre, celular, asunto);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al enviar el correo");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Correo enviado!!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}
	
}
