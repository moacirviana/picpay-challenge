package com.pp.picpaychallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pp.picpaychallenge.domain.Carteira;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
	
	
}
