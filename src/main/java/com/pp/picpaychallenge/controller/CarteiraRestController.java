package com.pp.picpaychallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pp.picpaychallenge.domain.Carteira;
import com.pp.picpaychallenge.domain.Usuario;
import com.pp.picpaychallenge.domain.dto.CarteiraDTOTest;
import com.pp.picpaychallenge.services.CarteiraService;

@RestController
@RequestMapping("/carteira")
public class CarteiraRestController {
	@Autowired
	CarteiraService service;

	@GetMapping
	public ResponseEntity<List<Carteira>> findAll() {
		List<Carteira> lst = service.findAll();
		return ResponseEntity.ok(lst);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Carteira> findById(@PathVariable Long id) {
		Carteira obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}

	@PostMapping()
	public ResponseEntity<Carteira> insert(@RequestBody Carteira obj) {
		obj = service.insert(obj);
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping("/test")
	public ResponseEntity<Void> insertTest(@RequestBody CarteiraDTOTest obj) {
		for (Usuario u : obj.getUsuarios()) {
			Carteira carteira = new Carteira();
			carteira.setUsuario(u);
			carteira.setValor(obj.getValor());
			service.insert(carteira);	
		}
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Carteira> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
