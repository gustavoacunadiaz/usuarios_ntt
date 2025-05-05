package com.usuarios.repository;

import java.util.UUID;
import com.usuarios.entity.Telefono;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITelefonoRepository extends CrudRepository<Telefono, Long> {
	
	@Query("SELECT t FROM Telefono t WHERE t.usuarioId = :id")
	public Telefono findByIdUsuario(@Param("id") UUID id);

}