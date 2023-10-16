package com.pp.picpaychallenge.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Movimento implements Serializable {
	private static final long serialVersionUID = 2098912607121669286L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proxb_id")
	@SequenceGenerator(name = "proxb_id", sequenceName = "proxb_id", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime instant;

	@Column(nullable = false)
	private Double valor;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_carteira_origem")
	private Carteira origemCarteira;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_carteira_destino")
	private Carteira destinoCarteira;
	

	public Movimento() {
	}

	public Movimento(Long id, LocalDateTime instant, Double valor,
			Carteira origemCarteira, Carteira destinoCarteira) {
		this.id = id;
		this.instant = instant;
		this.valor = valor;
		this.origemCarteira = origemCarteira;
		this.destinoCarteira = destinoCarteira;		
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

	public Carteira getOrigemCarteira() {
		return origemCarteira;
	}

	public void setOrigemCarteira(Carteira origemCarteira) {
		this.origemCarteira = origemCarteira;
	}

	public Carteira getDestinoCarteira() {
		return destinoCarteira;
	}

	public void setDestinoCarteira(Carteira destinoCarteira) {
		this.destinoCarteira = destinoCarteira;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimento other = (Movimento) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Movimento [id=" + id + ", instant=" + instant + ", valor=" + valor + ", origemCarteira="
				+ origemCarteira.getId() + ", destinoCarteira=" + destinoCarteira.getId() +  "]";
	}
	
	

}
