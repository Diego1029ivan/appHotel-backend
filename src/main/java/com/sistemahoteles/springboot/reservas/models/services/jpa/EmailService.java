package com.sistemahoteles.springboot.reservas.models.services.jpa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.mail.MessagingException;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service

public class EmailService {

	private final JavaMailSender javaMailSender;
	 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
     Calendar cal = Calendar.getInstance();
	
	@Autowired
	public EmailService(JavaMailSender javaMailSender) {this.javaMailSender=javaMailSender;}
	
	@Value("${spring.mail.username}")
	private String email;
	
	@Async
	public Boolean enviar(String correo,String nombre,String celular,String asunto) throws MessagingException {
		String mensaje ="<h2>Asunto:</h2>"
						+ "<p>"+asunto+"</p>"+ 
						"<h3>Nombre:</h3>"
						+ "<p>"+nombre+"</p>"+
						"<h3>Correo:</h3>"
						+ "<p>"+correo+"</p>"+
						"<h2>Celular:</h2>"
						+ "<p>"+celular+"</p>"+
						"<h5>Fecha:"+dateFormat.format(cal.getTime())+"</h5>";
		
		
		var mensajes = javaMailSender.createMimeMessage();
		var helper = new MimeMessageHelper(mensajes,true);
		helper.setFrom(email);
		helper.setTo(email);
		helper.setSubject(correo);
		
		helper.setText(mensaje,true);
		javaMailSender.send(mensajes);
		
		return true;
		
	}
	
	@Async
	public Boolean respuesta(String correo,String nombre,String celular,String asunto) throws MessagingException {
		String mensaje = "<h2>Hola señor@ " + nombre + " nos estaremos comunicando con "
				+ "usted lo más pronto posible, gracias por enviar su mensaje</h2>"+
				"<p>Sistema Booking - "+dateFormat.format(cal.getTime())+"</p>";
		
		/*String mensaje = "<h2>Hola " + usuario.getNombre() + " "
				+ usuario.getApellido() + "</h2>";

		mensaje += "<br/>Le informamos que usted ha realizado una compra a fecha "
				+ compra.getFecha()
				+ "<br/><br/>El precio total ha sido de "
				+ compra.getTotal()
				+ " Euros a la siguiente Cuenta Bancaria: "
				+ compra.getCuentaBancaria();

		mensaje += "<br/><br/>Aplicaciones Comprada: <br/><br/>";*/
		
		var mensajes = javaMailSender.createMimeMessage();
		var helper = new MimeMessageHelper(mensajes,true);
		helper.setFrom(email);
		helper.setTo(correo);
		helper.setSubject("Respuesta del mensaje");
		
		helper.setText(mensaje,true);
		javaMailSender.send(mensajes);
		
		return true;
		
	}
	public void enviarEmailconAnexo(String para,String titulo,String contenido,String archivo) throws MessagingException {
		Log.info("Enviando email con anexo");
		
		var mensajes = javaMailSender.createMimeMessage();
		var helper = new MimeMessageHelper(mensajes,true);
		helper.setTo(para);
		helper.setSubject(titulo);
		
		helper.setText(contenido,true);
		helper.addAttachment("image1.jpeg", new ClassPathResource(archivo));
		javaMailSender.send(mensajes);
		Log.info("Email con anexo enviado exitosamente");
	}
	
}
