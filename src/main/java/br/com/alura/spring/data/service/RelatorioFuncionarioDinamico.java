package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {
	
	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicializar(Scanner scanner) {
		System.out.println("Digite o nome do funcionario para consulta: (Caso não queira pesquisar pelo nome, digite 0)");
		String nome = scanner.next();
		
		if(nome.equalsIgnoreCase("0")) {
			nome = null;
		}
		
		System.out.println("Digite o CPF do funcionario: (Caso não queira pesquisar pelo CPF, digite 0");
		String cpf = scanner.next();
		
		if(cpf.equalsIgnoreCase("0")) {
			cpf = null;
		}
		
		System.out.println("Digite o salario a ser consultado: (Caso não queira pesquisar pelo salario, digite 0");
		BigDecimal salario = scanner.nextBigDecimal();
		String valor = salario.toString();
		
		if(valor.equalsIgnoreCase("0")) {
			salario = null;
		}
		
		System.out.println("Digite a data de contratação: (Caso não queira pesquisar pela data, digite 0)");
		String data = scanner.next();
		LocalDate dataContratacao;
		
		if(data.equalsIgnoreCase("0")) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> funcionarios = funcionarioRepository
				.findAll(Specification.where(SpecificationFuncionario.nome(nome))
						.or(SpecificationFuncionario.cpf(cpf))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacao)));
		
		funcionarios.forEach(f -> System.out.println(f));
	}
	
}
