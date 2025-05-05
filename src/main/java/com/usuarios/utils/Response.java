package com.usuarios.utils;

public abstract class Response {
	
	public static Integer status;
	public static String mensaje;
	
	public Response(Integer status, String mensaje) {
		Response.status = status;
		Response.mensaje = mensaje;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		Response.status = status;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		Response.mensaje = mensaje;
	}
	
}