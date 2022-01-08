package org.training.campus.onlineshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.training.campus.onlineshop.dao.jdbc.JdbcTemplateProductDao;
import org.training.campus.onlineshop.model.Product;

@Component
public class ProductService {

	@Autowired
	private JdbcTemplateProductDao dao;

	public List<Product> getAll() {
		return dao.getAll();
	}

	public void persist(Product product) {
		dao.persist(product);
	}

	public void merge(Product product) {
		dao.merge(product);
	}

	public void remove(Product product) {
		dao.remove(product);
	}

	public void remove(long id) {
		dao.remove(id);
	}

	public Optional<Product> findById(long id) {
		return dao.findById(id);
	}

}
