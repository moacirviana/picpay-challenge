package com.pp.picpaychallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pp.picpaychallenge.domain.Movimento;

public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
	
	
}
