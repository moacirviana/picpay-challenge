package com.pp.picpaychallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pp.picpaychallenge.domain.TipoUsuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
	
	
}
