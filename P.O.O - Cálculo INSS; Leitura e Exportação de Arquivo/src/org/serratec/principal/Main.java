package org.serratec.principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.serratec.enums.TipoParentesco;
import org.serratec.funcionario.Dependente;
import org.serratec.funcionario.Funcionario;



public class Main {	

	public static void main(String[] args) throws IOException {
		String nome;
		String cpf;
		String data;
		LocalDate dtNascimento;
		double salarioBruto;
		TipoParentesco tpParentesco;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("entrada.csv"));
		//Ler a primeira linha que é o funcionário e realiza seu cadastro;
		for (int i = 0; i < 1; i++) {
			String linha1 = br.readLine();
			String[]palavras1 = linha1.split(";");
				nome = palavras1[0];
				cpf = palavras1[1];
				data = palavras1[2];
				dtNascimento = LocalDate.parse(data, formatter);
				salarioBruto = Double.parseDouble(palavras1[3]);
				
				Funcionario f = new Funcionario(nome, cpf, dtNascimento, salarioBruto);
				f.cadastrarFuncionario(f);
		
				
			//Enquanto tem linhas, executa o while	
			while(br.ready()) {
				String linha = br.readLine();
				String[]palavras = linha.split(";");
				//System.out.print(linha+"\n"+"j");
				
				//Verifica se a linha é branca, se for a próxima é um funcionário
				//então, essa função realiza o cadastro deste funcionário
				if(linha.isBlank()){
					linha = br.readLine();
					palavras = linha.split(";");
					nome = palavras[0];
					cpf = palavras[1];
					data = palavras[2];
					dtNascimento = LocalDate.parse(data, formatter);
					salarioBruto = Double.parseDouble(palavras[3]);
					
		
					Funcionario f1 = new Funcionario(nome, cpf, dtNascimento, salarioBruto);
					f1.cadastrarFuncionario(f1);
					
					//Verifica se a linha é legível e, se não for em branco, adiciona o próximo dependente para Raquel. Se for em branco, volta
					//para adicionar os dependentes em Maria. 
					
					if(br.ready()) {
						String linha2 = br.readLine();
						String[]palavras2 = linha2.split(";");
						
						if(!linha2.isBlank()) {
							nome = palavras2[0];
							cpf = palavras2[1];
							data = palavras2[2];
							dtNascimento = LocalDate.parse(data, formatter);
							f1.qtdDep++;
							if(palavras2[3].equals("FILHO")) {
								tpParentesco = TipoParentesco.FILHO;
							}
							else if(palavras[3].equals("SOBRINHO")) {
								tpParentesco = TipoParentesco.SOBRINHO;
							}
							
							else {
								tpParentesco = TipoParentesco.OUTROS;
							}

							
							Dependente dep = new Dependente(nome, cpf, dtNascimento, tpParentesco);
							dep.cadastrarDependente(dep);
				
							//Aqui cria o arquivo de escrita (fileWriter) e escreve (já chamando os métodos que calculam) no arquivo.
							//A escrita do arquivo está aqui por questão de escopo, não sei como pegaria o f1 por fora desse loop.
							try {
								FileWriter myWriter = new FileWriter("DadosExportados.txt");
								myWriter.write("Funcionario: " + f1.getNome() + 
											"\nSalario Bruto: R$" + f1.getSalarioBruto() + 
											"\nDesconto INSS: R$" + f1.arredondar(f1.calcularInss()) +
											"\nQuantidade de dependentes: " + f1.qtdDep + 
											"\tValor abatido no IR: R$" + (f1.qtdDep * f1.VALOR_DEP) +
											"\nDesconto de IR: R$" + f1.arredondar(f1.calcularIr()) +
											"\nSalario liquido: R$" + f1.arredondar(f1.calculaSalarioLiq())+
											"\n" + "\n"+
											
											"Funcionario: " + f.getNome() + 
											"\nSalario Bruto: R$" + f.getSalarioBruto() + 
											"\nDesconto INSS: R$" + f.arredondar(f.calcularInss()) +
											"\nQuantidade de dependentes: " + f.qtdDep + 
											"\tValor abatido no IR: R$" + (f.qtdDep * f.VALOR_DEP) +
											"\nDesconto de IR: R$" + f.arredondar(f.calcularIr()) +
											"\nSalario liquido: R$" + f.arredondar(f.calculaSalarioLiq())+
											"\n" + "\n");
								myWriter.close();
								System.out.println("Arquivo escrito com sucesso.");
								} catch (IOException e) {
								System.out.println("Erro: "+ e);
								}
						}
					}	
				}
				
				//Se a linha não for branca, cadastra o dependente;
				else if(!linha.isBlank()) {
					nome = palavras[0];
					cpf = palavras[1];
					data = palavras[2];
					dtNascimento = LocalDate.parse(data, formatter);
					f.qtdDep++;
					if(palavras[3].equals("FILHO")) {
						tpParentesco = TipoParentesco.FILHO;
					}
					else if(palavras[3].equals("SOBRINHO")) {
						tpParentesco = TipoParentesco.SOBRINHO;
					}
					
					else {
						tpParentesco = TipoParentesco.OUTROS;
					}

					
					Dependente dep = new Dependente(nome, cpf, dtNascimento, tpParentesco);
					dep.cadastrarDependente(dep);


				}

			}
		}	

		
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao acessar arquivo");
		}		
	}

}



