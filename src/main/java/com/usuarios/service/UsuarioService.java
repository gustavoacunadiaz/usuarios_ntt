package com.usuarios.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.usuarios.entity.Telefono;
import com.usuarios.entity.Usuario;
import com.usuarios.repository.ITelefonoRepository;
import com.usuarios.repository.IUsuarioRepository;
import com.usuarios.utils.Constants;
import com.usuarios.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {
	@Autowired
	private IUsuarioRepository usuarioRepository;
	@Autowired
	private ITelefonoRepository telefonoRepository;
	
	public List<Usuario> getUsuarios() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	public HttpStatus addUsuario(Usuario usuario) {
    	try {
    		Integer existeCorreo = usuarioRepository.existeCorreo(usuario.getCorreo());
    		if (Utils.validaCorreo(usuario.getCorreo())) {
    			if (existeCorreo!=Constants.UNO) {
    				if (Utils.validaContrasena(usuario.getContraseña())) {        		
    					Usuario u = new Usuario();
		        		Telefono t = new Telefono();
		    			u.setNombre(usuario.getNombre());
		            	u.setCorreo(usuario.getCorreo());
		            	u.setContraseña(usuario.getContraseña());        	
		                Usuario u2 = usuarioRepository.save(u);
		                for (Telefono tel : usuario.getTelefonos()) {            	
		                	t.setNumero(tel.getNumero());
		                	t.setCodigoCiudad(tel.getCodigoCiudad());
		                	t.setCodigoPais(tel.getCodigoPais());
		                	t.setUsuarioId(u2.getId());
		                }
		                telefonoRepository.save(t);
		                return HttpStatus.OK;                
    				} else { return HttpStatus.BAD_GATEWAY; }
    			} else { return HttpStatus.IM_USED; }
    		} else { return HttpStatus.BAD_REQUEST;  } 		
        } catch (Exception e) { return HttpStatus.NOT_FOUND; }
	}

	public HttpStatus updateUsuario(Usuario usuario) {
		try { 
			Usuario usu  = usuarioRepository.findById(usuario.getId()).get();
			Telefono tel = telefonoRepository.findByIdUsuario(usuario.getId());
			usu.setNombre(usuario.getNombre());
			usu.setCorreo(usuario.getCorreo());
			usu.setContraseña(usuario.getContraseña());
		    for (Telefono t : usuario.getTelefonos()) {
			   tel.setNumero(t.getNumero());
			   tel.setCodigoCiudad(t.getCodigoCiudad());
			   tel.setCodigoPais(t.getCodigoPais());
		    }
		    if (Utils.validaCorreo(usu.getCorreo())) {
		    	if (Utils.validaContrasena(usu.getContraseña())) {  		    
				    telefonoRepository.save(tel);	
				    usuarioRepository.save(usu);
				    return HttpStatus.OK;				    
		    	} else { return HttpStatus.BAD_GATEWAY; }
		    } else { return HttpStatus.BAD_REQUEST;  }
		} catch (Exception e) { return HttpStatus.NOT_FOUND; }
	}

	public HttpStatus deleteUsuario(Usuario usuario) {
		try {
			Telefono tel = telefonoRepository.findByIdUsuario(usuario.getId());
			telefonoRepository.deleteById(tel.getId());
			usuarioRepository.deleteById(usuario.getId());			
			return HttpStatus.OK;	
		} catch(Exception ex) { return HttpStatus.NOT_FOUND; }	
	}

	public HttpStatus updateUsuarioP(UUID id,  Map<String, Object> map) {
		try {		
			Usuario usu  = usuarioRepository.findById(id).get();
			map.forEach((key, value) -> {
				Field field = ReflectionUtils.findRequiredField(Usuario.class, key);
				if ( field != null) {
					field.setAccessible(true);
					ReflectionUtils.setField(field, usu, value);
				}				
			});
			usuarioRepository.save(usu);
			return HttpStatus.OK;
		} catch (Exception e) {	return HttpStatus.NOT_FOUND; }
	}
	
}