package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {

	/*Opera��o respons�vel por inserir no BD e receber� Seller como
	par�metro de entrada*/
	void insert(Seller obj);
	void update(Seller obj);
	void deleteById(Integer id);
	//Respons�vel por consultar o id no BD. Se existir vai retornar o Seller.
	//Se n�o existir vai retornar nulo.
	Seller findById(Integer id);
	//Retorna todos os departamentos.
	List<Seller> findAll();
}
