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
		
		int action = scanner.nextInt();
		
		switch (action) {
		case 1:
			salvar(scanner);
			break;
			
		case 2:
			atualizar(scanner);
			break;
			
		default:
			system = false;
			break;
		}
		
	}
	
	public void salvar(Scanner scanner) {
		System.out.println("Descrição do cargo");
		String descricao = scanner.next();
		
		Cargo cargo = new Cargo();
		cargo.setCargo(descricao);
		cargoRepository.save(cargo);
		System.out.println("Salvo");
	}
	
	public void atualizar(Scanner scanner) {
		
		System.out.println("Digite o ID do cargo a ser atualizado:");
		int id = scanner.nextInt();
		
		Optional<Cargo> optional = this.cargoRepository.findById(id);
		if(optional.isPresent()) {
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
	
	

}
