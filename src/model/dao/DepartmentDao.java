package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {

	/*Opera��o respons�vel por inserir no BD e receber� Department como
	par�metro de entrada*/
	void insert(Department obj);
	void update(Department obj);
	void deleteById(Integer id);
	//Respons�vel por consultar o id no BD. Se existir vai retornar o Department.
	//Se n�o existir vai retornar nulo.
	Department findById(Integer id);
	//Retorna todos os departamentos.
	List<Department> findAll();
}
