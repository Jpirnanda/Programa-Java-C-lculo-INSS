package org.serratec.funcionario;

import java.time.LocalDate;

public abstract class Pessoa {
	protected String nome;
	protected String cpf;
	protected LocalDate dtNascimento;

	
	protected Pessoa(String nome, String cpf, LocalDate dtNascimento) {
		super();
		this.nome = nome;
		this.cpf =cpf;
		this.dtNascimento = dtNascimento;
	}
	public Pessoa(String nome, String cpf) {
		this.nome = nome;
		this.cpf =cpf;
	}
	
	public void verificarIdade(LocalDate dtNascimento) {}
	

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDtNascimento(LocalDate dtNascimento) {}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	//Método para verificar se o CPF é igual a um já existente;	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
}
