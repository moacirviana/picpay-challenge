package com.pp.picpaychallenge.services;

import org.springframework.stereotype.Service;

import com.pp.picpaychallenge.domain.Movimento;

@Service
public class MensagemService {
	
	// ENVIO DE NOTIFICAÇÂO
		public void enviarNotificacao(Movimento obj) {
			System.out.println("******************************************");
			System.out.println("********** SERVICO DE MENSAGEM ***********");
			System.out.println("Sr(a) " + obj.getOrigemCarteira().getUsuario().getNome() + " foi transferido o valor de " 
			                  + obj.getValor() + " para a conta de " + obj.getDestinoCarteira().getUsuario().getNome());
			System.out.println("*************** e vice versa****************");
		}
		
		
	
		
}
