package org.serratec.funcionario;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.serratec.dependente.excessao.DependenteException;
import org.serratec.enums.TipoParentesco;

public class Dependente extends Pessoa {
	
	private TipoParentesco parentesco;
	Set<Pessoa> dependentes = new HashSet<>();
	
	
	public Dependente(String nome, String cpf, LocalDate dtNascimento,TipoParentesco parentesco){
		super(nome, cpf);		
		verificarIdade(dtNascimento);
		this.parentesco = parentesco;	
	}
	public Set<Pessoa> chamarLista() {
		return dependentes;
	}
	
	public void cadastrarDependente(Pessoa dep){
		dependentes.add(dep);
	}
	public TipoParentesco getParentesco() {
		return parentesco;
	}

	@Override
	public void setDtNascimento(LocalDate dtNascimento) {
			try {
				verificarIdade(dtNascimento);
			} catch (DependenteException e) {}
			
	}
	
	
	public String retornaTipoParentesco() {
		return getParentesco().getDesc();	
	}
	
	
	//Verifica a idade do dependente;
	@Override
	public void verificarIdade(LocalDate dtNascimento) {
		if(Period.between(dtNascimento, LocalDate.now()).getYears() < 18) {
			this.dtNascimento = dtNascimento;
		}else {
			throw new DependenteException("Dependente " + this.nome + " é maior de 18 anos, favor verificar");
		}
	}
	@Override
	public String toString() {
		return "Dependente [parentesco=" + parentesco + ", dependentes=" + dependentes + "]";
	}

	
}
