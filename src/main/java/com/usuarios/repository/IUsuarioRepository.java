package com.usuarios.repository;

import java.util.UUID;
import com.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, UUID>{
	
	@Query("SELECT COUNT(u.correo) FROM Usuario u WHERE u.correo = :correo")
	public Integer existeCorreo(@Param("correo") String correo);

}