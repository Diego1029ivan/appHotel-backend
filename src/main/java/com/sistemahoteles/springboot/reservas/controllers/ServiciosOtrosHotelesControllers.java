package com.sistemahoteles.springboot.reservas.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import com.sistemahoteles.springboot.reservas.models.entity.Galeria;
import com.sistemahoteles.springboot.reservas.models.entity.Hotel;
import com.sistemahoteles.springboot.reservas.models.entity.Picina;
import com.sistemahoteles.springboot.reservas.models.entity.PrecioxTipoHabitacion;
import com.sistemahoteles.springboot.reservas.models.entity.Rating;
import com.sistemahoteles.springboot.reservas.models.entity.ReservaHotel;
import com.sistemahoteles.springboot.reservas.models.entity.Testimonio;
import com.sistemahoteles.springboot.reservas.models.entity.TipoPago;
import com.sistemahoteles.springboot.reservas.models.entity.Ubicacion;
import com.sistemahoteles.springboot.reservas.models.entity.Usuario;
import com.sistemahoteles.springboot.reservas.models.services.IGaleriaServices;
import com.sistemahoteles.springboot.reservas.models.services.IHotelesServices;
import com.sistemahoteles.springboot.reservas.models.services.IPicinaServices;
import com.sistemahoteles.springboot.reservas.models.services.IPrecioxTHabitacionServices;
import com.sistemahoteles.springboot.reservas.models.services.IRatingServices;
import com.sistemahoteles.springboot.reservas.models.services.IReservasService;
import com.sistemahoteles.springboot.reservas.models.services.ITestimonioServices;
import com.sistemahoteles.springboot.reservas.models.services.ITipoPagoServices;
import com.sistemahoteles.springboot.reservas.models.services.IUbicacionServices;
import com.sistemahoteles.springboot.reservas.models.services.IUploadFileService;
import com.sistemahoteles.springboot.reservas.models.services.IUsuarioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ServiciosOtrosHotelesControllers {
	@Autowired
	private IUploadFileService uploadService;

	@Autowired
	private IPicinaServices picinaServices;

	@Autowired
	private IGaleriaServices galeriaServices;

	@Autowired
	private IPrecioxTHabitacionServices precioxTHabitacionServices;

	@Autowired
	private IUbicacionServices ubicacionServices;

	@Autowired
	private ITestimonioServices testimonioServices;

	@Autowired
	private IUsuarioService usuarioServices;

	@Autowired
	private IReservasService reservaServices;

	@Autowired
	private IHotelesServices hotelService;

	@Autowired
	private ITipoPagoServices tipoPagoService;

	@Autowired
	private IRatingServices ratingService;

	@GetMapping("/picina/{id}")
	public ResponseEntity<?> mostarOnePicina(@PathVariable Long id) {

		Picina picinaOne = null;

		Map<String, Object> response = new HashMap<>();

		try {
			picinaOne = picinaServices.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la colsulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (picinaOne == null) {
			response.put("mensaje", "El Picina ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Picina>(picinaOne, HttpStatus.OK);// 200

	}

	@PostMapping("/picina/hotel/{id_hotel}")
	public ResponseEntity<?> crearPicina(@Valid @RequestBody Picina picina, BindingResult result,
			@PathVariable Long id_hotel) {

		Picina picinaNuevo = null;
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
			hotelCarga = hotelService.findById(id_hotel);
			picina.setHotel(hotelCarga);
			picinaNuevo = picinaServices.savePicina(picina);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La picina ha sido creado con 'exito!");
		response.put("picina", picinaNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@PutMapping("/picina/{id}/hotel/{id_hotel}")
	public ResponseEntity<?> updatePicina(@Valid @RequestBody Picina picina, BindingResult result,
			@PathVariable Long id, @PathVariable Long id_hotel) {
		Map<String, Object> response = new HashMap<>();

		Picina picinaActual = picinaServices.findById(id);
		Picina picinaUpdate = null;
		Hotel hotelCarga;

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (picinaActual == null) {
			response.put("mensaje", "Error no se pudo editar, el picina ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			hotelCarga = hotelService.findById(id_hotel);
			picinaActual.setPrecioPicina(picina.getPrecioPicina());
			picinaActual.setHorarioInicio(picina.getHorarioInicio());
			picinaActual.setHorarioCierre(picina.getHorarioCierre());
			picinaActual.setDescripcionPicina(picina.getDescripcionPicina());
			picinaActual.setHotel(hotelCarga);

			picinaUpdate = picinaServices.savePicina(picinaActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el picina en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La picina ha sido actualizado con 'exito!");
		response.put("picina", picinaUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201

	}

	@PostMapping("/picina/upload")
	public ResponseEntity<?> uploadFotoPicina(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Picina picinaFoto = picinaServices.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen de la picina");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombrebarAnterior = picinaFoto.getFotoPicina();

			uploadService.eliminar(nombrebarAnterior);

			picinaFoto.setFotoPicina(nombreArchivo);
			picinaServices.savePicina(picinaFoto);

			response.put("picina", picinaFoto);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@GetMapping("/galeria/{id}")
	public ResponseEntity<?> mostarOneGaleria(@PathVariable Long id) {

		Galeria galeriaOne = null;

		Map<String, Object> response = new HashMap<>();

		try {
			galeriaOne = galeriaServices.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la colsulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (galeriaOne == null) {
			response.put("mensaje", "El Galeria ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Galeria>(galeriaOne, HttpStatus.OK);// 200

	}

	@PostMapping("/galeria/hotel/{id_hotel}")
	public ResponseEntity<?> crearGaleria(@Valid @RequestBody Galeria galeria, BindingResult result,
			@PathVariable Long id_hotel) {

		Galeria galeriaNuevo = null;
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
			hotelCarga = hotelService.findById(id_hotel);
			galeria.setHotel(hotelCarga);
			galeriaNuevo = galeriaServices.saveCochera(galeria);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La galeria ha sido creado con 'exito!");
		response.put("galeria", galeriaNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@PutMapping("/galeria/{id}/hotel/{id_hotel}")
	public ResponseEntity<?> updateGaleria(@Valid @RequestBody Galeria galeria, BindingResult result,
			@PathVariable Long id, @PathVariable Long id_hotel) {
		Map<String, Object> response = new HashMap<>();

		Hotel hotelCarga;

		Galeria galeriaActual = galeriaServices.findById(id);
		Galeria galeriaActualUpdate = null;

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (galeriaActual == null) {
			response.put("mensaje", "Error no se pudo editar, el galeria ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			hotelCarga = hotelService.findById(id_hotel);

			galeriaActual.setDescripcion(galeria.getDescripcion());
			galeriaActual.setDescripcionf1(galeria.getDescripcionf1());
			galeriaActual.setDescripcionf2(galeria.getDescripcionf2());
			galeriaActual.setDescripcionf3(galeria.getDescripcionf1());
			galeriaActual.setHotel(hotelCarga);

			galeriaActualUpdate = galeriaServices.saveCochera(galeriaActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el habitacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La galeria ha sido actualizado con 'exito!");
		response.put("habitacion", galeriaActualUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201

	}

	@PostMapping("/galeria/uploadfoto1")
	public ResponseEntity<?> uploadGaleria(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Galeria galeriafotos = galeriaServices.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen de la foto1");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreAnterior = galeriafotos.getFoto();
			uploadService.eliminar(nombreAnterior);

			galeriafotos.setFoto(nombreArchivo);

			galeriaServices.saveCochera(galeriafotos);

			response.put("galeria", galeriafotos);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@PostMapping("/galeria/uploadfoto2")
	public ResponseEntity<?> uploadGaleria2(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Galeria galeriafotos = galeriaServices.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen de la foto1");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreAnterior = galeriafotos.getFoto2();
			uploadService.eliminar(nombreAnterior);

			galeriafotos.setFoto2(nombreArchivo);

			galeriaServices.saveCochera(galeriafotos);

			response.put("galeria", galeriafotos);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@PostMapping("/galeria/uploadfoto3")
	public ResponseEntity<?> uploadGaleria3(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Galeria galeriafotos = galeriaServices.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen de la foto1");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreAnterior = galeriafotos.getFoto3();
			uploadService.eliminar(nombreAnterior);

			galeriafotos.setFoto3(nombreArchivo);

			galeriaServices.saveCochera(galeriafotos);

			response.put("galeria", galeriafotos);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@PostMapping("/galeria/uploadfoto33")
	public ResponseEntity<?> uploadGaleriaTripe(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("archivo2") MultipartFile archivo2, @RequestParam("archivo3") MultipartFile archivo3,
			@RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Galeria galeriafotos = galeriaServices.findById(id);

		if (!archivo.isEmpty() && !archivo2.isEmpty() && !archivo3.isEmpty()) {
			String nombreArchivo = null;
			String nombreArchivo2 = null;
			String nombreArchivo3 = null;

			try {
				nombreArchivo = uploadService.copiar(archivo);
				nombreArchivo2 = uploadService.copiar(archivo2);
				nombreArchivo3 = uploadService.copiar(archivo3);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen de la foto");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreAnterior = galeriafotos.getFoto();
			String nombreAnterior2 = galeriafotos.getFoto2();
			String nombreAnterior3 = galeriafotos.getFoto3();

			uploadService.eliminar(nombreAnterior);
			uploadService.eliminar(nombreAnterior2);
			uploadService.eliminar(nombreAnterior3);

			galeriafotos.setFoto(nombreArchivo);
			galeriafotos.setFoto2(nombreArchivo2);
			galeriafotos.setFoto3(nombreArchivo3);

			galeriaServices.saveCochera(galeriafotos);

			response.put("galeria", galeriafotos);
			response.put("mensaje", "Has subido correctamente la imagen");

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@GetMapping("/tipoHabitacion/{id}")
	public ResponseEntity<?> mostarOnePrecioHabitacion(@PathVariable Long id) {

		PrecioxTipoHabitacion precioxHabitacion = null;

		Map<String, Object> response = new HashMap<>();

		try {
			precioxHabitacion = precioxTHabitacionServices.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la colsulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (precioxHabitacion == null) {
			response.put("mensaje", "El Picina ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PrecioxTipoHabitacion>(precioxHabitacion, HttpStatus.OK);// 200

	}

	@PostMapping("/tipoHabitacion/hotel/{id_hotel}")
	public ResponseEntity<?> crearPrecioHabitacion(@Valid @RequestBody PrecioxTipoHabitacion precioxHabitacion,
			BindingResult result, @PathVariable Long id_hotel) {

		PrecioxTipoHabitacion precioxHabitacionNuevo = null;
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
			hotelCarga = hotelService.findById(id_hotel);
			precioxHabitacion.setHotel(hotelCarga);
			precioxHabitacionNuevo = precioxTHabitacionServices.savePrecioxTipoHabitacion(precioxHabitacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El precioPorHabitacion ha sido creado con 'exito!");
		response.put("Habitacion", precioxHabitacionNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@PutMapping("/tipoHabitacion/{id}/hotel/{id_hotel}")
	public ResponseEntity<?> updatePrecioHabitacion(@Valid @RequestBody PrecioxTipoHabitacion precio,
			BindingResult result, @PathVariable Long id, @PathVariable Long id_hotel) {
		Map<String, Object> response = new HashMap<>();

		Hotel hotelCarga;
		PrecioxTipoHabitacion precioxHabitacionActual = precioxTHabitacionServices.findById(id);

		PrecioxTipoHabitacion habitacionUpdate = null;

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (precioxHabitacionActual == null) {
			response.put("mensaje", "Error no se pudo editar, el picina ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			hotelCarga = hotelService.findById(id_hotel);

			precioxHabitacionActual.setPrecio(precio.getPrecio());
			precioxHabitacionActual.setTipoHabitacion(precio.getTipoHabitacion());
			precioxHabitacionActual.setHotel(hotelCarga);
			precioxHabitacionActual.setCantidad(precio.getCantidad());

			habitacionUpdate = precioxTHabitacionServices.savePrecioxTipoHabitacion(precioxHabitacionActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el habitacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La habitacion ha sido actualizado con 'exito!");
		response.put("habitacion", habitacionUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201

	}

	@GetMapping("/ubicacion/{id}")
	public ResponseEntity<?> mostarOneUbicacion(@PathVariable Long id) {

		Ubicacion ubicacionOne = null;

		Map<String, Object> response = new HashMap<>();

		try {
			ubicacionOne = ubicacionServices.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la colsulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (ubicacionOne == null) {
			response.put("mensaje",
					"La ubicación por ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Ubicacion>(ubicacionOne, HttpStatus.OK);// 200

	}

	@PostMapping("/ubicacion")
	public ResponseEntity<?> crearUbicacion(@Valid @RequestBody Ubicacion ubicacion, BindingResult result) {

		Ubicacion ubicacionNueva = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {

			ubicacionNueva = ubicacionServices.saveUbicacion(ubicacion);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La Ubicación ha sido creado con 'exito!");
		response.put("ubicacion", ubicacionNueva);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@PostMapping("/ubicacion/upload")
	public ResponseEntity<?> uploadFotoUbicacion(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Ubicacion ubicacionFoto = ubicacionServices.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen de la picina");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombrebarAnterior = ubicacionFoto.getFotoCiudad();

			uploadService.eliminar(nombrebarAnterior);

			ubicacionFoto.setFotoCiudad(nombreArchivo);
			ubicacionServices.saveUbicacion(ubicacionFoto);

			response.put("ubicacion", ubicacionFoto);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@PutMapping("/ubicacion/{id}")
	public ResponseEntity<?> updateUbicacion(@Valid @RequestBody Ubicacion ubicacion, BindingResult result,
			@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		Ubicacion ubicacionActual = ubicacionServices.findById(id);

		Ubicacion habitacionUpdate = null;

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (ubicacionActual == null) {
			response.put("mensaje", "Error no se pudo editar, el ubicacion ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			ubicacionActual.setPais(ubicacion.getPais());
			ubicacionActual.setDepartamento(ubicacion.getDepartamento());

			ubicacionActual.setCiudad(ubicacion.getCiudad());
			ubicacionActual.setDescripcionCiudad(ubicacion.getDescripcionCiudad());

			habitacionUpdate = ubicacionServices.saveUbicacion(ubicacionActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la ubicacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La ubicacion ha sido actualizado con 'exito!");
		response.put("ubicacion", habitacionUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201

	}

	@GetMapping("/ubicacion")
	public List<Ubicacion> Mostra() {
		return ubicacionServices.findAll();
	}

	/*** RESERVAS ***/
	@GetMapping("/reserva")
	public List<ReservaHotel> MostrarRESERVA() {
		return reservaServices.findAll();
	}

	@GetMapping("/reserva/hotel/{id}")
	public List<ReservaHotel> reservaxHotel(@PathVariable Long id) {
		return reservaServices.lisReservaxhotel(id);
	}

	@PutMapping("/reserva/{id}")
	public ResponseEntity<?> updateReserva(@Valid @RequestBody ReservaHotel reserva, BindingResult result,
			@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		ReservaHotel reservaActual = reservaServices.findById(id);

		ReservaHotel reservaUpdate = null;

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (reservaActual == null) {
			response.put("mensaje", "Error no se pudo editar, la reserva ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			reservaActual.setEstado(reserva.getEstado());

			reservaUpdate = reservaServices.saveReserva(reservaActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la ubicacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La reserva ha sido actualizado con 'exito!");
		response.put("Reserva", reservaUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201

	}

	@PostMapping("/reserva/user/{id_user}/hote/{id_hotel}")
	public ResponseEntity<?> crearReseva(@Valid @RequestBody ReservaHotel reserva, BindingResult result,
			@PathVariable Long id_user, @PathVariable Long id_hotel) {
		Usuario usuarioCarga;
		Hotel hotelCarga;
		ReservaHotel reservaNuevo = null;

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
			hotelCarga = hotelService.findById(id_hotel);
			reserva.setUsuario(usuarioCarga);
			reserva.setHotel(hotelCarga);
			reservaNuevo = reservaServices.saveReserva(reserva);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La reserva ha sido creado con 'exito!");
		response.put("Reserva", reservaNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@DeleteMapping("/reserva/{id}")
	public ResponseEntity<?> deleteReserva(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		ReservaHotel reservaActual = reservaServices.findById(id);

		if (reservaActual == null) {
			response.put("mensaje", "Error no se pudo eliminar, la reserva con ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			reservaServices.delete(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la reserva en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El testimonio ha sido eliminado con 'exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);// 200

	}

	/*** Testimonios ***/
	@GetMapping("/testimonio")
	public List<Testimonio> MostrarTestimonio() {
		return testimonioServices.findAll();
	}

	@PostMapping("/testimonio/user/{id_user}")
	public ResponseEntity<?> crearTestimonio(@Valid @RequestBody Testimonio testimonio, BindingResult result,
			@PathVariable Long id_user) {
		Usuario usuarioCarga;
		Testimonio testimonioNuevo = null;

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

			testimonio.setUsuario(usuarioCarga);
			testimonioNuevo = testimonioServices.saveTestimonio(testimonio);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El testimonio ha sido creado con 'exito!");
		response.put("testimonio", testimonioNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@DeleteMapping("/testimonio/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		Testimonio testimonioActual = testimonioServices.findById(id);

		if (testimonioActual == null) {
			response.put("mensaje", "Error no se pudo eliminar, el hotel ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			testimonioServices.delete(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el testimonio en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El testimonio ha sido eliminado con 'exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);// 200

	}

	/*** tipo Pago ***/
	@GetMapping("/tipo_pago")
	public List<TipoPago> MostrarPagos() {
		return tipoPagoService.findAll();
	}

	/** Clasificación de estrellas **/

	@GetMapping("/rating")
	public List<Rating> MostrarRating() {
		return ratingService.findAll();
	}

	@PostMapping("/rating/user/{id_user}/hotel/{id_hotel}")
	public ResponseEntity<?> crearRating(@Valid @RequestBody Rating rating, BindingResult result,
			@PathVariable Long id_user, @PathVariable Long id_hotel) {

		Usuario usuarioCarga;
		Hotel hotelCarga;
		Rating ratingNuevo = null;

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
			hotelCarga = hotelService.findById(id_hotel);

			rating.setUsuario(usuarioCarga);
			rating.setHotel(hotelCarga);
			ratingNuevo = ratingService.saveRating(rating);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El rating ha sido creado con 'exito!");
		response.put("rating", ratingNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

}
