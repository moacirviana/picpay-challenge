package com.pp.picpaychallenge.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pp.picpaychallenge.domain.TipoUsuario;
import com.pp.picpaychallenge.domain.Usuario;
import com.pp.picpaychallenge.repositories.TipoUsuarioRepository;
import com.pp.picpaychallenge.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	TipoUsuarioRepository tipoUsuarioRepo;
	
	@Autowired
	UsuarioRepository usuarioRepo;
	

	public void instantiateTestDatabase() throws Exception {
		TipoUsuario tpCommum = new TipoUsuario(null, "COMUM");
		TipoUsuario tpLogista = new TipoUsuario(null, "LOJISTA");
		tipoUsuarioRepo.saveAll(Arrays.asList(tpCommum, tpLogista));
		
		Usuario usuarioComum = new Usuario(null, "Usuario1", "34904650085", "usuario1@picpay.com", "123", tpCommum);
		Usuario usuarioComum2 = new Usuario(null, "Usuario2", "69722525042", "usuario2@picpay.com", "123", tpCommum);
		Usuario usuarioLojista = new Usuario(null, "Lojista1", "92425459000155", "lojista1@picpay.com", "123", tpLogista);
		Usuario usuarioLojista2 = new Usuario(null, "Lojista2", "88908970000130", "lojista2@picpay.com", "123", tpLogista);
		
		tpCommum.getUsuarios().addAll(Arrays.asList(usuarioComum, usuarioComum2));
		tpLogista.getUsuarios().addAll(Arrays.asList(usuarioLojista, usuarioLojista2));
		usuarioRepo.saveAll(Arrays.asList(usuarioComum, usuarioComum2, usuarioLojista, usuarioLojista2));
		
		
	
	}
	
}
