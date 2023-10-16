package com.pp.picpaychallenge.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity()
public class Carteira implements Serializable {
	private static final long serialVersionUID = 1879877413724588024L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proxb_id")
	@SequenceGenerator(name = "proxb_id", sequenceName = "proxb_id", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime instant;

	@Column(nullable = false)
	private Double valor;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Carteira() {
	}

	public Carteira(Long id, LocalDateTime instant, Double valor, Usuario usuario) {
		this.id = id;
		this.instant = instant;
		this.valor = valor;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getInstant() {
		return instant;
	}

	public void setInstant(LocalDateTime instant) {
		this.instant = instant;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Carteira [id=" + id + ", instant=" + instant + ", valor=" + valor + ", usuario=" + usuario + "]";
	}

}
