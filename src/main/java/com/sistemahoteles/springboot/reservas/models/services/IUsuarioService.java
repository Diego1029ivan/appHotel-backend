package com.sistemahoteles.springboot.reservas.models.services;

import java.util.List;

import com.sistemahoteles.springboot.reservas.models.entity.Usuario;


public interface IUsuarioService {
	
  public List<Usuario> findAll();
  
  public Usuario findById(Long id);
  
  public void delete(Long id);
  
  public Usuario saveUsuario(Usuario usuario);
  
  public Usuario findByUsername(String username);
}
