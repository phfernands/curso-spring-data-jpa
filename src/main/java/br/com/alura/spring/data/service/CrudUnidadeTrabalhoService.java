package br.com.alura.spring.data.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {

	private final UnidadeTrabalhoRepository trabalhoRepository;
	private Boolean system = true;

	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository trabalhoRepository) {
		this.trabalhoRepository = trabalhoRepository;
	}

	public void inicializar(Scanner scanner) {
		System.out.println("Qual ação voce deseja reazilar?");
		System.out.println("1 - Salvar");
		System.out.println("2 - Atualizar dados");
		System.out.println("3 - Lista de unidades");
		System.out.println("4 - Deletar unidade");
		System.out.println("0 - Sair");
		
		int action = scanner.nextInt();
		
		switch (action) {
		case 1:
			salvar(scanner);
			break;
			
		case 2:
			atualizar(scanner);
			break;
		
		case 3:
			listar();
			break;
			
		case 4:
			deletar(scanner);
			break;

		default:
			system = false;
			break;
		}
		
	}

	private void salvar(Scanner scanner) {
		System.out.println("Digite nome da unidade");
		String nome = scanner.next();
		UnidadeTrabalho unidade = new UnidadeTrabalho();
		unidade.setDescricao(nome);

		System.out.println("Endereço da unidade");
		String endereco = scanner.next();
		unidade.setEndereco(endereco);
		trabalhoRepository.save(unidade);
		System.out.println("Unidade salva");

	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o ID da inudade a ser atualizada");
		Integer id = scanner.nextInt();
		Optional<UnidadeTrabalho> opicional = this.trabalhoRepository.findById(id);

		if (opicional.isPresent()) {
			UnidadeTrabalho unidade = opicional.get();

			System.out.println("Nome da unidade:");
			String nome = scanner.next();
			unidade.setDescricao(nome);
			System.out.println("Novo endereço da unidade:");
			String endereco = scanner.next();
			unidade.setEndereco(endereco);
			trabalhoRepository.save(unidade);
			System.out.println("Unidade atualizada");
		} else {
			System.out.println("ID " + id + " inexistete.");
		}
	}
	
	private void listar() {
		Iterable<UnidadeTrabalho> unidades = trabalhoRepository.findAll();
		unidades.forEach(local -> System.out.println(local));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Digite o ID da unidade a ser deletada do sistema");
		Integer id = scanner.nextInt();
		Optional<UnidadeTrabalho> opicinal = trabalhoRepository.findById(id);
		
		if(opicinal.isPresent()) {
			trabalhoRepository.deleteById(id);
			System.out.println("Unidade deletada");
		} else {
			System.out.println("ID " + id + " inexistete.");
		}
	}

}
