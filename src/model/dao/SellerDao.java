package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {

	/*Operação responsável por inserir no BD e receberá Seller como
	parâmetro de entrada*/
	void insert(Seller obj);
	void update(Seller obj);
	void deleteById(Integer id);
	//Responsável por consultar o id no BD. Se existir vai retornar o Seller.
	//Se não existir vai retornar nulo.
	Seller findById(Integer id);
	//Retorna todos os departamentos.
	List<Seller> findAll();
}
