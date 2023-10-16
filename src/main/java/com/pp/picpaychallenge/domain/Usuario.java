package com.pp.picpaychallenge.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import com.pp.picpaychallenge.domain.enums.Perfil;


@Entity()
public class Usuario implements Serializable {
	private static final long serialVersionUID = 5001007569664373910L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proxb_id")
	@SequenceGenerator(name = "proxb_id", sequenceName = "proxb_id", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "Nome não pode ser nulo")
	private String nome;

	@Column(nullable = false, unique = true)
	@NotBlank(message = "Email não pode ser nulo")
	private String email;
	
	@Column(nullable = false, unique = true)
	@NotBlank(message = "CPF/CNPJ não pode ser nulo")
	private String cpfOuCnpj;
	
	@Column(nullable = false)
	@NotBlank(message = "Senha não pode ser nulo")
	private String password;
	
	@ManyToOne()
	@JoinColumn(name = "id_tipo")
	private TipoUsuario tipo;
	
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	public Usuario() {
		addPerfil(Perfil.USUARIO);
	}

	public Usuario(Long id, String nome, String cpfOuCnpj, String email, String senha, TipoUsuario tipo) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.password = senha;
		this.tipo = tipo;
		addPerfil(Perfil.USUARIO);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOrCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	/*
	public UserSS toUserSS() {
		UserSS userSS = new UserSS(this.id, this.email, getPerfis());
		return userSS;
	}
	*/

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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", password=" + password + ", perfis="
				+ perfis.toString() + "]";
	}

}
