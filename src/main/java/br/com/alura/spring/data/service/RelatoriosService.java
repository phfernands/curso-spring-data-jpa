package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private final FuncionarioRepository funcionarioRepository;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicializar(Scanner scanner) {

		Boolean isTrue = true;

		while (isTrue) {
			System.out.println("Qual acao de busca voce deseja executar?");
			System.out.println("1 - Busca pro nome de funcionario");
			System.out.println("2 - Busca por nome, salario e data de contratação");
			System.out.println("3 - Listar funcionarios por data de contratação");
			System.out.println("0 - Sair");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				buscaFuncionarioPorNome(scanner);
				break;
				
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
				
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
				
			default:
				isTrue = false;
				break;
			}
		}
	}

	private void buscaFuncionarioPorNome(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = scanner.next();

		List<Funcionario> lista = funcionarioRepository.findByNome(nome);
		if(lista.isEmpty()) {
			System.out.println("Nao existe funcionario com esse nome.");
		} else {
			lista.forEach(func -> System.out.println(func));
		}
		
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Digite o nome do funcionario");
		String nome = scanner.next();
		
		System.out.println("Digite o salario");
		BigDecimal salario = scanner.nextBigDecimal();
		
		System.out.println("Digite a data de contratação do funcionario");
		String data = scanner.next();
		LocalDate date = LocalDate.parse(data, formatter);
		
		List<Funcionario> lista = funcionarioRepository
				.findNomeSalarioMaiorDataContratacao(nome, salario, date);
		lista.forEach(func -> System.out.println(func));
		
		
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Digite a data inicial da pesquisa:");
		String nome = scanner.next();
		LocalDate date = LocalDate.parse(nome, formatter);
		
		List<Funcionario> lista = funcionarioRepository.findDataDeContratacaoMaior(date);
		lista.forEach(func -> System.out.println(func));
	}

}
