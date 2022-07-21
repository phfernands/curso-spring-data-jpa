package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;
	private final CrudUnidadeTrabalhoService trabalhoService;

	private Boolean system = true;

	public SpringDataApplication(CrudCargoService service, CrudUnidadeTrabalhoService service2) {
		this.cargoService = service;
		this.trabalhoService = service2;
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

			int action = scanner.nextInt();
			if (action == 1) {
				cargoService.inicial(scanner);
			}
			if (action == 2) {
				trabalhoService.inicializar(scanner);
			}
			if (action == 0) {
				system = false;
			}

		}

	}

}
