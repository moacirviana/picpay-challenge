package com.pp.picpaychallenge.services;

import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pp.picpaychallenge.domain.dto.PojoJson;

@Service
public class AutorizacaoService {
	// SERVIÇO DE AUTORIZAÇÃO EXTERNA
		public boolean autorizacaoExterna(Long idUsuario, Double valor) {
			boolean ret = false;
			ObjectMapper mapper = new ObjectMapper();
			// PEGANDO DADOS DO USUARIO
			try {
				// SERA INFORMADO A API USUARIO/VALOR PARA LIBERACAO DA TRANSFERENCIA
				URL url = new URL("https://reqres.in/api/users/2");
				InputStreamReader reader = new InputStreamReader(url.openStream());
				// CONVERTER JSON EM UM OBJETO
				PojoJson jsonObj = mapper.readValue(reader, PojoJson.class);
				System.out.println("IdUser = " + idUsuario  + ", VALOR= " + valor + ", resposta " +  jsonObj.toString());
				ret = true;
			} catch (Exception e) {
				return false;
			}
			return ret;
		}
		
}
