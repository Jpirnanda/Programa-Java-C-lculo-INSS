package org.serratec.dependente.excessao;

public class DependenteException extends RuntimeException{	

	private static final long serialVersionUID = 4410728854053266134L;

	public DependenteException(String mensagem) {
		super(mensagem);
	}
}
