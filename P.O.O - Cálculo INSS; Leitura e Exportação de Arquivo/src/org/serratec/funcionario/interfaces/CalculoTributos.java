package org.serratec.funcionario.interfaces;

public interface CalculoTributos {
	//Atributo para auxiliar calculo de IR;
	public final double VALOR_DEP = 189.59;
	
	
	//Métodos para o cálculo dos tributos;
	double calcularInss();
	double calcularIr();	
	String arredondar(Double valor);
}
