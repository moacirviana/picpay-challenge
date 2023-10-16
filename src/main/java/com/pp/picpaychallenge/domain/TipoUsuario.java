package com.pp.picpaychallenge.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity()
public class TipoUsuario implements Serializable {
	private static final long serialVersionUID = 4100915707837617036L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proxb_id")
	@SequenceGenerator(name = "proxb_id", sequenceName = "proxb_id", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "descrição não pode ser nulo")
	private String descricao;
	
	@JsonIgnore
	@OneToMany(mappedBy="tipo")
	private List<Usuario> usuarios = new ArrayList<>();

	public TipoUsuario() {
	}

	public TipoUsuario(Long id, @NotBlank(message = "descrição não pode ser nulo") String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
		TipoUsuario other = (TipoUsuario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "TipoUsuario [id=" + id + ", descricao=" + descricao + "]";
	}

}
