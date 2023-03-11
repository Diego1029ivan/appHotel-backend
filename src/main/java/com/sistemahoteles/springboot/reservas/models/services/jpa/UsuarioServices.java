package com.sistemahoteles.springboot.reservas.models.services.jpa;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemahoteles.springboot.reservas.models.dao.IUsuarioDao;
import com.sistemahoteles.springboot.reservas.models.entity.Usuario;
import com.sistemahoteles.springboot.reservas.models.services.IUsuarioService;

@Service
public class UsuarioServices implements IUsuarioService,UserDetailsService {
	private Logger logger = LoggerFactory.getLogger(UsuarioServices.class);
	
	
	@Autowired
	private IUsuarioDao usuariodao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {

		return (List<Usuario>)usuariodao.findAll();
	}

	@Override
	@Transactional
	public Usuario saveUsuario(Usuario usuario) {
		return usuariodao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {

		return usuariodao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {

		usuariodao.deleteById(id);
	}
//login 
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuariodao.findByUsername(username);
		
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario '"+username+ "' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+ "' en el sistema!");
			
		}
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombreRol()))
				.peek(authority-> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		
		return usuariodao.findByUsername(username);
	}
	
	
}
