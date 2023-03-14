package com.sistemahoteles.springboot.reservas.models.entity;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="testimonios")
@Where(clause = "estado = 1")
public class Testimonio {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String detalle_testimonio;
		
		private Integer estado;
		
		private Date fecha;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDetalle_testimonio() {
			return detalle_testimonio;
		}

		public void setDetalle_testimonio(String detalle_testimonio) {
			this.detalle_testimonio = detalle_testimonio;
		}
		
		public Integer getEstado() {
			return estado;
		}

		public void setEstado(Integer estado) {
			this.estado = estado;
		}

		@ManyToOne(fetch = FetchType.EAGER) // CARGA PERESOSA
		@JoinColumn(name = "usuario_id")
		@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
		private Usuario usuario;

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}
		
		
		
		
}
