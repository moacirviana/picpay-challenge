package com.pp.picpaychallenge.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pp.picpaychallenge.domain.TipoUsuario;
import com.pp.picpaychallenge.exception.DataIntegrityException;
import com.pp.picpaychallenge.exception.ObjectNotFoundException;
import com.pp.picpaychallenge.repositories.TipoUsuarioRepository;

@Service
public class TipoUsuarioService {
	@Autowired
	private TipoUsuarioRepository repo;
	
	public List<TipoUsuario> findAll(){
		return repo.findAll();
	}
	
	public TipoUsuario findById(Long id) {
    	Optional<TipoUsuario> obj = repo.findById(id); 
    	return obj.orElseThrow(() -> new ObjectNotFoundException( 
    		      "Objeto não encontrado! Id: " + id + ", Tipo: " + TipoUsuario.class.getName()));  
   }
	
	
	@Transactional
	public TipoUsuario insert(TipoUsuario obj) {
	    obj.setId(null);
	    obj = repo.save(obj);
	    return obj;
	}
	
	@Transactional
	public void delete(Long id) {
		findById(id);
    	try {
    		repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um TipoUsuario que dados em Usuário.");
		}
	    repo.deleteById(id);
	}
	
	/*
	 @Transactional
	 public TipoUsuario update(Usuario obj) {
		 Optional<TipoUsuario> newObj  = repo.findById(obj.getId());
		 newObj.get().setNome(obj.getNome());
    	 return repo.save(newObj.orElse(null));
    }
    */
	 
}
