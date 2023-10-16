package com.pp.picpaychallenge.services;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pp.picpaychallenge.domain.Carteira;
import com.pp.picpaychallenge.domain.Movimento;
import com.pp.picpaychallenge.exception.DataIntegrityException;
import com.pp.picpaychallenge.exception.ObjectNotFoundException;
import com.pp.picpaychallenge.repositories.MovimentoRepository;

@Service
public class MovimentoService {
	@Autowired
	private MovimentoRepository movimentoRepo;
	
	@Autowired
	private CarteiraService carteiraService;
	
	@Autowired
	AutorizacaoService autorizaoService;
	
	@Autowired
	MensagemService mensagemService;
	
	public Movimento findById(Long id) {
		Optional<Movimento> obj = movimentoRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Movimento.class.getName()));
	}

	@Transactional
	public Movimento insert(Movimento obj) throws Exception{
		obj.setId(null);
		  obj.setInstant(LocalDateTime.now());
		   Carteira origem = carteiraService.findById(obj.getOrigemCarteira().getId());
		    obj.setOrigemCarteira(origem);
		     Carteira destino = carteiraService.findById(obj.getDestinoCarteira().getId());
		      obj.setDestinoCarteira(destino);
		      	// VALIDAÇÃO DA MOVIMENTAÇÃO
		        validarMovimento(obj);
		        // SE NÃO OCORRER NENHUM ERRO DADOS SERÃO SALVOS
			    obj = movimentoRepo.save(obj);
			    // ATUALIZANDO OS SALDOS DAS CARTEIRAS
			    origem.setValor(origem.getValor() - obj.getValor() );
			    destino.setValor( destino.getValor() + obj.getValor() );
			    carteiraService.update(origem);
			    carteiraService.update(destino);
			// ENVIA MENSAGEM DA TRANSFERENCIA
		  //mensagemService.enviarNotificacao(obj);
	  return obj;
	}
	
	
			
	@Transactional
	public void delete(Long id) {
		findById(id);
		try {
			movimentoRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Carteira que possui usuario.");
		}
		movimentoRepo.deleteById(id);
	}

	private void validarMovimento(Movimento obj) throws Exception{
		// SE USUARIO FOR LOJISTA NÃO PERMITIR TRANSFERENCIA
		if (obj.getOrigemCarteira().getUsuario().getTipo().getId()!=1) {
			throw new Exception("Usuário lojista não pode fazer transferência");
		}
		
		// NÃO PODE FAZER SELF-TRANSFERENCIA
		if (obj.getOrigemCarteira().getUsuario().getId() == obj.getDestinoCarteira().getUsuario().getId()) {
			throw new Exception("Usuário não pode fazer transferência para ele mesmo");
		}
			
		// VERIFICA SE USUARIO TEM SALDO
		if (obj.getValor() > obj.getOrigemCarteira().getValor() ) {
			throw new Exception("Usuário não tem saldo suficiente!");
		}
		
		// SERVICO DE AUTORIZACAO - VERIFICA SE USUARIO PODE REALIZAR A TRANSFERENCIA
		if (!autorizaoService.autorizacaoExterna(obj.getOrigemCarteira().getUsuario().getId(), obj.getValor())) {
			throw new Exception("Usuário não tem autorização para efetuar essa transferencia!");
		}
		
	}
	

}
