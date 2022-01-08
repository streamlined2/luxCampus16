package org.training.campus.onlineshop.dao;

import java.util.List;
import java.util.Optional;

import org.training.campus.onlineshop.model.Product;

public interface ProductDao {

	List<Product> getAll();

	Optional<Product> findById(long id);

	void persist(Product product);

	void merge(Product product);

	void remove(Product product);

	void remove(long id);

}
