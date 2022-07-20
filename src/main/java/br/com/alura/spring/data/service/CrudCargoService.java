package br.com.alura.spring.data.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {

	private final CargoRepository cargoRepository;
	private Boolean system = true;

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public void inicial(Scanner scanner) {
		System.out.println("Qual ação de cargo você deseja executar?");
		System.out.println("0 - Sair");
		System.out.println("1 - Salvar");
		System.out.println("2 - Atualizar");
		System.out.println("3 - Visualizar tabela de cargos");
		System.out.println("4 - Deletar cargo");

		int action = scanner.nextInt();

		switch (action) {
		case 1:
			salvar(scanner);
			break;

		case 2:
			atualizar(scanner);
			break;
			
		case 3:
			visualizar();
			break;
			
		case 4:
			deletar(scanner);

		default:
			system = false;
			break;
		}

	}

	private void salvar(Scanner scanner) {
		System.out.println("Descrição do cargo");
		String descricao = scanner.next();

		Cargo cargo = new Cargo();
		cargo.setCargo(descricao);
		cargoRepository.save(cargo);
		System.out.println("Salvo");
	}

	private void atualizar(Scanner scanner) {

		System.out.println("Digite o ID do cargo a ser atualizado:");
		int id = scanner.nextInt();

		Optional<Cargo> optional = this.cargoRepository.findById(id);
		if (optional.isPresent()) {
			Cargo cargo = optional.get();

			System.out.println("Descrição do cargo");
			String descricao = scanner.next();

			cargo.setCargo(descricao);
			cargoRepository.save(cargo);
			System.out.println("Cargo atualizado.");

		} else {
			System.out.println("O ID do cargo informado: " + id + " é invalido");
		}

	}

	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();

		cargos.forEach(cargo -> System.out.println(cargo));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Digite o ID do cargo que você deseja deletar:");
		int id = scanner.nextInt();
		
		Optional<Cargo> opicional= this.cargoRepository.findById(id);
		if(opicional.isPresent()) {
			cargoRepository.deleteById(id);
			System.out.println("Cargo deletado");
		} else {
			System.out.println("ID inválido");
		}
	}

}
