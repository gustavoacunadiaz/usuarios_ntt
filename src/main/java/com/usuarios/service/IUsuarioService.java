package com.usuarios.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.usuarios.entity.Usuario;
import org.springframework.http.HttpStatus;

public interface IUsuarioService {
	
	public List<Usuario> getUsuarios();
	
	public HttpStatus addUsuario(Usuario usuario);
	
	public HttpStatus updateUsuario(Usuario usuario);
	
	public HttpStatus deleteUsuario(Usuario usuario);
	
	public HttpStatus updateUsuarioP(UUID id,  Map<String, Object> patch);

}