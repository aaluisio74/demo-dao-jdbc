package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {

	/*Operação responsável por inserir no BD e receberá Department como
	parâmetro de entrada*/
	void insert(Department obj);
	void update(Department obj);
	void deleteById(Integer id);
	//Responsável por consultar o id no BD. Se existir vai retornar o Department.
	//Se não existir vai retornar nulo.
	Department findById(Integer id);
	//Retorna todos os departamentos.
	List<Department> findAll();
}
