package com.sistemahoteles.springboot.reservas.models.entity;

import java.io.ByteArrayInputStream;

public class ReporteReservasDTO {

		private String fileName;
		private ByteArrayInputStream stream;
		private int lenght;
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public ByteArrayInputStream getStream() {
			return stream;
		}
		public void setStream(ByteArrayInputStream stream) {
			this.stream = stream;
		}
		public int getLenght() {
			return lenght;
		}
		public void setLenght(int lenght) {
			this.lenght = lenght;
		}
		
}
