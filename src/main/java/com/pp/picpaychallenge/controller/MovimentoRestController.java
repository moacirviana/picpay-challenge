package com.pp.picpaychallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pp.picpaychallenge.domain.Movimento;
import com.pp.picpaychallenge.services.CarteiraService;
import com.pp.picpaychallenge.services.MovimentoService;

@RestController
@RequestMapping("/movimento")
public class MovimentoRestController {
	@Autowired
	MovimentoService service;
	
	@Autowired
	CarteiraService carteiraService;
	
/*
 - Usuários podem enviar dinheiro (efetuar transferência) para lojistas e entre usuários.
 - Lojistas só recebem transferências, não enviam dinheiro para ninguém.
 - Validar se o usuário tem saldo antes da transferência.
 - Antes de finalizar a transferência, deve-se consultar um serviço autorizador externo, 
      use este mock para simular (https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6).
 A operação de transferência deve ser uma transação (ou seja, revertida em qualquer caso de inconsistência) 
     e o dinheiro deve voltar para a carteira do usuário que envia.
 No recebimento de pagamento, o usuário ou lojista precisa receber notificação (envio de email, sms) 
    enviada por um serviço de terceiro e eventualmente este serviço pode estar indisponível/instável. 
 
 Use este mock para simular o envio (http://o4d9z.mocklab.io/notify).
 
 */

	@GetMapping("/{id}")
	public ResponseEntity<Movimento> findById(@PathVariable Long id) {
		Movimento obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}

	@PostMapping()
	public ResponseEntity<Movimento> insert(@RequestBody Movimento obj) throws Exception{
		obj = service.insert(obj);
		return ResponseEntity.ok(obj);		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Movimento> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
	

}
