package org.serratec.funcionario.interfaces;

public interface CalculoTributos {
	//Atributo para auxiliar calculo de IR;
	public final double VALOR_DEP = 189.59;
	
	
	//M�todos para o c�lculo dos tributos;
	double calcularInss();
	double calcularIr();	
	String arredondar(Double valor);
}
