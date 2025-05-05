package com.usuarios.utils;

import java.util.Date;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;

public class Utils {	
	private static final String REGEX_CORREO = "^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$";
	private static final String REGEX_CONTRASENA = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
	private static final Pattern PATRON = Pattern.compile(REGEX_CORREO);
	private static Date fecha = new Date();
		
	@SuppressWarnings("deprecation")
	public static Response generarResponse(HttpStatus status) {
		if (status.value() == Constants.DOSCIENTOS) {			
			EstadoResponse er = new EstadoResponse();	
			er.setStatus(status.value());
			er.setCreado(fecha.toLocaleString());
			er.setActivo(true);		
			er.setMensaje(Constants.OK);
			return (Response) er;	
		
		} else if (status.value() == Constants.CUATROCIENTOS) {
			EstadoResponseError ere = new EstadoResponseError();
			ere.setStatus(status.value());
			ere.setMensaje(Constants.ERRORVALIDACORREO);
			return (Response) ere;
			
		} else if (status.value() == Constants.DOSCIENTOSVEINTISEIS) {
			EstadoResponseError ere = new EstadoResponseError();
			ere.setStatus(status.value());
			ere.setMensaje(Constants.ERROREXISTECORREO);
			return (Response) ere;
			
		} else if (status.value() == Constants.QUINIENTOSDOS) {
			EstadoResponseError ere = new EstadoResponseError();
			ere.setStatus(status.value());
			ere.setMensaje(Constants.ERRORCONTRASENA);
			return (Response) ere;		

		} else {			
			EstadoResponseError ere = new EstadoResponseError();
			ere.setStatus(status.value());
			ere.setMensaje(Constants.ERRORMENSAJE);
			return (Response) ere;
		}			
	}

	public static boolean validaCorreo(String correo) {
		if (correo == null) return false;
        return PATRON.matcher(correo).matches();
	}

	public static boolean validaContrasena(String contrasena) {
		return contrasena != null && contrasena.matches(REGEX_CONTRASENA);
	}
	
}