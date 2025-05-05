package com.usuarios.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.usuarios.utils.Utils;
import com.usuarios.entity.Usuario;
import com.usuarios.service.IUsuarioService;
import com.usuarios.utils.Constants;
import com.usuarios.utils.EstadoResponse;
import com.usuarios.utils.EstadoResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;	

	@GetMapping("/usuario")
	public List<Usuario> getUsuarios() {
		return usuarioService.getUsuarios();
	}

	@PostMapping("/usuario")
	public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {
		var status = usuarioService.addUsuario(usuario);
		
		if (status.value() == Constants.DOSCIENTOS) {
			 EstadoResponse er = (EstadoResponse) Utils.generarResponse(status);
			return ResponseEntity.status(status).body(
					new EstadoResponse(er.getId(), er.getCreado(), er.getModificado(), er.getUltimoLogin(), er.getToken(), er.isActivo()));
		} else {
			@SuppressWarnings("unused")
			EstadoResponseError er = (EstadoResponseError) Utils.generarResponse(status);
			return ResponseEntity.status(status).body(
					new EstadoResponseError());
		}		
	}

	@PutMapping("/usuario")
	public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario) {
		var status = usuarioService.updateUsuario(usuario);
		if (status.value() == Constants.DOSCIENTOS) {
			EstadoResponse er = (EstadoResponse) Utils.generarResponse(status);
			return ResponseEntity.status(status).body(
					new EstadoResponse(er.getId(), er.getCreado(), er.getModificado(), er.getUltimoLogin(), er.getToken(), er.isActivo()));
		} else {
			@SuppressWarnings("unused")
			EstadoResponseError er = (EstadoResponseError) Utils.generarResponse(status);
			return ResponseEntity.status(status).body(
					new EstadoResponseError());
		}		
	}

	@DeleteMapping("/usuario")
	public ResponseEntity<?> deleteUsuario(@RequestBody Usuario usuario) {
		var status = usuarioService.deleteUsuario(usuario);
		if (status.value() == Constants.DOSCIENTOS) {
			EstadoResponse er = (EstadoResponse) Utils.generarResponse(status);
			return ResponseEntity.status(status).body(
					new EstadoResponse(er.getId(), er.getCreado(), er.getModificado(), er.getUltimoLogin(), er.getToken(), er.isActivo()));
		} else {
			@SuppressWarnings("unused")
			EstadoResponseError er = (EstadoResponseError) Utils.generarResponse(status);
			return ResponseEntity.status(status).body(
					new EstadoResponseError());
		}		
	}
	
	@PatchMapping("/usuario/{id}")
	public ResponseEntity<?> updateUsuarioP(@PathVariable UUID id, @RequestBody Map<String, Object> patch) {
		var status = usuarioService.updateUsuarioP(id, patch);
		if (status.value() == Constants.DOSCIENTOS) {
			EstadoResponse er = (EstadoResponse) Utils.generarResponse(status);
			return ResponseEntity.status(status).body(
					new EstadoResponse(er.getId(), er.getCreado(), er.getModificado(), er.getUltimoLogin(), er.getToken(), er.isActivo()));
		} else {
			@SuppressWarnings("unused")
			EstadoResponseError er = (EstadoResponseError) Utils.generarResponse(status);
			return ResponseEntity.status(status).body(
					new EstadoResponseError());
		}		
	}
	
}