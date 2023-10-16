package com.pp.picpaychallenge.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.pp.picpaychallenge.domain.Usuario;

public class CarteiraDTOTest  {

	private Double valor;

	private List<Usuario> usuarios = new ArrayList<Usuario>();

	public CarteiraDTOTest() {
	}

	public CarteiraDTOTest(Double valor) {
		this.valor = valor;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
