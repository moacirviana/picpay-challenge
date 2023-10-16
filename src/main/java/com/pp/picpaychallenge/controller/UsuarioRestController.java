package com.pp.picpaychallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pp.picpaychallenge.domain.Usuario;
import com.pp.picpaychallenge.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {
	@Autowired
	UsuarioService service;

	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> lst = service.findAll();
		return ResponseEntity.ok(lst);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}

	@PostMapping()
	public ResponseEntity<Usuario> insert(@RequestBody Usuario obj) throws Exception{
		obj = service.insert(obj);
		return ResponseEntity.ok(obj);
	}

	@PutMapping()
	public ResponseEntity<Usuario> update(@RequestBody Usuario obj) {
		obj = service.update(obj);
		return ResponseEntity.ok(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
