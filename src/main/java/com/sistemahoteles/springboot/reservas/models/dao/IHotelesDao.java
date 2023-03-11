package com.sistemahoteles.springboot.reservas.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistemahoteles.springboot.reservas.models.entity.Hotel;

public interface IHotelesDao extends JpaRepository<Hotel, Long> {
	@Query("from Hotel")
	public List<Hotel> findAllHoteles();
	
	@Query(value="SELECT hoteles.*, usuarios.nombre, ubicaciones.*  FROM hoteles INNER JOIN usuarios  ON hoteles.usuario_id = bd_hoteles.usuarios.id INNER JOIN ubicaciones ON hoteles.ubicacion_id =ubicaciones.id where hoteles.usuario_id=?1 and hoteles.estado_hotel = '1' ", nativeQuery = true)
	public List<Hotel> ListUsuarioxhotel(Long iduser);
}
