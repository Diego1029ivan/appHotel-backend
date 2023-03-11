package com.sistemahoteles.springboot.reservas.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemahoteles.springboot.reservas.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
	public Usuario findByUsername(String username);
}
