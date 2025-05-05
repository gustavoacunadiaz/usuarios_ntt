package com.usuarios.utils;

import java.util.UUID;

public class EstadoResponse extends Response {
	private UUID id;
	private String creado;
	private String modificado;
	private String ultimoLogin;
	private String token;
	private boolean activo;
	
	public EstadoResponse() {
		super(status, mensaje);
	}
	
	public EstadoResponse(UUID id, String creado, String modificado, String ultimoLogin, String token, boolean activo) {
		super(status, mensaje);
		this.id = UUID.randomUUID();
		this.creado = creado;
		this.modificado = modificado;
		this.ultimoLogin = ultimoLogin;
		this.token = token;
		this.activo = activo;
	}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getCreado() {
		return creado;
	}
	public void setCreado(String creado) {
		this.creado = creado;
	}	
	public String getModificado() {
		return modificado;
	}
	public void setModificado(String modificado) {
		this.modificado = modificado;
	}
	public String getUltimoLogin() {
		return ultimoLogin;
	}
	public void setUltimoLogin(String ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		Response.mensaje = mensaje;
	}
		
}