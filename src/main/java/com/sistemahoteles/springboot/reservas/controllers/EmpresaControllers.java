package com.sistemahoteles.springboot.reservas.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemahoteles.springboot.reservas.models.dao.IRolesDao;
import com.sistemahoteles.springboot.reservas.models.entity.Role;
import com.sistemahoteles.springboot.reservas.models.entity.Usuario;
import com.sistemahoteles.springboot.reservas.models.services.IUsuarioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class EmpresaControllers {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IRolesDao rolesDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/usuarios")
	public List<Usuario> Mostra() {
		return usuarioService.findAll();
	}
	
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Usuario user = null;
		Map<String, Object> response = new HashMap<>();

		try {
			user = usuarioService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la colsulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (user == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);// 200
	}

	@PostMapping(path = "/usuarios")
	public ResponseEntity<?> crearUsuarioCliente(@Valid @RequestBody Usuario usuario, BindingResult result) {

		Usuario nuevoUser = null;

		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		Usuario user = new Usuario(usuario.getUsername(), usuario.getNombre(), usuario.getApellido(),
				usuario.getEmail(), usuario.getCelular(), passwordEncoder.encode(usuario.getPassword()),
				usuario.getEnabled(), usuario.getEstado());

		Set<Role> roles = new HashSet<>();

		try {
			Long id = (long) 1;
			Optional<Role> userRole = rolesDao.findById(id);

			roles.add(userRole.get());
			user.setRoles(roles);

			nuevoUser = usuarioService.saveUsuario(user);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario ha sido creado con 'exito!");
		response.put("usuario", nuevoUser);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();
		Usuario userActual = usuarioService.findById(id);
		Usuario userUpdate = null;

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (userActual == null) {
			response.put("mensaje", "Error no se pudo editar, el usuario ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			userActual.setUsername(usuario.getUsername());

			if (usuario.getNombre() != null) {
				userActual.setNombre(usuario.getNombre());
				}

			if (usuario.getApellido() != null) {
				userActual.setApellido(usuario.getApellido());
			}
			if (usuario.getEmail() != null) {
				userActual.setEmail(usuario.getEmail());
			}

			if (usuario.getCelular() != null) {
				userActual.setCelular(usuario.getCelular());
			}
			if (usuario.getPassword() != null) {
				userActual.setPassword(passwordEncoder.encode(usuario.getPassword()));
			}

			userUpdate = usuarioService.saveUsuario(userActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario ha sido actualizado con 'exito!");
		response.put("usuario", userUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}
	
	
	@PostMapping(path = "/usuariosAdmin")
	public ResponseEntity<?> crearUsuarioAdmin(@Valid @RequestBody Usuario usuario, BindingResult result) {

		Usuario nuevoUser = null;

		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		Usuario user = new Usuario(usuario.getUsername(), usuario.getNombre(), usuario.getApellido(),
				usuario.getEmail(), usuario.getCelular(), passwordEncoder.encode(usuario.getPassword()),
				usuario.getEnabled(), usuario.getEstado());

		Set<Role> roles = new HashSet<>();

		try {	
			Long id2 = (long) 2;		
			Optional<Role> userRole2 = rolesDao.findById(id2);
			
			roles.add(userRole2.get());
			user.setRoles(roles);

			nuevoUser = usuarioService.saveUsuario(user);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario ha sido creado con 'exito!");
		response.put("usuario", nuevoUser);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);// 201
	}
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();	
		Usuario userActual = usuarioService.findById(id);
		
			if (userActual == null) {
				response.put("mensaje", "Error no se pudo eliminar, el usuario ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		
		try {
 	
		    usuarioService.delete(id);
			

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario ha sido eliminado con 'exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);// 200

	}
}
