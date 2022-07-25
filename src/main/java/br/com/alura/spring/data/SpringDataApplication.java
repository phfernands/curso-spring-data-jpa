package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.alura.spring.data.service.RelatorioFuncionarioDinamico;
import br.com.alura.spring.data.service.RelatoriosService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;
	private final CrudUnidadeTrabalhoService trabalhoService;
	private final CrudFuncionarioService funcionarioService;
	private final RelatoriosService relatorioService;
	private final RelatorioFuncionarioDinamico dinamicoService;

	private Boolean system = true;

	public SpringDataApplication(CrudCargoService service, RelatoriosService  service4, CrudUnidadeTrabalhoService service2, CrudFuncionarioService service3, RelatorioFuncionarioDinamico service5) {
		this.cargoService = service;
		this.trabalhoService = service2;
		this.funcionarioService = service3;
		this.relatorioService = service4;
		this.dinamicoService = service5;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual ação você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidade");
			System.out.println("3 - Funcionario");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Pesquisa Dinamica");

			int action = scanner.nextInt();
			if (action == 1) {
				cargoService.inicializar(scanner);
			}
			if (action == 2) {
				trabalhoService.inicializar(scanner);
			}
			if(action == 3) {
				funcionarioService.inicializar(scanner);
			}
			if(action == 4) {
				relatorioService.inicializar(scanner);
			}
			if(action == 5) {
				dinamicoService.inicializar(scanner);
			}
			if (action == 0) {
				system = false;
			}

		}

	}

}
