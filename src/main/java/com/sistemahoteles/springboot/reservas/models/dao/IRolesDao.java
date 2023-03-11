package com.sistemahoteles.springboot.reservas.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemahoteles.springboot.reservas.models.entity.Role;

public interface IRolesDao extends JpaRepository<Role, Long> {

}
