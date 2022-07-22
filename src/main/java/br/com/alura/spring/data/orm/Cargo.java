package br.com.alura.spring.data.orm;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cargos")
public class Cargo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String cargo;
	
	@OneToMany(mappedBy = "cargo", fetch = FetchType.EAGER)
	private Set<Funcionario> listaFuncionarios;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public Set<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}
	@Override
	public String toString() {
		return "Cargo [id=" + id + ", cargo=" + cargo + "]";
	}
	
}
