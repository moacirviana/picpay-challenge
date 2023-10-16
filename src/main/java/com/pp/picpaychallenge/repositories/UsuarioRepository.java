package com.pp.picpaychallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pp.picpaychallenge.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  
	@Transactional(readOnly = true) // não precisa ser envolvida numa transação de banco, diminui o lock de banco
	Usuario findByEmail(String email);
	
	@Transactional(readOnly = true) // não precisa ser envolvida numa transação de banco, diminui o lock de banco
	Usuario findByCpfOuCnpj(String cpfOuCnpj);
	
}
