package org.serratec.funcionario;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.serratec.funcionario.interfaces.CalculoTributos;


public class Funcionario extends Pessoa implements CalculoTributos{
	private double salarioBruto;
	private double salarioliq;
	private double descInss;
	private double descIr;
	public int qtdDep = 0;
	
	Set<Funcionario> funcionarios = new HashSet<>();

	public int getFuncionarios() {
		return funcionarios.size();
	}
	 
	public Funcionario(String nome, String cpf, LocalDate dtNascimento, double salarioBruto) {
		super(nome, cpf, dtNascimento);
		this.salarioBruto = salarioBruto;
		this.qtdDep = qtdDep;
	}

	

	public Set<Funcionario> chamaLista() {
		return funcionarios;
	}
	
	public void cadastrarFuncionario(Funcionario f){
		funcionarios.add(f);
	}
	
	
	@Override
	public String getCpf() {
		return cpf;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public double getDescInss() {
		return descInss;
	}

	public double getDescIr() {
		return descIr;
	}
	
	public double getSalarioliq() {
		return salarioliq;
	}
	
	
	//Calcula o valor de Desconto de INSS;
	@Override
	public double calcularInss() {
		if(this.salarioBruto <= 1100) {
			return this.descInss = (this.salarioBruto * 7.5/100);
		}
		else if(this.salarioBruto <= 2203.48) {
			return this.descInss = ((this.salarioBruto * 9/100) - 16.5);
		}
		else if(this.salarioBruto <= 3305.22) {
			return this.descInss = ((this.salarioBruto * 12/100) - 82.61);
		}
		else if(this.salarioBruto <= 6433.57) {
			return this.descInss = ((this.salarioBruto * 14/100) - 148.72);
		}
		else {
			return this.descInss = 751.98;
		}	
	}
	
	
	//Calcula o valor de Desconto de IR;
	@Override
	public double calcularIr() {
		
		double baseIR = (salarioBruto - (this.qtdDep * VALOR_DEP) - this.descInss);
		
		if(baseIR <= 1903.98) {
			return this.descIr = 0;			
		}
		else if(baseIR <= 2826.65) {
			return this.descIr = ((baseIR * 7.5/100) - 142.8); 		
		}
		else if(baseIR <= 3751.05) {
			return this.descIr = ((baseIR * 15/100)- 354.8); 		
		}
		else if(baseIR <= 4664.68) {
			return this.descIr = ((baseIR * 22.5/100)- 636.13); 		
		}
		else{
			return this.descIr = ((baseIR * 27.5/100) - 869.36); 		
		}
	}
	
	//Calcula o salário líquido do funcionário e adiciona no atributo salarioliq;
	public double calculaSalarioLiq() {
		return this.salarioliq = this.salarioBruto - this.descInss - this.descIr;	
	}
	
	
	//Arredonda o valor dos tributos para uma forma mais clara;
    public String arredondar(Double valor) {
    	return new DecimalFormat("#,##0.00").format(valor);
    }
	
    
	//ToString para visualização;
	@Override
	public String toString() {		
		return "Funcionario: " + getNome() + 
				"\nSalario Bruto: R$" + salarioBruto + 
				"\nDesconto INSS: R$" + arredondar(calcularInss()) +
				"\nQuantidade de dependentes: " + qtdDep + 
				"\tValor abatido no IR: R$" + (qtdDep * VALOR_DEP) +
				"\nDesconto de IR: R$" + arredondar(calcularIr()) +
				"\nSalario liquido: R$" + arredondar(calculaSalarioLiq());
	}


	public boolean createNewFile() {
		return false;
	}



}
