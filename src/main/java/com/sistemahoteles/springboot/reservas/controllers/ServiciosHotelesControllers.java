package com.sistemahoteles.springboot.reservas.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sistemahoteles.springboot.reservas.models.entity.Bar;
import com.sistemahoteles.springboot.reservas.models.entity.Cochera;
import com.sistemahoteles.springboot.reservas.models.entity.Hotel;
import com.sistemahoteles.springboot.reservas.models.entity.Ubicacion;
import com.sistemahoteles.springboot.reservas.models.entity.Usuario;
import com.sistemahoteles.springboot.reservas.models.services.IBarServices;
import com.sistemahoteles.springboot.reservas.models.services.ICocheraServices;
import com.sistemahoteles.springboot.reservas.models.services.IHotelesServices;
import com.sistemahoteles.springboot.reservas.models.services.IUbicacionServices;
import com.sistemahoteles.springboot.reservas.models.services.IUploadFileService;
import com.sistemahoteles.springboot.reservas.models.services.IUsuarioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ServiciosHotelesControllers {

	@Autowired
	private IUsuarioService usuarioServices;

	@Autowired
	private IUbicacionServices ubicacionServices;

	@Autowired
	private IHotelesServices hotelservice;

	@Autowired
	private IBarServices barServices;

	@Autowired
	private ICocheraServices cocheraServices;

	@Autowired
	private IUploadFileService uploadService;

	@GetMapping("/hoteles")
	public List<Hotel> mostrarH() {
		return hotelservice.findAll();
	}

	@GetMapping("/hoteles/usuariologeadoxhoteles/{id}")
	public List<Hotel> usuarioxHotel(@PathVariable Long id) {

		return hotelservice.ListUsuarioxhotel(id);
	}

	@GetMapping("/hoteles/{id}")
	public ResponseEntity<?> mostarOneHotel(@PathVariable Long id) {

		Hotel hotel = null;

		Map<String, Object> response = new HashMap<>();

		try {
			hotel = hotelservice.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la colsulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (hotel == null) {
			response.put("mensaje", "El hotel ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);// 200

	}

	@PostMapping(path = "/hoteles/user/{id_user}/ubicacion/{id_ubica}")
	public ResponseEntity<?> crearHotel(@Valid @RequestBody Hotel hotel, BindingResult result,
			@PathVariable Long id_user, @PathVariable Long id_ubica) {

		Usuario usuarioCarga;

		Ubicacion ubicacionCarga;

		Hotel hotelNuw = null;

		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {

			usuarioCarga = usuarioServices.findById(id_user);
			ubicacionCarga = ubicacionServices.findById(id_ubica);
			hotel.setUsuario(usuarioCarga);
			hotel.setUbicacion(ubicacionCarga);

			hotelNuw = hotelservice.saveHotel(hotel);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El hotel ha sido creado con 'exito!");
		response.put("hotel", hotelNuw);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@PutMapping("/hoteles/{id}")
	public ResponseEntity<?> updateHotel(@Valid @RequestBody Hotel hotel, BindingResult result, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		Hotel hotelActual = hotelservice.findById(id);
		Hotel hotelUpdate = null;

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (hotelActual == null) {
			response.put("mensaje", "Error no se pudo editar, el usuario ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			// modifique ubicacion
			hotelActual.setNombre(hotel.getNombre());
			hotelActual.setRuc(hotel.getRuc());
			hotelActual.setCantidadHabitacion(hotel.getCantidadHabitacion());
			hotelActual.setDescripcionHotel(hotel.getDescripcionHotel());

			if (hotel.getUsuario() != null) {
				hotelActual.setUsuario(hotel.getUsuario());
			}

			if (hotel.getUbicacion() != null) {
				hotelActual.setUbicacion(hotel.getUbicacion());
			}

			hotelUpdate = hotelservice.saveHotel(hotelActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el hotel en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El hotel ha sido actualizado con 'exito!");
		response.put("hotel", hotelUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201

	}

	@PutMapping("/hoteles/{id}/ubicacion/{id_ubica}")
	public ResponseEntity<?> updateHotelUbicacion(@Valid @RequestBody Hotel hotel, BindingResult result,
			@PathVariable Long id, @PathVariable Long id_ubica) {
		Map<String, Object> response = new HashMap<>();

		Hotel hotelActual = hotelservice.findById(id);
		Hotel hotelUpdate = null;
		Ubicacion ubicacionCarga;

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (hotelActual == null) {
			response.put("mensaje", "Error no se pudo editar, el usuario ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			ubicacionCarga = ubicacionServices.findById(id_ubica);
			// modifique ubicacion
			hotelActual.setNombre(hotel.getNombre());
			hotelActual.setRuc(hotel.getRuc());
			hotelActual.setCantidadHabitacion(hotel.getCantidadHabitacion());
			hotelActual.setDescripcionHotel(hotel.getDescripcionHotel());

			if (hotel.getUsuario() != null) {
				hotelActual.setUsuario(hotel.getUsuario());
			}

			hotelActual.setUbicacion(ubicacionCarga);

			hotelUpdate = hotelservice.saveHotel(hotelActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el hotel en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El hotel ha sido actualizado con 'exito!");
		response.put("hotel", hotelUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201

	}

	@DeleteMapping("/hoteles/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		Hotel hotelActual = hotelservice.findById(id);

		if (hotelActual == null) {
			response.put("mensaje", "Error no se pudo eliminar, el hotel ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			Hotel hotel = hotelservice.findById(id);
			String nombreLogoAnterior = hotel.getLogo();

			uploadService.eliminar(nombreLogoAnterior);

			hotelservice.delete(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el hotel en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El hotel ha sido eliminado con 'exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);// 200

	}

	@PostMapping("/hoteles/upload")
	public ResponseEntity<?> uploadLogo(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Hotel hotel = hotelservice.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del logo");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreLogoAnterior = hotel.getLogo();

			uploadService.eliminar(nombreLogoAnterior);

			hotel.setLogo(nombreArchivo);
			hotelservice.saveHotel(hotel);

			response.put("hoteles", hotel);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

		Resource recurso = null;

		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}

		HttpHeaders cabecera = new HttpHeaders();

		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

	@GetMapping("/bar/{id}")
	public ResponseEntity<?> mostarOneBar(@PathVariable Long id) {

		Bar barupdate = null;

		Map<String, Object> response = new HashMap<>();

		try {
			barupdate = barServices.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la colsulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (barupdate == null) {
			response.put("mensaje", "El bar ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Bar>(barupdate, HttpStatus.OK);// 200

	}

	@PostMapping("/bar/hotel/{id_hotel}")
	public ResponseEntity<?> crearBar(@Valid @RequestBody Bar bar, BindingResult result, @PathVariable Long id_hotel) {

		Bar barnuevo = null;

		Hotel hotelCarga;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			hotelCarga = hotelservice.findById(id_hotel);
			bar.setHotel(hotelCarga);
			barnuevo = barServices.saveBar(bar);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El bar ha sido creado con 'exito!");
		response.put("bar", barnuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@PutMapping("/bar/{id}/hotel/{id_hotel}")
	public ResponseEntity<?> updateBar(@Valid @RequestBody Bar bar, BindingResult result, @PathVariable Long id,
			@PathVariable Long id_hotel) {
		Map<String, Object> response = new HashMap<>();

		Hotel hotelCarga;

		Bar baractual = barServices.findById(id);
		Bar barupdate = null;

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (baractual == null) {
			response.put("mensaje", "Error no se pudo editar, el bar ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			hotelCarga = hotelservice.findById(id_hotel);
			baractual.setDescripcionBar(bar.getDescripcionBar());
			baractual.setHotel(hotelCarga);

			barupdate = barServices.saveBar(baractual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el bar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El bar ha sido actualizado con 'exito!");
		response.put("bar", barupdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201

	}

	@PostMapping("/bar/upload")
	public ResponseEntity<?> uploadFotoBar(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Bar barfoto = barServices.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del bar");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombrebarAnterior = barfoto.getFotoBar();

			uploadService.eliminar(nombrebarAnterior);

			barfoto.setFotoBar(nombreArchivo);
			barServices.saveBar(barfoto);

			response.put("bar", barfoto);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@GetMapping("/cochera/{id}")
	public ResponseEntity<?> mostarOneCochera(@PathVariable Long id) {

		Cochera cocheraUpdate = null;

		Map<String, Object> response = new HashMap<>();

		try {
			cocheraUpdate = cocheraServices.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la colsulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cocheraUpdate == null) {
			response.put("mensaje", "El Cochera ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cochera>(cocheraUpdate, HttpStatus.OK);// 200

	}

	@PostMapping("/cochera/hotel/{id_hotel}")
	public ResponseEntity<?> crearCochera(@Valid @RequestBody Cochera cochera, BindingResult result,
			@PathVariable Long id_hotel) {

		Cochera cocheraNuevo = null;
		Hotel hotelCarga;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			hotelCarga = hotelservice.findById(id_hotel);
			cochera.setHotel(hotelCarga);
			cocheraNuevo = cocheraServices.saveCochera(cochera);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La cochera ha sido creado con 'exito!");
		response.put("cochera", cocheraNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@PutMapping("/cochera/{id}/hotel/{id_hotel}")
	public ResponseEntity<?> updateCochera(@Valid @RequestBody Cochera cochera, BindingResult result,
			@PathVariable Long id, @PathVariable Long id_hotel) {
		Map<String, Object> response = new HashMap<>();

		Cochera cocherActual = cocheraServices.findById(id);
		Cochera cocheraUpdate = null;
		
		Hotel hotelCarga;

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (cocherActual == null) {
			response.put("mensaje", "Error no se pudo editar, la cochera ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			hotelCarga = hotelservice.findById(id_hotel);
			cocherActual.setDescripcionCochera(cochera.getDescripcionCochera());

		
				cocherActual.setHotel(hotelCarga);
		

			cocheraUpdate = cocheraServices.saveCochera(cocherActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la cochera en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La cochera ha sido actualizado con 'exito!");
		response.put("cochera", cocheraUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201

	}

	@PostMapping("/cochera/upload")
	public ResponseEntity<?> uploadFotoCochera(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Cochera cocherFoto = cocheraServices.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen de la cochera");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombrebarAnterior = cocherFoto.getFotoCochera();

			uploadService.eliminar(nombrebarAnterior);

			cocherFoto.setFotoCochera(nombreArchivo);
			cocheraServices.saveCochera(cocherFoto);

			response.put("cochera", cocherFoto);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

}
