package br.com.alura.spring.data.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private final FuncionarioRepository repository;
	private final UnidadeTrabalhoRepository unidadeRepository;
	private final CargoRepository cargoRepository;
	

	public CrudFuncionarioService(FuncionarioRepository repository, CargoRepository cargoRepository,
			UnidadeTrabalhoRepository unidadeRepository) {
		this.repository = repository;
		this.cargoRepository = cargoRepository;
		this.unidadeRepository = unidadeRepository;
	}

	public void inicializar(Scanner scanner) {
		
		Boolean system = true;
	
		while(system == true ) {
			
			System.out.println("Qual ação voce deseja reazilar?");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar dados");
			System.out.println("3 - Listar funcionarios");
			System.out.println("4 - Deletar funcionario");
			System.out.println("5 - Adicionar unidades de trabalho para o funcionario");
			System.out.println("6 - Visualizar dados de um funcionario");
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
				
			case 5:
				adicionarUnidadeTrablho(scanner);
				break;
				
			case 6:
				visualizar(scanner);
				break;
				
			default:
				system = false;
				break;
				
			}
		}
		
	}

	private void salvar(Scanner scanner) {
		System.out.println("Digite o nome do novo funcionario");
		String nome = scanner.next();
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);

		System.out.println("Digite o CPF do funcionario");
		String cpf = scanner.next();
		funcionario.setCpf(cpf);

		System.out.println("Digite o salario do funcionario");
		BigDecimal salario = scanner.nextBigDecimal();
		funcionario.setSalario(salario);

		System.out.println("Digite o ID do cargo");
		Integer id = scanner.nextInt();
		Optional<Cargo> optCargo = cargoRepository.findById(id);
		Cargo cargo = optCargo.get();
		funcionario.setCargo(cargo);

		repository.save(funcionario);
		System.out.println("Funcionario salvo");

	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Digite o ID do funcionario a ser atualizado");
		Integer id = scanner.nextInt();
		Optional<Funcionario> opicional = this.repository.findById(id);

		if (opicional.isPresent()) {
			Funcionario funcionario = opicional.get();
			System.out.println("Digite o nome funcionario");
			String nome = scanner.next();
			funcionario.setNome(nome);

			System.out.println("Digite o CPF do funcionario");
			String cpf = scanner.next();
			funcionario.setCpf(cpf);

			System.out.println("Digite o salario do funcionario");
			BigDecimal salario = scanner.nextBigDecimal();
			funcionario.setSalario(salario);

			System.out.println("Digite o ID do cargo");
			Integer id2 = scanner.nextInt();
			Optional<Cargo> optCargo = cargoRepository.findById(id2);
			Cargo cargo = optCargo.get();
			funcionario.setCargo(cargo);

			repository.save(funcionario);
			System.out.println("Funcionario atualizado");
		} else {
			System.out.println("ID " + id + " inexistete.");
		}
	}
	
	private void listar() {
		Iterable<Funcionario> listaFuncionarios = repository.findAll();
		listaFuncionarios.forEach(func -> System.out.println(func));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Digite o ID da unidade a ser deletada do sistema");
		Integer id = scanner.nextInt();
		Optional<Funcionario> opicinal = repository.findById(id);
		
		if(opicinal.isPresent()) {
			repository.deleteById(id);
			System.out.println("Funcionario deletado");
		} else {
			System.out.println("ID " + id + " inexistete.");
		}
		
	}
	
	private Set<UnidadeTrabalho> adicionar(Scanner scanner) {
		Boolean isTrue = true;
		Set<UnidadeTrabalho> unidades = new HashSet<UnidadeTrabalho>();
		
		while(isTrue) {
			System.out.println("Digite o ID da unidade que o funcionario irá trabalhar. (Digite '0' para sair)");
			Integer id = scanner.nextInt();
			
			if(id > 0) {
				System.out.println("----------------------------------------------------------------");
				System.out.println("Unidade ID: " + id);
				Optional<UnidadeTrabalho> opicional = unidadeRepository.findById(id);
				System.out.println("----------------------------------------------------------------");
				
				if(opicional.isPresent()) {
					unidades.add(opicional.get());
				} else {
					System.out.println("ID de Unidade inexistente");
				}
			} else {
				isTrue = false;
			}
		}
		return unidades;
	}
	
	private void adicionarUnidadeTrablho(Scanner scanner) {
		
		System.out.println("Digite o ID do funcionario para adicionar sua(s) unidade(s) de trabalho:");
		Integer id = scanner.nextInt();
		
		Optional<Funcionario> opicional = repository.findById(id);
		if(opicional.isPresent()) {
			Funcionario funcionario = opicional.get();
			Set<UnidadeTrabalho> novaUnidade = this.adicionar(scanner);
			funcionario.getUnidadeTrabalho().addAll(novaUnidade);
			repository.save(funcionario);
			System.out.println("Unidade(s) de trabalho adicionada(s).");
		}
	}
	
	private void visualizar(Scanner scanner) {
		System.out.println("Digite o ID do funcionaria para visualizar seus dados:");
		Integer id = scanner.nextInt();
		
		Optional<Funcionario> opicional = repository.findById(id);
		if(opicional.isPresent()) {
			Funcionario func = opicional.get();
			
			System.out.println("-DADOS DO FUNCIONARIO-");
			System.out.println("ID: " + func.getId());
			System.out.println("Nome: " + func.getNome());
			System.out.println("CPF: " +  func.getCpf());
			System.out.println("Cargo: " + func.getCargo());
			System.out.println("Salario: " + func.getSalario());
			System.out.println(" ");
			System.out.println("Unidade: (");
			for(UnidadeTrabalho unidade : func.getUnidadeTrabalho()) {
				System.out.println("Nome: " + unidade.getDescricao());
				System.out.println("Endereco: " + unidade.getEndereco());
			}
			System.out.println(")");
			System.out.println(" ");
		} else {
			System.out.println("ID " + id + " inexistete.");
		}
	}

}
