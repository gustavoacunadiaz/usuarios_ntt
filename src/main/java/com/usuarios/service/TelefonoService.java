package com.usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usuarios.entity.Telefono;
import com.usuarios.repository.ITelefonoRepository;

@Service
public class TelefonoService implements ITelefonoService {

	@Autowired
	private ITelefonoRepository telefonoRepository;
	
	@Override
	public Telefono addTelefono(Telefono telefono) {
		return telefonoRepository.save(telefono);
	}

}