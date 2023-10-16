package com.pp.picpaychallenge.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pp.picpaychallenge.domain.Carteira;
import com.pp.picpaychallenge.exception.DataIntegrityException;
import com.pp.picpaychallenge.exception.ObjectNotFoundException;
import com.pp.picpaychallenge.repositories.CarteiraRepository;

@Service
public class CarteiraService {
	@Autowired
	private CarteiraRepository repo;
	
	@Autowired
	private UsuarioService usuarioService;

	public List<Carteira> findAll() {
		return repo.findAll();
	}

	public Carteira findById(Long id) {
		Optional<Carteira> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Carteira não encontrada! Id: " + id + ", Tipo: " + Carteira.class.getName()));
	}

	@Transactional
	public Carteira insert(Carteira obj) {
		obj.setId(null);
		obj.setInstant(LocalDateTime.now());
		obj.setUsuario(usuarioService.findById(obj.getUsuario().getId()));
		obj = repo.save(obj);
		return obj;
	}
	
	@Transactional
	public Carteira update(Carteira obj) {
		obj = repo.save(obj);
		return obj;
	}

	@Transactional
	public void delete(Long id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Carteira que possui usuario.");
		}
		repo.deleteById(id);
	}

	/*
	 * @Transactional public Carteira update(Carteira obj) { Optional<Carteira>
	 * newObj = repo.findById(obj.getId()); newObj.get().setNome(obj.getNome());
	 * return repo.save(newObj.orElse(null)); }
	 */

}
