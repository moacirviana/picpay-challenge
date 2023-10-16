package com.pp.picpaychallenge.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pp.picpaychallenge.domain.Usuario;
import com.pp.picpaychallenge.exception.DataIntegrityException;
import com.pp.picpaychallenge.exception.ObjectNotFoundException;
import com.pp.picpaychallenge.repositories.TipoUsuarioRepository;
import com.pp.picpaychallenge.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private TipoUsuarioRepository tipoUsusarioRepo;
	
	public List<Usuario> findAll(){
		return repo.findAll();
	}
	
	public Usuario findById(Long id) {
    	Optional<Usuario> obj = repo.findById(id); 
    	return obj.orElseThrow(() -> new ObjectNotFoundException( 
    		      "Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));  
   }
	
	
	@Transactional
	public Usuario insert(Usuario obj) throws Exception{
		this.validaInsert(obj);
		 obj.setId(null);
		 obj.setTipo(tipoUsusarioRepo.findById(obj.getTipo().getId()).get());
		 obj = repo.save(obj);	
		return obj;
	}
	
	@Transactional
	public void delete(Long id) {
		findById(id);
    	try {
    		repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			System.out.println("Gerou erro");
			throw new DataIntegrityException("Não é possivel excluir um Usuario que possui saldo em carteira");
		}
	}
	
	 @Transactional
	 public Usuario update(Usuario obj) {
		 Optional<Usuario> newObj  = repo.findById(obj.getId());
		 newObj.get().setNome(obj.getNome());
    	 return repo.save(newObj.orElse(null));
    }
	
	private void validaInsert(Usuario obj) throws Exception{
	   Optional<Usuario>  usuario = Optional.ofNullable(repo.findByEmail(obj.getEmail()));
		if (usuario.isPresent()) {
			throw new Exception("Email já cadastrado");
		}
		
		usuario = Optional.ofNullable(repo.findByCpfOuCnpj(obj.getCpfOuCnpj()));
		if (usuario.isPresent()) {
			throw new Exception("CPF ou CNPJ já cadastrado");
		}
		
	}
	 
	 
}
